package algs4.sort;


public class InsertionSort {

  public static void sort(int[] arr) {
    int n = arr.length;
    for (int i = 1; i < n; i++) {
      for (int j = i; j > 0 && arr[j - 1] > arr[j]; j--) { // 11 total instructions
        swap(arr, j, j - 1);
      }
    }
  }

  static void swap(int[] arr, int j, int i) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}