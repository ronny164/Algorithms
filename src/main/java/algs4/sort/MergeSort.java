package algs4.sort;

import java.util.Comparator;

/**
 * @author Ronny A. Pena
 */
public class MergeSort {

  /**
   * Stable, O(n + log n) space complexity, O(n log n) time.
   */
  public static <T> void sort(T[] arr, Comparator<T> cmp) {
    if (arr == null || arr.length == 0 || cmp == null) {
      return;
    }
    T[] aux = (T[]) new Object[arr.length];
    sort(arr, 0, arr.length - 1, aux, cmp);
  }

  private static <T> void sort(T[] arr, int start, int end, T[] aux, Comparator<T> cmp) {
    if (end - start < 1) { // sub arrays have to be of size greater than one.
      return;
    }
    int mid = (start + end) / 2;
    sort(arr, start, mid, aux, cmp);
    sort(arr, mid + 1, end, aux, cmp);
    merge(arr, start, mid, mid + 1, end, aux, cmp);
  }

  private static <T> void merge(T[] arr, int leftStart, int leftEnd, int rightStart, int rightEnd, 
      T[] aux, Comparator<T> cmp) {
    int size = rightEnd - leftStart + 1;
    System.arraycopy(arr, leftStart, aux, leftStart, size);
    int left = leftStart;
    int right = rightStart;
    for (int i = leftStart; i <= rightEnd; i++) {
      if (left > leftEnd) { //nothing left on the left partition, use the right partition
        arr[i] = aux[right++];
      } else if (right > rightEnd) { //nothing left on the right partition, use the left partition
        arr[i] = aux[left++];
      } else if (cmp.compare(aux[left], aux[right]) <= 0) { // move all the smaller left items.
        arr[i] = aux[left++];
      } else { // otherwise, move all the smaller right items.
        arr[i] = aux[right++];
      }
    }
  }
}
