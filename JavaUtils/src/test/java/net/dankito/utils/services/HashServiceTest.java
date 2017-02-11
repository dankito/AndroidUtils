package net.dankito.utils.services;

import org.junit.Before;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class HashServiceTest {

  protected static final String STRING_TO_HASH = "Liebe ist das schönste auf der Welt";


  protected HashService underTest;


  @Before
  public void setUp() {
    underTest = new HashService();
  }


  @Test
  public void hashString() throws Exception {
    for(HashAlgorithm algorithm : HashAlgorithm.values()) {
      testHashAlgorithm(algorithm);
    }
  }

  protected void testHashAlgorithm(HashAlgorithm algorithm) throws NoSuchAlgorithmException {
    String actual = underTest.hashString(algorithm, STRING_TO_HASH);
    String expected = createExpectedHash(algorithm);

    assertThat(actual, is(expected));
  }

  private String createExpectedHash(HashAlgorithm algorithm) throws NoSuchAlgorithmException {
    MessageDigest messageDigest = MessageDigest.getInstance(algorithm.getAlgorithmName());
    messageDigest.update(STRING_TO_HASH.getBytes());

    byte[] hashBytes = messageDigest.digest();

    return new String(hashBytes);
  }

}