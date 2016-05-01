package algs4.sort;


public class SelectionSort {

  public static void sort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n; i++) {
      int maxIndex = i;
      for (int j = i; j >= 0; j--) {
        if (arr[j] < arr[i]) {
          maxIndex = j;
        }
      }
      InsertionSort.swap(arr, i, maxIndex);
    }
  }
}
