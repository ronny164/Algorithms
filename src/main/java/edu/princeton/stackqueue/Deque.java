package edu.princeton.stackqueue;

import java.util.Iterator;

/**
 * @author Ronny A. Pena
 *
 * @param <T> The type of object to store.
 */
public class Deque<T> implements Iterable<T> {

  private Node<T> head;
  private Node<T> tail;
  private int size;

  private static class Node<T> {

    private T val;
    private Node<T> next; 
    private Node<T> previous;

    public Node(T item) {
      this.val = item;
    }

    public T getVal() {
      return val;
    }

    public Node<T> getNext() {
      return next;
    }

    public void setNext(Node<T> next) {
      this.next = next;
    }

    public Node<T> getPrevious() {
      return previous;
    }

    public void setPrevious(Node<T> previous) {
      this.previous = previous;
    }

    @Override
    public String toString() {
      T nextVal = null;
      T previousVal = null;
      if (next != null) {
        nextVal = next.getVal();
      }
      if (previous != null) {
        previousVal = previous.getVal();
      }

      return previousVal + " <- " + this.getVal().toString() + " -> " + nextVal;
    }

  }

  private class DequeueIterator<T> implements Iterator<T> {

    private Node<T> current;
    private int index;
    private int size;

    public DequeueIterator(Node<T> first, int size) {
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
      T val = this.current.getVal();
      this.current = this.current.getNext();
      index++;
      return val;
    }

    @Override
    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }
  }

  public Deque() { // construct an empty deque
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

    Node<T> newFirst = new Node<>(item);
    if (size == 0) {
      addInitial(newFirst);
    } else {
      head.setPrevious(newFirst);
      newFirst.setNext(head);
      head = newFirst;
    }
    size++;
  }

  public void addLast(T item) { // add the item to the end
    if (item == null) {
      throw new java.lang.NullPointerException();
    }

    Node<T> newLast = new Node<>(item);
    if (size == 0) {
      addInitial(newLast);
    } else {
      tail.setNext(newLast);
      newLast.setPrevious(tail);
      tail = newLast;
    }
    size++;
  }

  private void addInitial(Node<T> newItem) {
    tail = newItem;
    head = newItem;
    head.setNext(tail);
    tail.setPrevious(head);
  }

  private void removeInitial() {
    tail.setNext(null);
    head.setPrevious(null);
    head = null;
    tail = null;
  }

  public T removeFirst() { // remove and return the item from the front
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    T val = null;
    if (size == 1) {
      val = head.getVal();
      removeInitial();
    } else {
      Node<T> oldfirst = head;
      val = oldfirst.getVal();
      head = oldfirst.getNext();
      head.setPrevious(null);
      oldfirst.setNext(null);
      oldfirst.setPrevious(null);
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
      val = tail.getVal();
      removeInitial();
    } else {
      Node<T> oldlast = tail;
      val = oldlast.getVal();
      tail = oldlast.getPrevious();
      tail.setNext(null);
      oldlast.setPrevious(null);
      oldlast.setNext(null);
    }
    size--;
    return val;
  }

  @Override
  public Iterator<T> iterator() { // return an iterator over items in order from front to end
    return new DequeueIterator<>(head, size);
  }
}
