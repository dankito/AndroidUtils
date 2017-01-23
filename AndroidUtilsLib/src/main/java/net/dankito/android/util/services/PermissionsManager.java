package net.dankito.android.util.services;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;


public class PermissionsManager implements IPermissionsManager {

  protected Activity activity;

  protected int nextRequestCode = 27388;

  protected Map<String, List<PermissionRequestCallback>> pendingPermissionRequests = new ConcurrentHashMap<>();


  public PermissionsManager(Activity activity) {
    this.activity = activity;
  }


  /**
   * <p>
   *  To be called from {@link #activity}.
   *  Simple pass all parameters passed to Activity's onRequestPermissionsResult() to this method.
   * </p>
   *
   * <p>
   *  Responses to permission requests are sent to an Activity, so there's no other way then doing it that cumbersome.
   * </p>
   */
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    String permission = permissions[0];
    List<PermissionRequestCallback> callbacks = null;

    synchronized(pendingPermissionRequests) {
      callbacks = pendingPermissionRequests.remove(permission);
    }

    if(callbacks != null) {
      if(permissions != null && permissions.length > 0 && grantResults != null && grantResults.length > 0) {
        boolean permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        for(PermissionRequestCallback callback : callbacks) {
          callback.permissionCheckDone(permission, permissionGranted);
        }
      }
    }
  }


  /**
   * <p>
   *  Checks first if the {@code permission} is granted. If so, returns immediately.
   * </p>
   * <p>
   *  If not, checks if permission has been requested before. If so, User has to be shown a rationale why she/he's being re-asked.<br/>
   *  If permission has never been requested before or User allows re-requesting it, a permission request will be passed on to User.
   * </p>
   * @param permission A value from {@link Manifest.permission}
   * @param rationaleToShowToUserResourceId The string resource id of rationale shown to User before re-requesting permission.
   * @param callback The callback being called when determined if permission is granted or not.
   */
  @Override
  public void checkPermission(String permission, int rationaleToShowToUserResourceId, PermissionRequestCallback callback) {
    checkPermission(permission, activity.getResources().getString(rationaleToShowToUserResourceId), callback);
  }

  /**
   * <p>
   *  Checks first if the {@code permission} is granted. If so, returns immediately.
   * </p>
   * <p>
   *  If not, checks if permission has been requested before. If so, User has to be shown a rationale why she/he's being re-asked.<br/>
   *  If permission has never been requested before or User allows re-requesting it, a permission request will be passed on to User.
   * </p>
   * @param permission A value from {@link Manifest.permission}
   * @param rationaleToShowToUser The rationale shown to User before re-requesting permission.
   * @param callback The callback being called when determined if permission is granted or not.
   */
  @Override
  public void checkPermission(String permission, String rationaleToShowToUser, PermissionRequestCallback callback) {
    if(isPermissionGranted(permission)) {
      callback.permissionCheckDone(permission, true);
    }
    else {
      requestPermission(permission, rationaleToShowToUser, callback);
    }
  }

  /**
   * <p>
   *  Checks for each permission first if the {@code permission} is granted. If so, returns immediately.
   * </p>
   * <p>
   *  If not, checks if permission has been requested before. If so, User has to be shown a rationale why she/he's being re-asked.<br/>
   *  If permission has never been requested before or User allows re-requesting it, a permission request will be passed on to User.
   * </p>
   * @param permissions A value from {@link Manifest.permission}
   * @param rationalesToShowToUser The rationales shown to User before re-requesting permission.
   * @param callback The callback being called when determined if permission is granted or not.
   */
  @Override
  public void checkPermissions(final String[] permissions, final String[] rationalesToShowToUser, final MultiplePermissionsRequestCallback callback) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        checkPermissionsInNewThread(permissions, rationalesToShowToUser, callback);
      }
    }).start();
  }

  /**
   * countDownLatch.await() blocks current thread -> do not block calling thread
   * @param permissions
   * @param rationalesToShowToUser
   * @param callback
   */
  protected void checkPermissionsInNewThread(String[] permissions, String[] rationalesToShowToUser, final MultiplePermissionsRequestCallback callback) {
    final Map<String, Boolean> permissionResults = new ConcurrentHashMap<>();
    final CountDownLatch countDownLatch = new CountDownLatch(permissions.length);

    for(int i = 0; i < permissions.length; i++) {
      String permission = permissions[i];

      if(isPermissionGranted(permission)) {
        permissionResults.put(permission, true);
        countDownLatch.countDown();
      }
      else {
        requestPermission(permission, rationalesToShowToUser[i], new PermissionRequestCallback() {
          @Override
          public void permissionCheckDone(String permission, boolean isGranted) {
            permissionResults.put(permission, isGranted);
            countDownLatch.countDown();
          }
        });
      }
    }

    try { countDownLatch.await(); } catch(Exception ignored) { }

    callback.permissionsCheckDone(permissionResults);
  }

  /**
   *  Checks if a permission is already granted.
   *
   * @param permission A value from {@link Manifest.permission}
   * @return
   */
  @Override
  public boolean isPermissionGranted(String permission) {
    return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(activity, permission);
  }


  /**
   * Checks if permission has been requested before.<br/>
   * If so, shows a rationale to User why permission is re-request.<br/>
   * If not, requests the permission directly from User.
   * @param permission A value from {@link Manifest.permission}
   * @param rationaleToShowToUser The rationale shown to User before re-requesting permission.
   * @param callback The callback being called when determined if permission is granted or not.
   */
  @Override
  public void requestPermission(final String permission, String rationaleToShowToUser, PermissionRequestCallback callback) {
    if(ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
      // Provide an additional rationale to the user if the permission was not granted
      // and the user would benefit from additional context for the use of the permission.
      // For example if the user has previously denied the permission.

      showRationale(permission, rationaleToShowToUser, callback);

    }
    else {
      // permission has not been granted yet. Request it directly.
      requestPermissionFromUser(permission, callback);
    }
  }

  /**
   * Calls {@link #showRationaleThreadSafe(String, String, PermissionRequestCallback)} on UI thread.
   * @param permission A value from {@link Manifest.permission}
   * @param rationaleToShowToUser The rationale shown to User before re-requesting permission.
   * @param callback The callback being called when determined if permission is granted or not.
   */
  protected void showRationale(final String permission, final String rationaleToShowToUser, final PermissionRequestCallback callback) {
    activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        showRationaleThreadSafe(permission, rationaleToShowToUser, callback);
      }
    });
  }

  /**
   * Shows rationale to User why permission is re-requested.<br/>
   * If User allows re-requesting, {@link #requestPermissionFromUser(String, PermissionRequestCallback)} is called.
   * @param permission A value from {@link Manifest.permission}
   * @param rationaleToShowToUser The rationale shown to User before re-requesting permission.
   * @param callback The callback being called when determined if permission is granted or not.
   */
  protected void showRationaleThreadSafe(final String permission, String rationaleToShowToUser, final PermissionRequestCallback callback) {
    new AlertDialog.Builder(activity)
        .setMessage(rationaleToShowToUser)
        .setNegativeButton(android.R.string.no, null)
        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            requestPermissionFromUser(permission, callback);
          }
        }).show();
  }

  /**
   * Shows request for permission to User.
   * @param permission A value from {@link Manifest.permission}
   * @param callback The callback being called when determined if permission is granted or not.
   */
  protected void requestPermissionFromUser(String permission, PermissionRequestCallback callback) {
    synchronized(pendingPermissionRequests) {
      if(pendingPermissionRequests.containsKey(permission)) { // there's already a pending requestPermissions() call for this permission -> don't ask again, add to pending permissions
        pendingPermissionRequests.get(permission).add(callback);
      }
      else {
        int requestCode = nextRequestCode++;

        List<PermissionRequestCallback> callbacksForPermission = new ArrayList<>();
        callbacksForPermission.add(callback);
        pendingPermissionRequests.put(permission, callbacksForPermission);

        ActivityCompat.requestPermissions(activity,
            new String[] { permission },
            requestCode);
      }
    }
  }

}
