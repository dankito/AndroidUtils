package net.dankito.utils.services;


public class HexConverter {

  protected static final char[] hexArray = "0123456789ABCDEF".toCharArray();


  public byte[] hexStringToByteArray(String hexString) {
    byte[] bytes = new byte[hexString.length() / 2];

    for (int i = 0; i < bytes.length; i++) {
      int index = i * 2;
      int v = Integer.parseInt(hexString.substring(index, index + 2), 16);
      bytes[i] = (byte) v;
    }

    return bytes;
  }

  public String byteArrayToHexStringViaStringFormat(byte[] bytes) {
    StringBuilder stringBuilder = new StringBuilder();

    for (byte singleByte : bytes) {
      stringBuilder.append(String.format("%02X", singleByte));
    }

    return stringBuilder.toString();
  }

  public static String byteArrayToHexString(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];

    for ( int j = 0; j < bytes.length; j++ ) {
      int v = bytes[j] & 0xFF;
      hexChars[j * 2] = hexArray[v >>> 4];
      hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    }

    return new String(hexChars);
  }

}
