package edu.princeton.disjointsets;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.HashSet;
import java.util.Set;

/**
 * Performs series of computational experiments on Percolation component.
 * 
 * @author Ronny A. Pena
 */
public class PercolationStats {
  private double[] samples;

  /**
   * Perform independent experiments on an N-by-N grid.
   */
  public PercolationStats(int dimension, int expirements) {
    if (dimension < 1 || expirements < 1) {
      throw new IllegalArgumentException("T or N has to be greater than 1.");
    }
    this.samples = new double[expirements];
    for (int k = 0; k < expirements; k++) {
      Set<Integer> openedSites = new HashSet<>();
      Percolation p = new Percolation(dimension);
      do {
        int rand = StdRandom.uniform(dimension * dimension);
        int i = (rand / dimension) + 1; // indexes start at 1, not 0
        int j = (rand % dimension) + 1; // indexes start at 1, not 0
        if (!openedSites.contains(Integer.valueOf(rand))) {
          p.open(i, j);
          openedSites.add(Integer.valueOf(rand));
        }
      } while (!p.percolates());
      samples[k] = ((openedSites.size()) / Math.pow(dimension, 2));
    }
  }

  /**
   * @return sample mean of percolation threshold
   */
  public double mean() {
    return StdStats.mean(samples);
  }

  /**
   * @return sample standard deviation of percolation threshold
   */
  public double stddev() {
    return StdStats.stddev(samples);
  }

  /**
   * @return low endpoint of 95% confidence interval
   */
  public double confidenceLo() {
    double a = stddev();
    double u = mean();
    return u - ((1.96 * a) / Math.sqrt(samples.length));
  }

  /**
   * @return high endpoint of 95% confidence interval
   */
  public double confidenceHi() {
        double a = stddev();
        double u = mean();
        return u + ((1.96 * a) / Math.sqrt(samples.length));
    }

  /**
   * test client (described below)
   */
  public static void main(String[] args) {
    if (args.length < 2) {
      throw new IllegalArgumentException("Please provide two input parameters.");
    }

    int N = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);

    PercolationStats ps = new PercolationStats(N, T);
    System.out.println("mean                    = " + ps.mean());
    System.out.println("stddev                  = " + ps.stddev());
    System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
  }
}
