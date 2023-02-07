package com.github.rmkane.tools.util;

import static com.github.rmkane.tools.util.CollectionUtils.groupBy;
import static com.github.rmkane.tools.util.CollectionUtils.immutableMapOf;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectionUtilsTest {

  private static final Logger logger = LoggerFactory.getLogger(CollectionUtilsTest.class);

  private static final List<Map<String, String>> data =
      asList(
          immutableMapOf("Username", "Batman", "Role", "Leader"),
          immutableMapOf("Username", "Robin", "Role", "Subordinate"),
          immutableMapOf("Username", "Superman", "Role", "Leader"));

  @Test
  public void testGroupBy() {
    logger.info("Test groupBy(Collection<E>, Function<E, K>, Function<E, V>)");
    Map<String, List<String>> grouped = groupBy(data, m -> m.get("Role"), m -> m.get("Username"));

    logger.info("Checking keys...");
    assertNotNull(grouped.get("Leader"));
    assertNotNull(grouped.get("Subordinate"));

    logger.info("Checking values...");
    assertEquals("Batman", grouped.get("Leader").get(0));
    assertEquals("Superman", grouped.get("Leader").get(1));
    assertEquals("Robin", grouped.get("Subordinate").get(0));
  }

  @Test
  public void testGroupBySimple() {
    logger.info("Test groupBy(Collection<E>, Function<E, K>)");
    Map<String, List<Map<String, String>>> grouped = groupBy(data, m -> m.get("Role"));

    logger.info("Checking keys...");
    assertNotNull(grouped.get("Leader"));
    assertNotNull(grouped.get("Subordinate"));

    logger.info("Checking values...");
    assertEquals("Batman", grouped.get("Leader").get(0).get("Username"));
    assertEquals("Superman", grouped.get("Leader").get(1).get("Username"));
    assertEquals("Robin", grouped.get("Subordinate").get(0).get("Username"));
  }
}
