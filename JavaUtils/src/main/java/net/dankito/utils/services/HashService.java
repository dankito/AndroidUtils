package net.dankito.utils.services;


import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashService {

  protected static final Charset DIGEST_CHAR_SET = Charset.forName("UTF-8");


  public String hashString(HashAlgorithm hashAlgorithm, String stringToHash) throws NoSuchAlgorithmException {
    return new String(hashStringToBytes(hashAlgorithm, stringToHash), DIGEST_CHAR_SET);
  }

  public byte[] hashStringToBytes(HashAlgorithm hashAlgorithm, String stringToHash) throws NoSuchAlgorithmException {
    MessageDigest messageDigest = MessageDigest.getInstance(hashAlgorithm.getAlgorithmName());
    byte[] stringToHashBytes = stringToHash.getBytes(DIGEST_CHAR_SET);

    messageDigest.update(stringToHashBytes);

    return messageDigest.digest();
  }

}
