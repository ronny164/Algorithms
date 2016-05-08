package algs4.datastructures;


public class Stack<T> extends DoublyLinkedList<T> {

  public void push(T val) {
    super.addFirst(val);
  }

  public T pop() {
    return super.removeFirst();
  }
}