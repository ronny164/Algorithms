package algs4.sort;

import algs4.datastructures.Heap;

import java.util.Collections;
import java.util.Comparator;

/**
 * @author Ronny A. Pena
 */
public class HeapSort {

  /**
   * unstable, O(1) space, and O(n log n) time, 2(n log n) estimated runtime. 
   */
  public static <T> void sort(T[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    int n = arr.length;
    Comparator<T> maxCmp = Collections.reverseOrder();

    // create a max heap
    // heapfy the arrax to a max heap. 
    // sink all the nodes in the top half.
    for (int i = n / 2; i >= 0; i--) { 
      Heap.sink(arr, maxCmp, i, n - 1);
    }

    int root = 0;
    for (int last = n - 1; last >= 0; last--) {
      // move the max element to the end,
      // replace it with the current value at the current location
      // put that value at the root
      Heap.swap(arr, root, last);
      // now sink the new root
      Heap.sink(arr, maxCmp, root, last - 1);
    }
  }
}