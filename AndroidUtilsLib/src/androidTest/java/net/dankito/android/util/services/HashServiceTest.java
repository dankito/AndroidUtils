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
  public void hashString() throws Exception {
    hashServiceTest.hashString();
  }


  @Test
  public void hashStringToBytes() throws Exception {
    hashServiceTest.hashStringToBytes();
  }

}