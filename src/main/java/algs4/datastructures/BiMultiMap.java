package algs4.datastructures;


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

  private HashMap<K, DoublyLinkedList<V>> keyValueMap = new HashMap<>();
  private HashMap<V, DoublyLinkedList<K>> valueKeyMap = new HashMap<>();

  public void put(K key, V val) {
    DoublyLinkedList<V> values = keyValueMap.get(key);
    DoublyLinkedList<K> keys = valueKeyMap.get(val);
    if (keys == null) {
      keys = new DoublyLinkedList<>();
      valueKeyMap.put(val, keys);
    }
    if (values == null) {
      values = new DoublyLinkedList<>();
      keyValueMap.put(key, values);
    }
    values.add(val);
    keys.add(key);
  }

  public Iterable<V> getValues(K key) {
    return keyValueMap.get(key);
  }

  public Iterable<K> getKeys(V val) {
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
