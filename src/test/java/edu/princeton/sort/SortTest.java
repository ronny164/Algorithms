package edu.princeton.sort;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class SortTest {


  @Test
  public void testInsertionSort() {
    int[] nums = new int[] {38, 27, 43, 3, 9, 82, 10};
    int[] sorted = new int[] {3, 9, 10, 27, 38, 43, 82};
    InsertionSort.sort(nums);
    System.out.println(Arrays.toString(nums));
    assertTrue(Arrays.equals(nums, sorted));
  }
  
  @Test
  public void testCountingSort() {
    int n = 15;
    int[] arr1 = new int[n];
    Random rand = new Random();
    for (int i = 0; i < arr1.length; i++) {
      arr1[i] = rand.nextInt(n);
    }
    System.out.println(Arrays.toString(arr1));
    int[] arr2 = Arrays.copyOf(arr1, n);
    CountSort.sort(arr1, n);
    System.out.println(Arrays.toString(arr1));
    Arrays.sort(arr2);
    assertEquals(Arrays.toString(arr2), Arrays.toString(arr1));
  }

  @Test
  public void testRadisSort() {
    String[] arr1 = arr();
    RadixSort.sort(arr1);
    String[] arr2 = arr();
    Arrays.sort(arr2);
    assertEquals(Arrays.toString(arr1), Arrays.toString(arr2));
  }

  static String[] arr() {
    String[] arr = new String[] {"127", "761", "302", "250", "327"};
    return arr;
  }
}