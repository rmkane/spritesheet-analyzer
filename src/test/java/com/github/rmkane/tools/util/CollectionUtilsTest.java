package com.github.rmkane.tools.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.junit.Assert;
import org.junit.Test;

public class CollectionUtilsTest {

  private Function<Map<String, String>, String> getKey =
      map -> map.entrySet().stream().findFirst().get().getKey();

  private Function<Map<String, String>, String> getValue =
      map -> map.entrySet().stream().findFirst().get().getValue();

  @Test
  public void testGroupBy() {
    List<Map<String, String>> listOfData =
        Arrays.asList(
            Collections.singletonMap("A", "1"),
            Collections.singletonMap("A", "2"),
            Collections.singletonMap("B", "3"));

    Map<String, List<String>> grouped = CollectionUtils.groupBy(listOfData, getKey, getValue);

    Assert.assertNotNull(grouped.get("A"));
    Assert.assertNotNull(grouped.get("B"));

    Assert.assertEquals("1", grouped.get("A").get(0));
    Assert.assertEquals("2", grouped.get("A").get(1));
    Assert.assertEquals("3", grouped.get("B").get(0));
  }
}
