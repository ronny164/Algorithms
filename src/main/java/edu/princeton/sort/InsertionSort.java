package edu.princeton.sort;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;

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

  @Test
  public void testInsertionSort() {
    int[] nums = new int[] {38, 27, 43, 3, 9, 82, 10};
    int[] sorted = new int[] {3, 9, 10, 27, 38, 43, 82};
    sort(nums);
    System.out.println(Arrays.toString(nums));
    assertTrue(Arrays.equals(nums, sorted));
  }
}
