package net.dankito.android.util.model;


import android.content.BroadcastReceiver;

import java.util.Calendar;


public class OneTimeJobConfig {

  protected Calendar startAt;

  protected Class<? extends BroadcastReceiver> classThatReceivesBroadcastWhenPeriodElapsed;


  public OneTimeJobConfig(Calendar startAt, Class<? extends BroadcastReceiver> classThatReceivesBroadcastWhenPeriodElapsed) {
    this.startAt = startAt;
    this.classThatReceivesBroadcastWhenPeriodElapsed = classThatReceivesBroadcastWhenPeriodElapsed;
  }


  public Calendar getStartAt() {
    return startAt;
  }

  public Class<? extends BroadcastReceiver> getClassThatReceivesBroadcastWhenPeriodElapsed() {
    return classThatReceivesBroadcastWhenPeriodElapsed;
  }

}
