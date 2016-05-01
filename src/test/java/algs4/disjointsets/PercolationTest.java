package algs4.disjointsets;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PercolationTest {

  @Test
  public void testDiagonal() {
    // dummy data
    int N = 3;

    // run the code
    Percolation p = new Percolation(N);
    for (int i = 1; i <= N; i++) {
      p.open(i, i);
      // p.print();
    }
    // verify
    assertTrue(p.isFull(1, 1));
    assertFalse(p.isFull(2, 2));
    assertFalse(p.percolates());
  }

  @Test
  public void testStraigthDown() {
    // dummy data
    int N = 4;

    // run the code
    Percolation p = new Percolation(N);
    for (int i = 1; i <= N; i++) {
      p.open(i, 2);
      // p.print();
    }
    // verify
    assertTrue(p.isFull(1, 2));
    assertTrue(p.isFull(2, 2));
    assertFalse(p.isFull(4, 4));
    assertTrue(p.percolates());
  }

  @Test
  public void testStatsGeneration() {
    PercolationStats p = new PercolationStats(10, 1);
    System.out.println("done, mean: " + p.mean());
    System.out.println("done, stddev: " + p.stddev());
    p = new PercolationStats(200, 100);
    System.out.println("done, mean: " + p.mean());
    System.out.println("done, stddev: " + p.stddev());
    p = new PercolationStats(2, 100000);
    System.out.println("done, mean: " + p.mean());
    System.out.println("done, stddev: " + p.stddev());
  }
}
