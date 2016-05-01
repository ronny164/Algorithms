package algs4.sort;

import java.util.Comparator;

public class MergeSort {

  public static <T> void sort(T[] arr, Comparator<T> cmp) {
    if (arr == null || arr.length == 0 || cmp == null) {
      return;
    }
    T[] aux = (T[]) new Object[arr.length];
    sort(arr, 0, arr.length - 1, aux, cmp);
  }

  private static <T> void sort(T[] arr, int start, int end, T[] aux, Comparator<T> cmp) {
    if (end - start < 1) {
      return;
    }
    int mid = (start + end) / 2;
    sort(arr, start, mid, aux, cmp);
    sort(arr, mid + 1, end, aux, cmp);
    merge(arr, start, mid, mid + 1, end, aux, cmp);
  }

  private static <T> void merge(T[] arr, int a1Start, int a1End, int a2Start, int a2End, T[] aux,
      Comparator<T> cmp) {
    System.arraycopy(arr, a1Start, aux, a1Start, a2End - a1Start + 1);
    int i = a1Start;
    int j = a2Start;
    for (int k = a1Start; k <= a2End; k++) {
      if (i > a1End) {
        arr[k] = aux[j++];
      } else if (j > a2End) {
        arr[k] = aux[i++];
      } else if (cmp.compare(aux[i], aux[j]) <= 0) {
        arr[k] = aux[i++];
      } else {
        arr[k] = aux[j++];
      }
    }
  }
}
