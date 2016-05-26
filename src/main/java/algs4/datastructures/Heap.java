package algs4.datastructures;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * We have some data we need to store/add/remove as we go 
 * and it needs to be in a sorted (prioritized) way.
 * 
 * For example, we need to keep track of the top 5 or bottom 5 elements.
 * 
 * @author Ronny A. Pena
 */
public class Heap<T extends Comparable<T>> implements Iterable<T> {
  private int size;
  private T[] arr;
  private Comparator<T> cmp;

  private void init() {
    this.arr = (T[]) new Comparable[10];
    this.cmp = Collections.reverseOrder(Collections.reverseOrder());
  }

  public Heap() {
    init();
  }

  public Heap(T[] initial) {
    if (initial == null) {
      throw new IllegalArgumentException();
    }
    init();
    for (T t : initial) {
      arr[size++] = t;
    }
    for (int i = size / 2; i >= 0; i--) {
      sink(arr, cmp, i, size - 1);
    }
  }

  public Heap(Comparator<T> cmp) {
    if (cmp == null) {
      throw new IllegalArgumentException();
    }
    init();
    this.cmp = cmp;
  }

  public void add(T val) {
    if (val == null) {
      throw new IllegalArgumentException();
    }
    ensureCapacity(size + 1);
    arr[size] = val; // add to the last
    swim(arr, cmp, size); // and swim it up if necessary.
    size++;
  }

  public T remove() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    T val = arr[0]; // the max or the min should be at the top.
    arr[0] = null; // remove it and save it temporarely,
    swap(arr, 0, size - 1); // move the last one to the top
    sink(arr, cmp, 0, size - 1); // sink the new first if it breaks the heap ordering.
    size--;
    return val;
  }

  /**
   * moves the element in the parent location down tree and promote and left or right child up
   * depending on their values to maintain the heap ordering. 
   */
  public static <T> void sink(T[] arr, Comparator<T> cmp, int parentIdx, int limit) {

    while (2 * parentIdx + 1 <= limit) {
      int leftIdx = 2 * parentIdx + 1;
      int rightIdx = 2 * parentIdx + 2;
      T parent = arr[parentIdx];

      T left = arr[leftIdx];
      if (parent == null || left == null) {
        return;
      }

      if (rightIdx > limit || arr[rightIdx] == null) { // the right child is invalid
        if (cmp.compare(left, parent) < 0) {
          swap(arr, parentIdx, leftIdx);
        }
        return;
      }
      T right = arr[rightIdx];
      // if the heap ordering is correct, exit.
      if (cmp.compare(left, parent) > 0 && cmp.compare(right, parent) > 0) {
        return;
      }

      if (cmp.compare(left, right) < 0) {
        swap(arr, parentIdx, leftIdx);
        parentIdx = leftIdx;
      } else {
        swap(arr, parentIdx, rightIdx);
        parentIdx = rightIdx;
      }
    }
  }

  /**
   * moves the element in the child location up in the tree
   * and maintains the heap invariant (heap ordering).
   */
  public static <T> void swim(T[] arr, Comparator<T> cmp, int childIdx) {
    while (childIdx > 0) {
      int parentIdx = (childIdx - 1) / 2;
      T child = arr[childIdx];
      T parent = arr[parentIdx];
      if (child == null || parent == null || cmp.compare(parent, child) < 0) {
        return;
      }
      swap(arr, childIdx, parentIdx);
      childIdx = parentIdx; // the current parent becomes the child
    }
  }

  public T peek() {
    return arr[0];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public static <T> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  private void ensureCapacity(int newSize) {
    int n = arr.length;
    if (newSize >= n) {
      T[] temp = (T[]) new Comparable[n * 2];
      System.arraycopy(arr, 0, temp, 0, n);
      arr = temp;
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new CustomIterator<T>(arr);
  }
  private static final class CustomIterator<T> implements Iterator<T>{
    private T[] arr;
    public CustomIterator(T[] arr2) {
      this.arr = arr2;
    }
    @Override
    public boolean hasNext() {
      return false;
    }
    @Override
    public T next() {
      return null;
    }
  }
}