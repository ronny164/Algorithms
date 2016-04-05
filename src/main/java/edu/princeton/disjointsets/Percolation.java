package edu.princeton.disjointsets;


/**
 * Given a composite systems comprised of randomly distributed insulating and metallic materials: 
 * what fraction of the materials need to be metallic so that the composite system is an electrical 
 * conductor? Given a porous landscape with water on the surface (or oil below), under what 
 * conditions will the water be able to drain through to the bottom (or the oil to gush through to 
 * the surface)? Scientists have defined an abstract process known as percolation to model such 
 * situations.
 * 
 * @author Ronny A. Pena
 */
public class Percolation {
  private DisjointSet topBottom;
  private DisjointSet top;
  private boolean[][] openSites;
  private int dimension;
  private int topVertex;
  private int bottomVertex;

  public Percolation(int dimension) {

    if (dimension <= 0) {
      throw new IllegalArgumentException();
    }

    this.openSites = new boolean[dimension][dimension];
    this.topVertex = (int) Math.pow(dimension, 2);
    this.bottomVertex = (int) Math.pow(dimension, 2) + 1;
    this.topBottom = new DisjointSet((int) Math.pow(dimension, 2) + 2);
    this.top = new DisjointSet((int) Math.pow(dimension, 2) + 2);
    this.dimension = dimension;
  }

  /**
   * Opens a site if it is not open already the test case start use 1, not 0, 
   * as the starting index.
   */
  public void open(int row, int col) {
    openSite(row - 1, col - 1);
  }

  private void openSite(int row, int col) {
    validate(row, col);
    if (!openSites[row][col]) {

      // opening the site
      openSites[row][col] = true;

      // connect top or bottom when the site is opened
      if (row == 0) {
        topBottom.union(topVertex, xyTo1D(row, col));
        top.union(topVertex, xyTo1D(row, col));
      } else if (row == dimension - 1) {
        topBottom.union(bottomVertex, xyTo1D(row, col));
      }

      // checking open sites on top, bottom, left, right and joining them
      if (row - 1 >= 0 && openSites[row - 1][col]) {
        topBottom.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        top.union(xyTo1D(row, col), xyTo1D(row - 1, col));
      }

      if (row + 1 < dimension && openSites[row + 1][col]) {
        topBottom.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        top.union(xyTo1D(row, col), xyTo1D(row + 1, col));
      }

      if (col - 1 >= 0 && openSites[row][col - 1]) {
        topBottom.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        top.union(xyTo1D(row, col), xyTo1D(row, col - 1));
      }

      if (col + 1 < dimension && openSites[row][col + 1]) {
        topBottom.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        top.union(xyTo1D(row, col), xyTo1D(row, col + 1));
      }

    }
  }

  private void validate(int row, int col) {
    if (row < 0 || col < 0 || row > dimension - 1 || col > dimension - 1) {
      throw new IndexOutOfBoundsException();
    }
  }

  private int xyTo1D(int row, int col) {
    return row * dimension + col;
  }

  public boolean isOpen(int row, int col) { // is site (row row, column col) open?
    // the test case start use 1, not 0, as the starting index.
    validate(row - 1, col - 1);
    return openSites[row - 1][col - 1];
  }

  public boolean isFull(int row, int col) { // is site (row row, column col) full?
    // the test case start use 1, not 0, as the starting index.
    validate(row - 1, col - 1);
    return top.isConnected(topVertex, xyTo1D(row - 1, col - 1));
  }

  public boolean percolates() { // does the system percolate?
    return topBottom.isConnected(topVertex, bottomVertex);
  }

}