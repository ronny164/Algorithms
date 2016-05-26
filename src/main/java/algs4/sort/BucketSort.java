package algs4.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ronny A. Pena
 */
public class BucketSort {
  /**
   * O(n + k) time avg case.
   * O(n^2 + k) time worst case.
   * stable when using insertion sort to sort the buckets.
   * 
   * @param arr the input array.
   * @param k The number of buckets to use.
   */
  public static void sort(int[] arr, int k) {
    if (arr == null || arr.length == 0) {
      return;
    }
    int min = arr[0];
    int max = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] < min) {
        min = arr[i];
      } else if (arr[i] > max) {
        max = arr[i];
      }
    }
    List<Integer>[] buckets = new ArrayList[k + 1];
    int div = (max - min + 1) / k;
    for (int val : arr) {
      int key = ((val - min) / div);
      if (buckets[key] == null) {
        buckets[key] = new ArrayList<>();
      }
      buckets[key].add(val);
    }
    int i = 0;
    for (List<Integer> bucket : buckets) {
      if (bucket != null) {
        Collections.sort(bucket);
        for (int val : bucket) {
          arr[i++] = val;
        }
      }
    }
  }
}