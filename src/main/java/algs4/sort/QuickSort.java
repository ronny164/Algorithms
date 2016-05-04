package algs4.sort;

import java.util.Comparator;

public class QuickSort {

  public static <T> T findKth(T[] arr, int k, Comparator<T> cmp) {
    if (arr == null || arr.length == 0 || k < 0 || k >= arr.length || cmp == null) {
      throw new IllegalArgumentException();
    }
    int start = 0;
    int end = arr.length - 1;
    while (start <= end) {
      int pivot = partition(arr, start, end, cmp);
      if (pivot == k) {
        return arr[k];
      } else if (cmp.compare(arr[k], arr[pivot]) < 0) {
        end = pivot - 1;
      } else {
        start = pivot + 1;
      }
    }
    return null;
  }

  public static <T> void sort(T[] arr, Comparator<T> cmp) {
    if (arr == null || arr.length == 0 || cmp == null) {
      throw new IllegalArgumentException();
    }
    sort(arr, 0, arr.length - 1, cmp);
  }

  private static <T> void sort(T[] arr, int start, int end, Comparator<T> cmp) {
    if (start >= end) {
      return;
    }
    int pivot = partition(arr, start, end, cmp);
    sort(arr, start, pivot - 1, cmp);
    sort(arr, pivot + 1, end, cmp);
  }

  private static <T> int partition(T[] arr, int start, int end, Comparator<T> cmp) {
    int pivot = start; // use the first one as the pivot
    T pivotVal = arr[pivot];
    int left = pivot + 1;
    int right = end;
    while (true) {
      // find left
      while (left < end && cmp.compare(arr[left], pivotVal) < 0) { 
        left++;
      }
      // find right
      while (right > start && cmp.compare(pivotVal, arr[right]) < 0) {
        right--;
      }
      if (left >= right) { // they crossed each other, stop
        break;
      }
      swap(arr, left, right); // found one set of elements that break the rule.
    }
    swap(arr, pivot, right); //swap the right with the pivot
    return right; // and now return the right as the partition point.
  }

  private static <T> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}