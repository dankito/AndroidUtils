package net.dankito.android.util;

import android.app.Activity;
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
    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    builder = builder.setMessage(message);

    if(alertTitle != null) {
      builder = builder.setTitle(alertTitle);
    }

    builder.setNegativeButton(android.R.string.ok, null);

    builder.create().show();
  }

}
