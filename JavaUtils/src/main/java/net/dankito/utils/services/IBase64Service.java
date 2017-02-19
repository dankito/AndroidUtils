package net.dankito.utils.services;


public interface IBase64Service {

  String encode(String stringToEncode);

  String encode(byte[] dataToEncode);

  String decode(String stringToDecode);

  byte[] decodeToBytes(String stringToDecode);

}
