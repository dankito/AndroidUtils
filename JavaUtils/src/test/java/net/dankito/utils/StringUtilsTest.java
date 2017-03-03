package net.dankito.utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class StringUtilsTest {

  @Test
  public void null_isNullOrEmptyReturnsTrue() throws Exception {
    assertThat(StringUtils.isNullOrEmpty(null), is(true));
  }

  @Test
  public void emptyString_isNullOrEmptyReturnsTrue() throws Exception {
    assertThat(StringUtils.isNullOrEmpty(""), is(true));
  }

  @Test
  public void notEmptyString_isNullOrEmptyReturnsFalse() throws Exception {
    assertThat(StringUtils.isNullOrEmpty("love"), is(false));
  }


  @Test
  public void null_isNotNullOrEmptyReturnsFalse() throws Exception {
    assertThat(StringUtils.isNotNullOrEmpty(null), is(false));
  }

  @Test
  public void emptyString_isNotNullOrEmptyReturnsFalse() throws Exception {
    assertThat(StringUtils.isNotNullOrEmpty(""), is(false));
  }

  @Test
  public void notEmptyString_isNotNullOrEmptyReturnsTrue() throws Exception {
    assertThat(StringUtils.isNotNullOrEmpty("love"), is(true));
  }

}