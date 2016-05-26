package algs4.sort;

/**
 * @author Ronny A. Pena
 */
public class RadixSort {
  
  /**
   * Stable, O(w*r + n) space, and O((n + r) * w) time.
   */
  public static void sort(String[] arr) {
    if (arr == null || arr.length < 1) {
      return;
    }

    int wordSize = arr[0].length(); // w is the wordsize
    String[] aux = new String[arr.length];
    for (int charIndex = wordSize - 1; charIndex >= 0; charIndex--) {
      CountingSort.sort(arr, aux, charIndex);
    }
  }
}