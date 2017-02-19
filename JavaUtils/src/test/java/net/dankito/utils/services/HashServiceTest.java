package net.dankito.utils.services;

import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class HashServiceTest {

  protected static final String STRING_TO_HASH = "Liebe ist das schönste auf der Welt";

  protected static final String MD5_CORRECT_HASH = "52e698a2d77e4a3fda000f374de3e0aa";

  protected static final String SHA_1_CORRECT_HASH = "de92975cdc46118e9f4257ff226d34ba68bc2b9d";

  protected static final String SHA_224_CORRECT_HASH = "de3041e07bdb05b441426a68bc9353b5e95a0007c92b7128060c5ce4";

  protected static final String SHA_256_CORRECT_HASH = "92cb473111fd1da422b09850e575b706be9ca9ed751ee9148f1eab6373155b5a";

  protected static final String SHA_384_CORRECT_HASH = "99f281ba2df32fc6ae8a40ab9f534a3e77d2e8b476669eb518bf9f14d82925c291beb13092cb1bdea76b0463cced9f71";

  protected static final String SHA_512_CORRECT_HASH = "e262352dd0376a7204cdab95d4a92966281867a1f9a9f8be96987454e908a9d71b195e415f1d43492479c176777568a200300a0f8909d21ec5a00bbbeb2c8dfd";

  protected static final Charset STRING_CHARSET = Charset.forName("UTF-8");


  protected HashService underTest;

  protected HexConverter hexConverter;


  @Before
  public void setUp() {
    underTest = new HashService();

    hexConverter = new HexConverter();
  }


  @Test
  public void hashString() throws Exception {
    for(HashAlgorithm algorithm : HashAlgorithm.values()) {
      testHashAlgorithm(algorithm);
    }
  }

  protected void testHashAlgorithm(HashAlgorithm algorithm) throws NoSuchAlgorithmException {
    String actual = underTest.hashString(algorithm, STRING_TO_HASH);
    String expected = getExpectedHash(algorithm);

    assertThat(actual, is(expected));
  }

  protected String getExpectedHash(HashAlgorithm algorithm) throws NoSuchAlgorithmException {
    return new String(getExpectedBytesHash(algorithm), STRING_CHARSET);
  }


  @Test
  public void hashStringToBytes() throws Exception {
    for(HashAlgorithm algorithm : HashAlgorithm.values()) {
      testHashAlgorithmBytes(algorithm);
    }
  }

  protected void testHashAlgorithmBytes(HashAlgorithm algorithm) throws NoSuchAlgorithmException {
    byte[] actual = underTest.hashStringToBytes(algorithm, STRING_TO_HASH);
    byte[] expected = getExpectedBytesHash(algorithm);

    assertThat(actual, is(expected));
  }

  protected byte[] getExpectedBytesHash(HashAlgorithm algorithm) throws NoSuchAlgorithmException {
    switch(algorithm) {
      case MD5:
        return hexConverter.hexStringToByteArray(MD5_CORRECT_HASH);
      case SHA1:
        return hexConverter.hexStringToByteArray(SHA_1_CORRECT_HASH);
      case SHA224:
        return hexConverter.hexStringToByteArray(SHA_224_CORRECT_HASH);
      case SHA256:
        return hexConverter.hexStringToByteArray(SHA_256_CORRECT_HASH);
      case SHA384:
        return hexConverter.hexStringToByteArray(SHA_384_CORRECT_HASH);
      case SHA512:
        return hexConverter.hexStringToByteArray(SHA_512_CORRECT_HASH);
      default:
        return new byte[0];
    }
  }


}