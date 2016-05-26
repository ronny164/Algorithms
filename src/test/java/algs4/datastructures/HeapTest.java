package algs4.datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Collections;

public class HeapTest {

  @Test
  public void testMaxHeap() {
    int n = 20;
    int[] arr = TestUtils.generateRandom(n);
    Heap<Integer> pq = new Heap<>(Collections.reverseOrder());
    for (int val : arr) {
      pq.add(val);
    }
    System.out.println("finished adding.");
    assertEquals(n, pq.remove().intValue());
    for (int i = 1; i <= 19; i++) {
      assertEquals(n - i, pq.remove().intValue());
    }
    assertTrue(pq.isEmpty());
  }

  @Test
  public void testTopKthElements() {
    int n = 20;
    int k = 3;
    int[] arr = TestUtils.generateRandom(n);
    Heap<Integer> pq = new Heap<>();

    // The code block below is O(n log k) time, and O(k) space.
    for (int i = 0; i < n; i++) {
      pq.add(arr[i]);
      if (i + 1 > k) {
        System.out.println(pq.remove());
      }
    }
    for (int i = 0; i < k; i++) {
      int val = pq.remove().intValue();
      assertTrue(val >= 18 && val <= 20);
    }
    assertTrue(pq.isEmpty());
  }
}