package net.dankito.android.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;


public class AlertHelper {

  public static void showMessageThreadSafe(final Activity activity, final String message) {
    showMessageThreadSafe(activity, message, null);
  }

  public static void showMessageThreadSafe(final Activity activity, final String message, final String alertTitle) {
    activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        showMessage(activity, message, alertTitle);
      }
    });
  }

  public static void showMessage(Activity activity, String message) {
    showMessage(activity, message, null);
  }

  public static void showMessage(Activity activity, String message, final String alertTitle) {
    AlertDialog.Builder builder = createDialog(activity, message, alertTitle);

    builder.create().show();
  }


  public static void showConfirmMessage(Activity activity, int infoMessageResId, ShowAlertDialogCallback callback) {
    showConfirmMessage(activity, activity.getString(infoMessageResId), callback);
  }

  public static void showConfirmMessage(Activity activity, int infoMessageResId, int alertTitleResId, ShowAlertDialogCallback callback) {
    showConfirmMessage(activity, activity.getString(infoMessageResId), activity.getString(alertTitleResId), callback);
  }

  public static void showConfirmMessage(Activity activity, CharSequence message, ShowAlertDialogCallback callback) {
    showConfirmMessage(activity, message, null, callback);
  }

  public static void showConfirmMessage(final Activity activity, final CharSequence message, final CharSequence alertTitle, final ShowAlertDialogCallback callback) {
    if(AndroidOnUiThreadRunner.isRunningOnUiThread() == true) {
      showConfirmMessageOnUiThread(activity, message, alertTitle, callback);
    }
    else {
      activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          showConfirmMessageOnUiThread(activity, message, alertTitle, callback);
        }
      });
    }
  }

  protected static void showConfirmMessageOnUiThread(final Activity activity, final CharSequence message, final CharSequence alertTitle, final ShowAlertDialogCallback callback) {
    AlertDialog.Builder builder = createDialog(activity, message, alertTitle);

    builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        callback.optionSelected(false);
      }
    });

    builder.setNegativeButton(android.R.string.yes, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        callback.optionSelected(true);
      }
    });

    builder.create().show();
  }


  @NonNull
  protected static AlertDialog.Builder createDialog(Activity activity, CharSequence message, CharSequence alertTitle) {
    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    if(alertTitle != null) {
      builder = builder.setTitle(alertTitle);
    }
    builder = builder.setMessage(message);
    // TODO: set error icon

    builder.setNegativeButton(android.R.string.ok, null);
    return builder;
  }

}
