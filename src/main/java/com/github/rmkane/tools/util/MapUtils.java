package com.github.rmkane.tools.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MapUtils {
  public static class MapBuilder<K, V> {
    private Map<K, V> map;
    private boolean unmodifiable;

    public MapBuilder() {
      this(false);
    }

    public MapBuilder(boolean unmodifiable) {
      this(HashMap::new, unmodifiable);
    }

    public MapBuilder(Supplier<Map<K, V>> supplier, boolean unmodifiable) {
      this.map = supplier.get();
      this.unmodifiable = unmodifiable;
    }

    public MapBuilder<K, V> put(K key, V val) {
      map.put(key, val);
      return this;
    }

    public MapBuilder<K, V> withUnmodifiable(boolean unmodifiable) {
      this.unmodifiable = unmodifiable;
      return this;
    }

    public Map<K, V> build() {
      return unmodifiable ? Collections.unmodifiableMap(map) : map;
    }
  }

  public static <K, V, M extends Map<K, V>> Map<K, V> initializeMap(
      K key, V value, Supplier<M> sup) {
    Map<K, V> map = sup.get();
    map.put(key, value);
    return map;
  }

  public static <K, V, M extends Map<K, V>> Map<K, V> initializeMap(
      K k1, V v1, K k2, V v2, Supplier<M> sup) {
    Map<K, V> map = sup.get();
    map.put(k1, v1);
    map.put(k2, v2);
    return map;
  }

  public static <K, V, M extends Map<K, V>> Map<K, V> initializeMap(
      K k1, V v1, K k2, V v2, K k3, V v3, Supplier<M> sup) {
    Map<K, V> map = sup.get();
    map.put(k1, v1);
    map.put(k2, v2);
    map.put(k3, v3);
    return map;
  }

  public static <K, V, M extends Map<K, V>> Map<K, V> initializeMap(K key, V value) {
    return initializeMap(key, value, HashMap::new);
  }

  public static <K, V, M extends Map<K, V>> Map<K, V> initializeMap(K k1, V v1, K k2, V v2) {
    return initializeMap(k1, v1, k2, v2, HashMap::new);
  }

  public static <K, V, M extends Map<K, V>> Map<K, V> initializeMap(
      K k1, V v1, K k2, V v2, K k3, V v3) {
    return initializeMap(k1, v1, k2, v2, k3, v3, HashMap::new);
  }

  public static void main(String[] args) {
    Map<String, String> map1 = initializeMap("foo", "bar");
    System.out.println(map1);

    Map<Integer, String> map2 = initializeMap(1, "one", 2, "two");
    System.out.println(map2);

    Map<Character, Float> map3 =
        new MapBuilder<Character, Float>(true).put('R', 1.0f).put('G', 0.0f).put('B', 0.0f).build();

    System.out.println(map3);
  }
}
