package net.dankito.android.util.services;

import android.Manifest;
import android.support.annotation.NonNull;

public interface IPermissionsManager {

  /**
   * <p>
   *  To be called from the Activity passed as parameter to PermissionManager's constructor.
   *  Simple pass all parameters passed to Activity's onRequestPermissionsResult() to this method.
   * </p>
   *
   * <p>
   *  Responses to permission requests are sent to an Activity, so there's no other way then doing it that cumbersome.
   * </p>
   */
  void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

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
  void checkPermission(String permission, int rationaleToShowToUserResourceId, PermissionRequestCallback callback);

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
  void checkPermission(String permission, String rationaleToShowToUser, PermissionRequestCallback callback);

  /**
   *  Checks if a permission is already granted.
   *
   * @param permission A value from {@link Manifest.permission}
   * @return
   */
  boolean isPermissionGranted(String permission);

  void requestPermission(String permission, String rationaleToShowToUser, PermissionRequestCallback callback);

}
