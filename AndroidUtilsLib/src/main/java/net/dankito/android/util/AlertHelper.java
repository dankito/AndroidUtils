package net.dankito.android.util;

import android.app.Activity;
import android.support.v7.app.AlertDialog;

/**
 * Created by ganymed on 14/11/16.
 */

public class AlertHelper {

  public static void showMessageThreadSafe(final Activity activity, final String errorMessage) {
    showMessageThreadSafe(activity, errorMessage, null);
  }

  public static void showMessageThreadSafe(final Activity activity, final String errorMessage, final String alertTitle) {
    activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        showMessage(activity, errorMessage, alertTitle);
      }
    });
  }

  public static void showMessage(Activity activity, String errorMessage) {
    showMessage(activity, errorMessage, null);
  }

  public static void showMessage(Activity activity, String errorMessage, final String alertTitle) {
    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    builder = builder.setMessage(errorMessage);

    if(alertTitle != null) {
      builder = builder.setTitle(alertTitle);
    }

    builder.setNegativeButton(android.R.string.ok, null);

    builder.create().show();
  }

}
