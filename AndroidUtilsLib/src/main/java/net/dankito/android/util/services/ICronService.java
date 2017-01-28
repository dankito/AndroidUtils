package net.dankito.android.util.services;

import android.content.BroadcastReceiver;

import net.dankito.android.util.model.OneTimeJobConfig;

import java.util.Calendar;

/**
 * Created by ganymed on 26/11/16.
 */

public interface ICronService {

  int scheduleOneTimeJob(OneTimeJobConfig config);

  int startPeriodicalJob(long periodicalCheckTimeMillis, Class<? extends BroadcastReceiver> classThatReceivesBroadcastWhenPeriodElapsed);

  int startPeriodicalJob(Calendar startTime, long intervalMillis, Class<? extends BroadcastReceiver> classThatReceivesBroadcastWhenPeriodElapsed);

  boolean cancelPeriodicalJob(int cronJobTokenNumber);

}
