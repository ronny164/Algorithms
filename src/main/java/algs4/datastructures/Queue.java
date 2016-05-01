package algs4.datastructures;


public class Queue<T> extends DoublyLinkedList<T> {
  
  public void enqueue(T val) {
    super.addLast(val);
  }
  
  public T dequeue() {
    return super.removeFirst();
  }
  
  public T peek() {
    return super.first();
 }
}