package net.dankito.android.util.services;

import android.telephony.SmsManager;

import java.util.ArrayList;


public class SmsService {

  public void sendTextSms(String receiverPhoneNumber, String messageText) {
    SmsManager smsManager = SmsManager.getDefault();

    ArrayList<String> singleMessageTexts = smsManager.divideMessage(messageText);

    for(String singleMessageText : singleMessageTexts) {
      smsManager.sendTextMessage(receiverPhoneNumber, null, singleMessageText, null, null);
    }
  }

}
