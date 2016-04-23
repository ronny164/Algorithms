package edu.princeton;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinarySearchTest {


  @Test
  public void testBinarySearch() {
    Integer[] nodes = new Integer[] {2, 3, 5, 6, 7, 8, 10};
    assertEquals(4,BinarySearch.indexOf(nodes, 7));
    nodes = new Integer[] {2, 3, 5, 6, 7, 8, 10, 11, 20, 30};
    assertEquals(1,BinarySearch.indexOf(nodes, 3));
    assertEquals(-1,BinarySearch.indexOf(nodes, 50));
  }
}