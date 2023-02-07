package com.github.rmkane.tools.util;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

public class CollectionUtils {
  public static <E, K, V> Map<K, List<V>> groupBy(
      Collection<E> collection, Function<E, K> keyFn, Function<E, V> valueFn) {
    return collection.stream()
        .map(item -> new SimpleEntry<K, V>(keyFn.apply(item), valueFn.apply(item)))
        .collect(groupingBy(Entry::getKey, mapping(Entry::getValue, toList())));
  }

  public static <K, V> boolean isMapValid(Map<K, V> map, Function<V, Boolean> validatorFn) {
    if (map == null) return false;
    if (map.size() < 1) return false;
    Iterator<Entry<K, V>> it = map.entrySet().iterator();
    while (it.hasNext()) {
      if (!validatorFn.apply(it.next().getValue())) {
        return false;
      }
    }
    return true;
  }
}
