package net.dankito.android.util.services;

import android.util.Base64;

import net.dankito.utils.services.IBase64Service;

import java.nio.charset.Charset;


public class AndroidBase64Service implements IBase64Service {

  protected static final Charset DEFAULT_CHAR_SET = Charset.forName("UTF-8");


  @Override
  public String encode(String stringToEncode) {
    return encode(stringToEncode.getBytes(DEFAULT_CHAR_SET));
  }

  @Override
  public String encode(byte[] dataToEncode) {
    return Base64.encodeToString(dataToEncode, Base64.NO_WRAP);
  }

  @Override
  public String decode(String stringToDecode) {
    return new String(decodeToBytes(stringToDecode), DEFAULT_CHAR_SET);
  }

  @Override
  public byte[] decodeToBytes(String stringToDecode) {
    return Base64.decode(stringToDecode, Base64.NO_WRAP);
  }

}
