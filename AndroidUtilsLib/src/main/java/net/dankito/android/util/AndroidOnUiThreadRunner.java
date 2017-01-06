package net.dankito.android.util;

import android.app.Activity;

import net.dankito.utils.IOnUiThreadRunner;

/**
 * Created by ganymed on 03/11/16.
 */

public class AndroidOnUiThreadRunner implements IOnUiThreadRunner {

  protected Activity activity;


  public AndroidOnUiThreadRunner(Activity activity) {
    this.activity = activity;
  }


  @Override
  public void runOnUiThread(Runnable task) {
    activity.runOnUiThread(task);
  }

}
