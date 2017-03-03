package net.dankito.utils.services;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class HexConverterTest {

  protected static final String HEX_STRING = "0E12FF78D4";

  protected static final byte[] BYTE_ARRAY = new byte[] { 14, 18, -1, 120, -44 };

  protected static final String INVALID_HEX_STRING = "0G12FF:7H";


  protected HexConverter underTest;


  @Before
  public void setUp() throws Exception {
    underTest = new HexConverter();
  }


  @Test
  public void hexStringToByteArray() throws Exception {
    assertThat(underTest.hexStringToByteArray(HEX_STRING), is(BYTE_ARRAY));
  }

  @Test
  public void byteArrayToHexStringViaStringFormat() throws Exception {
    assertThat(underTest.byteArrayToHexStringViaStringFormat(BYTE_ARRAY), is(HEX_STRING));
  }

  @Test
  public void byteArrayToHexString() throws Exception {
    assertThat(underTest.byteArrayToHexString(BYTE_ARRAY), is(HEX_STRING));
  }

  @Test(expected = Exception.class)
  public void invalidHexString_hexStringToByteArray() throws Exception {
    assertThat(underTest.hexStringToByteArray(INVALID_HEX_STRING), is(BYTE_ARRAY));
  }

}