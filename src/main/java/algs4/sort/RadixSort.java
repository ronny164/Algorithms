package algs4.sort;


public class RadixSort {

  public static void sort(String[] arr) {
    if (arr == null || arr.length < 1) {
      return;
    }

    int wordSize = arr[0].length();
    String[] aux = new String[arr.length];
    for (int charIndex = wordSize - 1; charIndex >= 0; charIndex--) {
      CountSort.sort(arr, aux, charIndex);
    }
  }
}