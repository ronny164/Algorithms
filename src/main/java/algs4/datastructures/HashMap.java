package algs4.datastructures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ronny A. Pena
 *
 * @param <K> The key type.
 * @param <V> The value type.
 */
public class HashMap<K, V> {
  
  private static final int THRESHOLD = 16;
  private Node<K, V>[] table;
  private int capacity = THRESHOLD;
  private int size;

  public HashMap() {
    table = new Node[THRESHOLD];
  }

  public HashMap(int initCapacity) {
    if (initCapacity < 0) {
      throw new IllegalArgumentException();
    }
    this.capacity = initCapacity;
    this.table = new Node[initCapacity];
  }

  private void resize(int newCapacity) {
    HashMap<K, V> temp = new HashMap<>(newCapacity);
    for (K key : this.keySet()) {
      temp.put(key, this.get(key));
    }
    this.capacity = newCapacity;
    this.table = temp.table;
  }

  public void put(K key, V val) {
    if (val == null) {
      remove(key);
    } else {
      int bucket = getBucket(key);
      boolean newlyAdded = add(bucket, key, val);
      if (newlyAdded) {
        amortized(size + 1);
        size++;
      }
    }
  }

  public void remove(K key) {
    int bucket = getBucket(key);
    Node<K, V> current = table[bucket];
    if (current == null) {
      return;
    }
    if (current.next == null) {
      table[bucket] = null;
    } else {
      while (current.next != null) {
        if (current.key.equals(key)) {
          current.next = current.next.next;
          break;
        }
        current = current.next;
      }
    }
    size--;
  }

  private int getBucket(K key) {
    return (key.hashCode() & 0x7fffffff) % capacity;
  }

  private boolean add(int bucket, K key, V val) {
    Node<K, V> current = table[bucket];
    if (current == null) {
      current = new Node<>(key, val, null);
      table[bucket] = current;
      return true;
    }

    while (current.next != null) {
      if (current.key.equals(key)) {
        current.val = val;
        return false;
      }
      current = current.next;
    }
    current.next = new Node<>(key, val, null);
    return true;
  }

  public V get(K key) {
    int bucket = getBucket(key);
    Node<K, V> node = table[bucket];
    while (node != null) {
      if (node.key.equals(key)) {
        return node.val;
      }
      node = node.next;
    }
    return null;
  }

  public boolean containsKey(K key) {
    return get(key) != null;
  }

  public void clear() {
    Arrays.fill(table, null);
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public Iterable<K> keySet() {
    return () -> new CustomIterator<>(table);
  }

  private void amortized(int newSize) {
    if (newSize > capacity) {
      resize(capacity * 2);
    }
    //TODO: reduce size when size is at 1/4 of the capacity
  }
  
  private static final class Node<K, V> {
    private K key;
    private V val;
    private Node<K, V> next;

    public Node(K key, V val, Node<K, V> next) {
      super();
      this.key = key;
      this.val = val;
      this.next = next;
    }

    @Override
    public String toString() {
      return "{" + key + "=" + val + ", hasNext=" + (next != null) + "}";
    }
  }

  private static final class CustomIterator<K> implements Iterator<K> {

    private Node<K, ?>[] table;
    //saving the next element in the hashtable.
    private int index;
    private Node<K, ?> cached;

    public CustomIterator(Node<K, ?>[] table) {
      this.table = table;
    }

    @Override
    public boolean hasNext() {
      // cache the next value.
      if (cached == null) {
        cached = nextNode();
      }
      return cached != null;
    }

    @Override
    public K next() {
      if (hasNext()) {
        Node<K, ?> local = cached;
        if (local == null) {
          return null;
        }
        // clears the cache if there no more entries;
        cached = cached.next;
        return local.key;
      }
      // users should call hasNext() first.
      throw new NoSuchElementException();
    }

    public Node<K, ?> nextNode() {
      //move to the next item in the link, if already on a linkedList.
      if (cached != null) {
        cached = cached.next;
      }
      // if not on a linkedList, check the table.
      while (index < table.length && cached == null) {
        cached = table[index++];
      }
      return cached;
    }
  }
}
