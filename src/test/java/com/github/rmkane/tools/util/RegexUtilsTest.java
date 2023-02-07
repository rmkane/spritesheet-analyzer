package com.github.rmkane.tools.util;

import org.junit.Assert;
import org.junit.Test;

public class RegexUtilsTest {

  @Test
  public void testMatch() {
    Assert.assertEquals("foo", RegexUtils.match("foo_bar", "^[a-z0-9]+"));
    Assert.assertEquals("bar", RegexUtils.match("foo_bar", "[a-z0-9]+$"));
  }
}
