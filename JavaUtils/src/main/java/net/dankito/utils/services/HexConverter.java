package net.dankito.utils.services;


public class HexConverter {

  public byte[] hexStringToByteArray(String hexString) {
    byte[] bytes = new byte[hexString.length() / 2];

    for (int i = 0; i < bytes.length; i++) {
      int index = i * 2;
      int v = Integer.parseInt(hexString.substring(index, index + 2), 16);
      bytes[i] = (byte) v;
    }

    return bytes;
  }

  public String byteArrayToHexString(byte[] bytes) {
    StringBuilder stringBuilder = new StringBuilder();

    for (byte singleByte : bytes) {
      stringBuilder.append(String.format("%02X ", singleByte));
    }

    return stringBuilder.toString();
  }

}
