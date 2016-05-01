package edu.princeton.stackqueue;

import edu.princeton.list.DoublyLinkedList;

public class Stack<T> extends DoublyLinkedList<T> {
  
  public void push(T val) {
    super.addLast(val);
  }
  
  public T pop() {
    return super.removeLast();
  }
}