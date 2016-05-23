package algs4.sort;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Ronny A. Pena
 */
public class BucketSort {
  /**
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

  @Test
  public void testSort() {
    int[] arr = new int[] {2, 10, 5, 9, 3, 4, 15, 20, 21, 18};
    sort(arr, 5);
    assertEquals("[2, 3, 4, 5, 9, 10, 15, 18, 20, 21]", Arrays.toString(arr));

    arr = new int[] {2, 10, 5, 9, 3, 4, 15, 20, 21, 18, 3, 4, 8, 2, 
        8, 8, 2, 14, 5, 6, 7, 8, 4, 2, 4, 7, 8, 4, 2, 5, 6, 9, 8, 5, 22};
    sort(arr, 5);
    assertEquals(
        "[2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 7, 7, 8, 8, 8, 8, 8, 8, 9, "
            + "9, 10, 14, 15, 18, 20, 21, 22]", Arrays.toString(arr));
  }
}