package algs4.datastructures;

public class Hashtable<K, V> {
  
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
  }

  private static final int THRESHOLD = 16;
  private Node<K, V>[] table;
  private int capacity = THRESHOLD;
  private int size = THRESHOLD;

  public Hashtable() {
    table =  new Node[THRESHOLD];
  }

  public Hashtable(int initCapacity) {
    if (initCapacity < 0) {
      throw new IllegalArgumentException();
    }
    table = new Node[initCapacity];
  }

  private void amortized(int newSize) {
    if (newSize > capacity) {
      capacity *= 2;
      move();
    } else if (newSize <= (capacity / 4) && newSize > THRESHOLD) {
      capacity /= 2;
      move();
    }
  }

  private void move() {
    Node[] newArr = new Node[capacity];
    System.arraycopy(table, 0, newArr, 0, size);
    this.table = newArr;
  }

  public void put(K key, V val) {
    int bucket = getBucket(key);
    boolean newlyAdded = add(bucket, key, val);
    if (newlyAdded) {
      amortized(size + 1);
      size++;
    }
  }

  private int getBucket(K key) {
    int hashcode = key.hashCode();
    return hashcode % capacity;
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
}
