package com.github.rmkane.tools.util;

import static java.util.Collections.unmodifiableMap;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class CollectionUtils {
  public static <E> Optional<E> find(Collection<E> collection, Predicate<E> predicate) {
    return collection.stream().filter(predicate).findFirst();
  }

  public static <E, K, V> Map<K, List<V>> groupBy(
      Collection<E> collection, Function<E, K> keyFn, Function<E, V> valueFn) {
    return collection.stream()
        .map(item -> new SimpleEntry<K, V>(keyFn.apply(item), valueFn.apply(item)))
        .collect(groupingBy(Entry::getKey, mapping(Entry::getValue, toList())));
  }

  public static <E, K> Map<K, List<E>> groupBy(Collection<E> collection, Function<E, K> keyFn) {
    return groupBy(collection, keyFn, Function.identity());
  }

  public static <K, V> boolean isMapValid(Map<K, V> map, Predicate<V> predicate) {
    if (map == null || map.isEmpty()) return false;
    return map.entrySet().stream().map(Entry::getValue).allMatch(predicate);
  }

  public static <K, V> Map<K, V> immutableMapOf(K k, V v) {
    return Collections.singletonMap(k, v);
  }

  public static <K, V> Map<K, V> immutableMapOf(K k1, V v1, K k2, V v2) {
    Map<K, V> mutableMap = new HashMap<>();
    mutableMap.put(k1, v1);
    mutableMap.put(k2, v2);
    return unmodifiableMap(mutableMap);
  }
}
