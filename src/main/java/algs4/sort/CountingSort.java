package algs4.sort;

/**
 * @author Ronny A. Pena
 */
public class CountingSort {

  private static int lowerBound = '0';
  private static int radix = 'z' + 1 - lowerBound; // lower case, upper case, and digits in ASCII.

  /**
   * Stable, O(n + r) time and space, 4n + r estimated runtime.
   */
  public static void sort(int[] arr, int range) {
    if (arr == null || arr.length < 1 || range < 0) {
      return;
    }
    int n = arr.length;
    int[] aux = new int[n];
    int[] count = new int[range + 1];

    // count
    for (int i = 0; i < n; i++) {
      int val = arr[i];
      if (val > range) {
        throw new IllegalArgumentException();
      }
      count[val + 1]++;
    }
    // accumulate
    for (int i = 1; i <= range; i++) {
      count[i] += count[i - 1];
    }
    // copy to aux
    for (int i = 0; i < n; i++) {
      int val = arr[i];
      int target = count[val]++;
      aux[target] = val;
    }
    // copy back
    for (int i = 0; i < n; i++) {
      arr[i] = aux[i];
    }
  }

  static void sort(String[] arr, String[] aux, int charIndex) {
    int n = arr.length;
    int[] count = new int[radix];
    // count
    for (int i = 0; i < n; i++) {
      int index = arr[i].charAt(charIndex) - lowerBound + 1;
      if (index > radix) {
        throw new IllegalArgumentException();
      }
      count[index]++;
    }
    // accumulate
    for (int i = 1; i < radix; i++) {
      count[i] += count[i - 1];
    }
    // copy to aux
    for (int i = 0; i < n; i++) {
      String word = arr[i];
      int index = word.charAt(charIndex) - lowerBound;
      int target = count[index];
      aux[target] = word;
      count[index]++; // move to next item for that letter.
    }
    // copy back
    for (int i = 0; i < n; i++) {
      arr[i] = aux[i];
    }
  }
}
