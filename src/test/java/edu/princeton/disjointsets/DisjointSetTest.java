package edu.princeton.disjointsets;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DisjointSetTest {
  @Test
  public void testBasic() {
    assertEquals(4, new DisjointSet(5, new int[][] {{0, 1}}).sets());
    assertEquals(2, new DisjointSet(5, new int[][] { {0, 1}, {1, 2}, {3, 4}}).sets());
    assertEquals(2, new DisjointSet(5, new int[][] { {0, 1}, {1, 2}, {0, 2}, {3, 4}}).sets());
    assertEquals(1, new DisjointSet(5, new int[][] { {0, 1}, {1, 2}, {2, 3}, {3, 4}}).sets());
    assertEquals(2, new DisjointSet(5, new int[][] { {0, 1}, {1, 2}, {0, 2}, {3, 4}}).sets());
    assertEquals(1, new DisjointSet(5, new int[][] { {0, 1}, {0, 2}, {2, 3}, {2, 4}}).sets());
  }
}
