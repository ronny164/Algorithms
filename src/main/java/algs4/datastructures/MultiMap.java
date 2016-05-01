package algs4.datastructures;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Data structure for Multi-valued support. Useful for representing an adjacency list.
 *
 * @author Ronny A. Pena
 *
 * @param <K> The key type.
 * @param <V> The value type.
 */
public class MultiMap<K, V> {

  private Map<K, Collection<V>> keyValueMap = new HashMap<>();

  private Collection<V> get(K key) {
    Collection<V> values = keyValueMap.get(key);
    if (values == null) {
      values = new LinkedList<>();
      keyValueMap.put(key, values);
    }
    return values;
  }

  public void put(K key, V val) {
    get(key).add(val);
  }

  public void put(K key, Collection<V> val) {
    get(key).addAll(val);
  }

  public Collection<V> getValues(K key) {
    return keyValueMap.get(key);
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

  @Override
  public String toString() {
    return "MultiMap [keyValueMap=" + keyValueMap + "]";
  }
}
