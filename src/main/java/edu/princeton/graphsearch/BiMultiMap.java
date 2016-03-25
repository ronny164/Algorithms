package edu.princeton.graphsearch;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Data structure for maintaining many-to-many relationships.
 * Bidirectional map with Multi-valued support.
 *
 * @author Ronny A. Pena
 *
 * @param <K> The key type.
 * @param <V> The value type.
 */
public class BiMultiMap<K, V> {

  private Map<K, Collection<V>> keyValueMap = new HashMap<>();
  private Map<V, Collection<K>> valueKeyMap = new HashMap<>();

  public void put(K key, V val) {
    Collection<V> values = keyValueMap.get(key);
    Collection<K> keys = valueKeyMap.get(val);
    if (keys == null) {
      keys = new LinkedList<>();
      valueKeyMap.put(val, keys);
    }
    if (values == null) {
      values = new LinkedList<>();
      keyValueMap.put(key, values);
    }
    values.add(val);
    keys.add(key);
  }

  public Collection<V> getValues(K key) {
    return keyValueMap.get(key);
  }

  public Collection<K> getKeys(V val) {
    return valueKeyMap.get(val);
  }

  public Iterable<K> keySet() {
    return keyValueMap.keySet();
  }

  public boolean containsKey(K key) {
    return keyValueMap.containsKey(key);
  }

  public int size() {
    return keyValueMap.size();
  }
}
