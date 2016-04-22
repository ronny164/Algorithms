package edu.princeton;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinarySearch {

  public <T extends Comparable> int indexOf(T[] arr, T search) {

    if (arr == null) {
      return -1;
    }

    int start = 0;
    int end = arr.length - 1;
    while (start <= end) {
      int mid = (end + start) / 2;
      int compareTo = arr[mid].compareTo(search);
      if (compareTo == 0) {
        return mid;
      } else if (compareTo < 0) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return -1;
  }

  @Test
  public void testBinarySearch() {
    Integer[] nodes = new Integer[] {2, 3, 5, 6, 7, 8, 10};
    assertEquals(4,indexOf(nodes, 7));
    nodes = new Integer[] {2, 3, 5, 6, 7, 8, 10, 11, 20, 30};
    assertEquals(1,indexOf(nodes, 3));
    assertEquals(-1,indexOf(nodes, 50));
  }
}
