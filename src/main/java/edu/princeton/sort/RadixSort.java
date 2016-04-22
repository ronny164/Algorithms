package edu.princeton.sort;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;

public class RadixSort {

  public void sort(String[] arr) {
    if (arr == null || arr.length < 1) {
      return;
    }

    int wordSize = arr[0].length();
    String[] aux = new String[arr.length];
    for (int charIndex = wordSize - 1; charIndex >= 0; charIndex--) {
      CountSort.sort(arr, aux, charIndex);
    }
  }

  @Test
  public void testSort() {
    String[] arr1 = arr();
    sort(arr1);
    String[] arr2 = arr();
    Arrays.sort(arr2);
    assertEquals(Arrays.toString(arr1), Arrays.toString(arr2));
  }

  static String[] arr() {
    String[] arr = new String[] {"127", "761", "302", "250", "327"};
    return arr;
  }
}
