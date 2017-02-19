package net.dankito.android.util.services;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Simply runs Tests of JavaUtils' HashServiceTest on Android
 */
@RunWith(AndroidJUnit4.class)
public class HashServiceTest {

  protected net.dankito.utils.services.HashServiceTest hashServiceTest;


  @Before
  public void setUp() {
    hashServiceTest = new net.dankito.utils.services.HashServiceTest();

    hashServiceTest.setUp();
  }


  @Test
  public void hashString_Ascii() throws Exception {
    hashServiceTest.hashString_Ascii();
  }

  @Test
  public void hashString_Utf8() throws Exception {
    hashServiceTest.hashString_Utf8();
  }


  @Test
  public void hashStringToBytes_Ascii() throws Exception {
    hashServiceTest.hashStringToBytes_Ascii();
  }

  @Test
  public void hashStringToBytes_Utf8() throws Exception {
    hashServiceTest.hashStringToBytes_Utf8();
  }

}