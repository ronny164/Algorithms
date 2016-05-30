package algs4.sort;

import static org.junit.Assert.assertEquals;
import algs4.datastructures.TestUtils;

import org.junit.Test;

import java.util.Arrays;

public class SortTest {

  @Test
  public void testInsertionSort() {
    int[] nums = new int[] {38, 27, 43, 3, 9, 82, 10};
    int[] sorted = new int[] {};
    InsertionSort.sort(nums);
    System.out.println(Arrays.toString(nums));
    assertEquals("[3, 9, 10, 27, 38, 43, 82]", Arrays.toString(nums));
  }

  @Test
  public void testCountingSort() {
    int n = 15;
    int[] arr1 = TestUtils.generateRandom(n);
    System.out.println(Arrays.toString(arr1));
    int[] arr2 = Arrays.copyOf(arr1, n);
    CountingSort.sort(arr1, n);
    System.out.println(Arrays.toString(arr1));
    Arrays.sort(arr2);
    assertEquals(Arrays.toString(arr2), Arrays.toString(arr1));
  }

  @Test
  public void testRadixSort() {
    String[] arr1 = new String[] {"127", "761", "302", "250", "327"};
    RadixSort.sort(arr1);
    String[] arr2 = new String[] {"127", "761", "302", "250", "327"};
    Arrays.sort(arr2);
    assertEquals(Arrays.toString(arr1), Arrays.toString(arr2));
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
  public void testBucketSort() {
    int[] arr = new int[] {2, 10, 5, 9, 3, 4, 15, 20, 21, 18};
    BucketSort.sort(arr, 5);
    assertEquals("[2, 3, 4, 5, 9, 10, 15, 18, 20, 21]", Arrays.toString(arr));

    arr = new int[] {2, 10, 5, 9, 3, 4, 15, 20, 21, 18, 3, 4, 8, 2, 
        8, 8, 2, 14, 5, 6, 7, 8, 4, 2, 4, 7, 8, 4, 2, 5, 6, 9, 8, 5, 22};
    BucketSort.sort(arr, 5);
    assertEquals(
        "[2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 7, 7, 8, 8, 8, 8, 8, 8, 9, "
            + "9, 10, 14, 15, 18, 20, 21, 22]", Arrays.toString(arr));
  }

  @Test
  public void testHeapSortOnSmallExample() {
    Integer[] arr = new Integer[] {3, 7, 4, 2, 5, 9, 6, 1, 8};
    HeapSort.sort(arr);
    System.out.println(Arrays.toString(arr));
    assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9]", Arrays.toString(arr));
  }

  @Test
  public void testHeapSortOnRandomArray() {
    int[] arr = TestUtils.generateRandom(20);
    Integer[] arr2 = new Integer[arr.length];
    int i = 0;
    for (int val : arr) {
      arr2[i++] = val;
    }
    HeapSort.sort(arr2);
    System.out.println(Arrays.toString(arr2));
    assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]", Arrays.toString(arr2));
  }
}