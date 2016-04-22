package edu.princeton.sort;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class CountSort {

  private static int lowerBound = '0';
  private static int radix = 'z' + 1 - lowerBound; // lower case, upper case, and digits in ASCII.

  public static void sort(int[] arr, int range) {
    if (arr == null || arr.length < 1 || range < 0) {
      return;
    }
    int n = arr.length;
    int[] aux = new int[n];
    int[] count = new int[range];

    // count
    for (int i = 0; i < n; i++) {
      int val = arr[i];
      if (val > range) {
        throw new IllegalArgumentException();
      }
      count[val + 1]++;
    }
    // accumulate
    for (int i = 1; i < range; i++) {
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

  @Test
  public void testSort() {
    int n = 15;
    int[] arr1 = new int[n];
    Random rand = new Random();
    for (int i = 0; i < arr1.length; i++) {
      arr1[i] = rand.nextInt(n);
    }
    System.out.println(Arrays.toString(arr1));
    int[] arr2 = Arrays.copyOf(arr1, n);
    CountSort.sort(arr1, 50);
    System.out.println(Arrays.toString(arr1));
    Arrays.sort(arr2);
    assertEquals(Arrays.toString(arr2), Arrays.toString(arr1));
  }
}
