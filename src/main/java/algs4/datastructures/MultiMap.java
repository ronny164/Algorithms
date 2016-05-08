package algs4.datastructures;

import java.util.Collection;

/**
 * Data structure for Multi-valued support. Useful for representing an adjacency list.
 *
 * @author Ronny A. Pena
 *
 * @param <K> The key type.
 * @param <V> The value type.
 */
public class MultiMap<K, V> {

  private HashMap<K, DoublyLinkedList<V>> keyValueMap = new HashMap<>();

  private DoublyLinkedList<V> get(K key) {
    DoublyLinkedList<V> values = keyValueMap.get(key);
    if (values == null) {
      values = new DoublyLinkedList<>();
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

  public Iterable<V> getValues(K key) {
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
