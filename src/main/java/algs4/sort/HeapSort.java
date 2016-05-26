package algs4.sort;

import algs4.datastructures.Heap;

import java.util.Collections;
import java.util.Comparator;

/**
 * @author Ronny A. Pena
 */
public class HeapSort {

  /**
   * In-place, unstable, 2(n log n) runtime. 
   */
  public static <T> void sort(T[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    int n = arr.length;
    Comparator<T> maxCmp = Collections.reverseOrder();
    
    // create a max heap
    for (int i = 1; i < n; i++) { // heapfy the arrax to a max heap.
      Heap.swim(arr, maxCmp, i );
    }

    for (int i = n - 1; i >= 0; i--) {
      // move the max element to the end, 
      // replace it with the current value at the current location
      // put that value at the root
      Heap.swap(arr, 0, i);
      // now sink the new root
      Heap.sink(arr, maxCmp, 0, i - 1);
    }
  }
}