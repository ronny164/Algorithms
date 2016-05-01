package algs4.sort;

import java.util.Arrays;
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
    int pivot = start;
    T pivotVal = arr[pivot];
    int i = start + 1;
    int j = end;
    System.out.println("i: " + i + ", j: " + j);
    while (true) {
      while (i < end && cmp.compare(arr[i], pivotVal) < 0) {
        i++;
      }
      while (j > start && cmp.compare(pivotVal, arr[j]) < 0) {
        j--;
      }
      if (i >= j) {
        break;
      }
      swap(arr, i, j);
    }
    swap(arr, pivot, j);
    System.out.println(Arrays.toString(arr));
    return j;
  }
  
  private static <T> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}