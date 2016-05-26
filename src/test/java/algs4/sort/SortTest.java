package algs4.sort;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import algs4.datastructures.HeapTest;

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

  @Test
  public void testMergeSort() throws Exception {
    Integer[] arr = new Integer[] {10, 5, 3, 4, 6, 8, 11, 9, 2};
    MergeSort.sort(arr, (o1, o2) -> {
      return o1 - o2;
    });
    assertEquals("[2, 3, 4, 5, 6, 8, 9, 10, 11]", Arrays.toString(arr));
  }

  @Test
  public void testQuickSort() throws Exception {
    Integer[] arr = new Integer[] {7, 1, 15, 10, 3, 5, 2, 9, 13, 4};
    QuickSort.sort(arr, (o1, o2) -> {
      return o1 - o2;
    });
    assertEquals("[1, 2, 3, 4, 5, 7, 9, 10, 13, 15]", Arrays.toString(arr));
  }

  @Test
  public void testFindKth() throws Exception {
    Integer[] arr = new Integer[] {7, 1, 15, 10, 3, 5, 2, 9, 13, 4};
    assertEquals(4, QuickSort.findKth(arr, 3, (o1, o2) -> {
      return o1 - o2;
    }).intValue());
    assertEquals(2, QuickSort.findKth(arr, 1, (o1, o2) -> {
      return o1 - o2;
    }).intValue());
    assertEquals(10, QuickSort.findKth(arr, 7, (o1, o2) -> {
      return o1 - o2;
    }).intValue());
  }
  


  @Test
  public void testHeapSortOnSmallExample() {
    Integer[] arr = new Integer[] {3, 7, 4, 2, 5, 9, 6, 1, 8};
    HeapSort.sort(arr);
    assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9]", Arrays.toString(arr));
  }

  @Test
  public void testHeapSortOnRandomArray() {
    int[] arr = HeapTest.generateRandom(20);
    Integer[] arr2 = new Integer[arr.length];
    int i = 0;
    for (int val : arr) {
      arr2[i++] = val;
    }
    HeapSort.sort(arr2);
    assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]", Arrays.toString(arr2));
  }
}
