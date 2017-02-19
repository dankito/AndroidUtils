package net.dankito.utils.services;

import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class HashServiceTest {

  protected static final String UTF8_STRING_TO_HASH = "Liebe ist das schönste auf der Welt";

  protected static final String UTF8_STRING_CORRECT_MD5_HASH = "52e698a2d77e4a3fda000f374de3e0aa";

  protected static final String UTF8_STRING_CORRECT_SHA_1_HASH = "de92975cdc46118e9f4257ff226d34ba68bc2b9d";

  protected static final String UTF8_STRING_CORRECT_SHA_224_HASH = "de3041e07bdb05b441426a68bc9353b5e95a0007c92b7128060c5ce4";

  protected static final String UTF8_STRING_CORRECT_SHA_256_HASH = "92cb473111fd1da422b09850e575b706be9ca9ed751ee9148f1eab6373155b5a";

  protected static final String UTF8_STRING_CORRECT_SHA_384_HASH = "99f281ba2df32fc6ae8a40ab9f534a3e77d2e8b476669eb518bf9f14d82925c291beb13092cb1bdea76b0463cced9f71";

  protected static final String UTF8_STRING_CORRECT_SHA_512_HASH = "e262352dd0376a7204cdab95d4a92966281867a1f9a9f8be96987454e908a9d71b195e415f1d43492479c176777568a200300a0f8909d21ec5a00bbbeb2c8dfd";


  protected static final String ASCII_STRING_TO_HASH = "88f0e0e0-08e5-45df-a994-7b65262c9b2e-033352";

  protected static final String ASCII_STRING_CORRECT_MD5_HASH = "fa482aa66b1329199ec784532b258712";

  protected static final String ASCII_STRING_CORRECT_SHA_1_HASH = "e573b8a906bacbc7d6716107be2335b16552477f";

  protected static final String ASCII_STRING_CORRECT_SHA_224_HASH = "085a15b05376e4b66b9ca594607cde21d677e17171f11a444c6e0031";

  protected static final String ASCII_STRING_CORRECT_SHA_256_HASH = "9892efdf0fcbaadf3060d9386e6ae184005b484dafc1f81adbddf33275ba4547";

  protected static final String ASCII_STRING_CORRECT_SHA_384_HASH = "ca227525a2e54db9750516085cfc746a81cb35540f91436f63fbd9c519815e1684dec0fbbdf9443869c63a6f0ca35277";

  protected static final String ASCII_STRING_CORRECT_SHA_512_HASH = "037119570a6039668270c0524916798bd5cc93cd458df94687aa068255581bc55d3efb1f4d256a6bf524dd24e3d816a9a514c2c183c615565e4729d2de524680";


  protected static final Charset STRING_CHARSET = Charset.forName("UTF-8");


  protected HashService underTest;

  protected HexConverter hexConverter;


  @Before
  public void setUp() {
    underTest = new HashService();

    hexConverter = new HexConverter();
  }


  @Test
  public void hashString_Ascii() throws Exception {
    for(HashAlgorithm algorithm : HashAlgorithm.values()) {
      testHashAlgorithm(algorithm, ASCII_STRING_TO_HASH);
    }
  }

  @Test
  public void hashString_Utf8() throws Exception {
    for(HashAlgorithm algorithm : HashAlgorithm.values()) {
      testHashAlgorithm(algorithm, UTF8_STRING_TO_HASH);
    }
  }

  protected void testHashAlgorithm(HashAlgorithm algorithm, String stringToHash) throws NoSuchAlgorithmException {
    String actual = underTest.hashString(algorithm, stringToHash);
    String expected = getExpectedHash(algorithm, stringToHash);

    assertThat("Created hash for " + algorithm + " " + actual + " does not equal correct one " + expected, actual, is(expected));
  }

  protected String getExpectedHash(HashAlgorithm algorithm, String stringToHash) throws NoSuchAlgorithmException {
    return new String(getExpectedBytesHash(algorithm, stringToHash), STRING_CHARSET);
  }


  @Test
  public void hashStringToBytes_Ascii() throws Exception {
    for(HashAlgorithm algorithm : HashAlgorithm.values()) {
      testHashAlgorithmBytes(algorithm, ASCII_STRING_TO_HASH);
    }
  }

  @Test
  public void hashStringToBytes_Utf8() throws Exception {
    for(HashAlgorithm algorithm : HashAlgorithm.values()) {
      testHashAlgorithmBytes(algorithm, UTF8_STRING_TO_HASH);
    }
  }

  protected void testHashAlgorithmBytes(HashAlgorithm algorithm, String stringToHash) throws NoSuchAlgorithmException {
    byte[] actual = underTest.hashStringToBytes(algorithm, stringToHash);
    byte[] expected = getExpectedBytesHash(algorithm, stringToHash);

    assertThat(actual, is(expected));
  }

  protected byte[] getExpectedBytesHash(HashAlgorithm algorithm, String stringToHash) throws NoSuchAlgorithmException {
    if(UTF8_STRING_TO_HASH.equals(stringToHash)) {
      return getExpectedBytesHashForUtf8String(algorithm);
    }
    else {
      return getExpectedBytesHashForAsciiString(algorithm);
    }
  }

  protected byte[] getExpectedBytesHashForUtf8String(HashAlgorithm algorithm) throws NoSuchAlgorithmException {
    switch(algorithm) {
      case MD5:
        return hexConverter.hexStringToByteArray(UTF8_STRING_CORRECT_MD5_HASH);
      case SHA1:
        return hexConverter.hexStringToByteArray(UTF8_STRING_CORRECT_SHA_1_HASH);
      case SHA224:
        return hexConverter.hexStringToByteArray(UTF8_STRING_CORRECT_SHA_224_HASH);
      case SHA256:
        return hexConverter.hexStringToByteArray(UTF8_STRING_CORRECT_SHA_256_HASH);
      case SHA384:
        return hexConverter.hexStringToByteArray(UTF8_STRING_CORRECT_SHA_384_HASH);
      case SHA512:
        return hexConverter.hexStringToByteArray(UTF8_STRING_CORRECT_SHA_512_HASH);
      default:
        return new byte[0];
    }
  }

  protected byte[] getExpectedBytesHashForAsciiString(HashAlgorithm algorithm) throws NoSuchAlgorithmException {
    switch(algorithm) {
      case MD5:
        return hexConverter.hexStringToByteArray(ASCII_STRING_CORRECT_MD5_HASH);
      case SHA1:
        return hexConverter.hexStringToByteArray(ASCII_STRING_CORRECT_SHA_1_HASH);
      case SHA224:
        return hexConverter.hexStringToByteArray(ASCII_STRING_CORRECT_SHA_224_HASH);
      case SHA256:
        return hexConverter.hexStringToByteArray(ASCII_STRING_CORRECT_SHA_256_HASH);
      case SHA384:
        return hexConverter.hexStringToByteArray(ASCII_STRING_CORRECT_SHA_384_HASH);
      case SHA512:
        return hexConverter.hexStringToByteArray(ASCII_STRING_CORRECT_SHA_512_HASH);
      default:
        return new byte[0];
    }
  }


}