package algs4.datastructures;

import java.util.Iterator;

/**
 * @author Ronny A. Pena
 *
 * @param <T> The type of object to store.
 */
public class DoublyLinkedList<T> implements Iterable<T> {

  private static class Node<T> {
    protected T val;
    private Node<T> next;
    private Node<T> previous;

    public Node(Node<T> previous, T item, Node<T> next) {
      this.val = item;
      this.previous = previous;
      this.next = next;
    }
  }

  private static class ListIterator<T> implements Iterator<T> {
    private Node<T> current;
    private int index;
    private int size;

    public ListIterator(Node<T> first, int size) {
      this.current = first;
      this.size = size;
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
      T val = this.current.val;
      this.current = this.current.next;
      index++;
      return val;
    }
  }
  private Node<T> tail;
  private Node<T> head;
  private int size;

  public DoublyLinkedList() { // construct an empty deque
  }

  public boolean isEmpty() { // is the deque empty?
    return size == 0;
  }

  public int size() { // return the number of items on the deque
    return size;
  }

  public void addFirst(T item) { // add the item to the front
    if (item == null) {
      throw new java.lang.NullPointerException();
    }

    Node<T> newFirst = new Node<>(null, item, head);
    if (size == 0) {
      addInitial(newFirst);
    } else {
      head.previous = newFirst;
      head = newFirst;
    }
    size++;
  }

  public void addLast(T item) { // add the item to the end
    if (item == null) {
      throw new java.lang.NullPointerException();
    }

    Node<T> newLast = new Node<>(tail, item, null);
    if (size == 0) {
      addInitial(newLast);
    } else {
      tail.next = newLast;
      tail = newLast;
    }
    size++;
  }

  private void addInitial(Node<T> newItem) {
    tail = newItem;
    head = newItem;
    head.next = tail;
    tail.previous = head;
  }

  private void removeInitial() {
    tail.next = null;
    head.previous = null;
    head = null;
    tail = null;
  }

  public T removeFirst() { // remove and return the item from the front
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    T val = null;
    if (size == 1) {
      val = head.val;
      removeInitial();
    } else {
      Node<T> oldfirst = head;
      val = oldfirst.val;
      head = oldfirst.next;
      head.previous = null;
      oldfirst.next = null;
      oldfirst.previous = null;
    }
    size--;
    return val;
  }

  public T removeLast() { // remove and return the item from the end
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    T val = null;
    if (size == 1) {
      val = tail.val;
      removeInitial();
    } else {
      Node<T> oldlast = tail;
      val = oldlast.val;
      tail = oldlast.previous;
      tail.next = null;
      oldlast.previous = null;
      oldlast.next = null;
    }
    size--;
    return val;
  }
  
  protected T first() {
    return head.val;
  }

  @Override
  public Iterator<T> iterator() { // return an iterator over items in order from front to end
    return new ListIterator<>(head, size);
  }
}
