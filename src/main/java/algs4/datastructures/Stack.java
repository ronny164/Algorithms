package algs4.datastructures;

/**
 * @author Ronny A. Pena
 */
public class Stack<T> extends DoublyLinkedList<T> {

  public void push(T val) {
    super.addFirst(val);
  }

  public T pop() {
    return super.removeFirst();
  }
}