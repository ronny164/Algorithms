package algs4.sort;

/**
 * @author Ronny A. Pena
 */
public class InsertionSort {
  
  /**
   * Stable, O(1) space, O(n) on the best case, O(n^2) runtime on the worst case, 
   */
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