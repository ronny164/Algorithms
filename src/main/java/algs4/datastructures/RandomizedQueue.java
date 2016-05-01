package algs4.datastructures;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * @author Ronny A. Pena
 *
 * @param <T> The type of object to store.
 */
public class RandomizedQueue<T> implements Iterable<T> {

  private T[] queue;
  private int head;
  private int tail;

  private static class CustomIterator<T> implements Iterator<T> {
    private T[] queue;
    private int[] indexes;
    private int index;
    private int size;

    public CustomIterator(T[] queue, int head, int tail) {
      this.queue = queue;
      this.size = tail - head;
      indexes = new int[size];
      for (int k = 0, j = head; j < tail; k++, j++) {
        indexes[k] = j;
      }
      StdRandom.shuffle(indexes);
    }

    @Override
    public boolean hasNext() {
      return index < size;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new java.util.NoSuchElementException();
      }
      return queue[indexes[index++]];
    }

    @Override
    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }
  }

  public RandomizedQueue() { // construct an empty randomized queue
    this.queue = create(4);
  }

  private T[] create(int size) {
    return (T[]) new Object[size];
  }

  public boolean isEmpty() { // is the queue empty?
    return head == tail;
  }

  /**
   * @return The number of items on the queue.
   */
  public int size() {
    if (head == tail) {
      return 0;
    }
    return tail - head;
  }

  /**
   * Add the item.
   */
  public void enqueue(T item) {
    if (item == null) {
      throw new java.lang.NullPointerException();
    }
    resizeIfNeeded();
    queue[tail++] = item;
  }

  /**
   * Remove and return a random item. 
   */
  public T dequeue() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    }

    resizeIfNeeded();
    int randomIndex = randomIndex();
    T entry = queue[randomIndex];
    queue[randomIndex] = null;
    swap(queue, randomIndex, head);
    head++;
    return entry;
  }

  private int randomIndex() {
    return StdRandom.uniform(size()) + head;
  }

  private static void swap(Object[] array, int i, int j) {
    if (i != j) {
      Object temp = array[i];
      array[i] = array[j];
      array[j] = temp;
    }
  }

  private void resizeIfNeeded() {
    int capacity = queue.length;
    int size = size();
    if (tail + 1 >= capacity || size >= capacity) {
      moveEntries(create(capacity * 2));
    } else if (size <= capacity / 4 && capacity > 4) {
      moveEntries(create(capacity / 2));
    }
  }

  private void moveEntries(T[] newQueue) {

    int newQueueIndex = 0;
    for (int i = head; i <= tail; i++) {
      newQueue[newQueueIndex++] = queue[i];
    }
    int len = newQueueIndex;
    queue = newQueue;
    tail = len - 1;
    head = 0;
  }

  /**
   * @return return (but do not remove) a random item.
   */
  public T sample() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    int randomIndex = randomIndex();
    return queue[randomIndex];
  }

  /**
   * @return an independent iterator over items in random order.
   */
  @Override
  public Iterator<T> iterator() {
    return new CustomIterator<>(queue, head, tail);
  }
}
