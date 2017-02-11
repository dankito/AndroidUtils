package net.dankito.utils.services;


import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashService {

  protected static final Charset DIGEST_CHAR_SET = Charset.forName("UTF-8");


  public String hashString(HashAlgorithm hashAlgorithm, String stringToHash) throws NoSuchAlgorithmException {
    MessageDigest messageDigest = MessageDigest.getInstance(hashAlgorithm.getAlgorithmName());
    messageDigest.update(stringToHash.getBytes(DIGEST_CHAR_SET));

    final byte byteData[] = messageDigest.digest();
    return new String(byteData, DIGEST_CHAR_SET);
  }

}
