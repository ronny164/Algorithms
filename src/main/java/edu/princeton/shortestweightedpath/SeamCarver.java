package edu.princeton.shortestweightedpath;

import edu.princeton.cs.algs4.Picture;

import java.util.Arrays;

/**
 * Seam-carving is a content-aware image resizing technique where the image is reduced in size by
 * one pixel of height (or width) at a time. A vertical seam in an image is a path of pixels
 * connected from the top to the bottom with one pixel in each row. (A horizontal seam is a path of
 * pixels connected from the left to the right with one pixel in each column.) Below left is the
 * original 505-by-287 pixel image; below right is the result after removing 150 vertical seams,
 * resulting in a 30% narrower image. Unlike standard content-agnostic resizing techniques (e.g.
 * cropping and scaling), the most interesting features (aspect ratio, set of objects present, etc.)
 * of the image are preserved.
 * 
 * @author Ronny A. Pena
 */
public class SeamCarver {

  private Vertex[][] vertices;
  private int width;
  private int height;
  // used to perform row-order or column order operations on the image.
  private boolean transposed;

  /**
   * Create a seam carver object based on the given picture.
   */
  public SeamCarver(Picture picture) {
    if (picture == null) {
      throw new NullPointerException();
    }
    this.height = picture.height();
    this.width = picture.width();
    this.vertices = new Vertex[height][width];
    ImageUtil.saveImage(picture, vertices);
  }

  private int[] computeShortestPath() {
    // look at the top row, select all the pixels as the starting point
    // traversal up while looking at the bottom left, bottom, and  bottom right pixel energy 
    // and selecting the smallest total accumulated energy in the end.
    int localHeight = internalHeight();
    int localWidth = internalWidth();
    double[][] distTo = new double[localHeight][localWidth];
    int[][] edgeTo = new int[localHeight][localWidth];
    for (int col = 0; col < localWidth - 1; col++) {
      distTo[0][col] = getEnergy(0, col);
      edgeTo[0][col] = col;
    }

    // In this case, The shortest path is the path with the lowest energy.
    // for current column, compute shortest path to any of the bottom column and keep that.
    // an edge is this case, is a pair of image pixel. 
    for (int row = 1; row < localHeight; row++) {
      Arrays.fill(distTo[row], Double.POSITIVE_INFINITY);
      int prevRow = row - 1;
      for (int col = 0; col < localWidth - 1; col++) {
        // left vertex
        if (col - 1 >= 0) {
          relaxEdge(distTo, edgeTo, prevRow, col, row, col - 1);
        }

        // middle vertex
        relaxEdge(distTo, edgeTo, prevRow, col, row, col);

        // right vertex
        if (col < localWidth) {
          relaxEdge(distTo, edgeTo, prevRow, col, row, col + 1);
        }
      }
    }

    int minVertex = findShortestVertex(distTo);
    return buildSeamFromPath(minVertex, edgeTo);
  }

  private void relaxEdge(double[][] distTo, int[][] edgeTo, 
      int prevRow, int prevCol, int row, int col) {
    double prevTotalDistance = distTo[prevRow][prevCol];
    double currentTotalDistance = distTo[row][col];
    double edgeDistance = getEnergy(row, col);
    if (prevTotalDistance + edgeDistance < currentTotalDistance) {
      distTo[row][col] = prevTotalDistance + edgeDistance;
      edgeTo[row][col] = prevCol;
    }
  }

  private int findShortestVertex(double[][] distTo) {
    double minDistance = Double.MAX_VALUE;
    int minVertex = 0;

    int localWidth = internalWidth();
    int localHeight = internalHeight();
    for (int col = 0; col < localWidth; col++) {
      // search all the bottom pixel for the one with min distance.
      double current = distTo[localHeight - 1][col];
      if (current < minDistance) {
        minDistance = current;
        minVertex = col;
      }
    }
    return minVertex;
  }

  private int[] buildSeamFromPath(int minVertex, int[][] edgeTo) {
    int localHeight = internalHeight();
    int[] seam = new int[localHeight];
    int i = localHeight - 1;
    int current = minVertex;
    while (i >= 0) {
      seam[i] = current;
      int parent = edgeTo[i][current];
      current = parent;
      i--;
    }
    return seam;
  }

  private void remove(int[] seam, int newHeight, int newWidth) {
    int localWidth = internalWidth();
    //validate the seam
    for (int i = 0; i < seam.length; i++) {
      int val = seam[i];
      if (val < 0 || val >= localWidth) {
        throw new IllegalArgumentException();
      }
      if (i > 0 && Math.abs(seam[i] - seam[i - 1]) > 1) {
        throw new IllegalArgumentException("Invalid distances between seam cols: "
            + Arrays.toString(seam));
      }
    }
    //shift the array, row-order or col-order
    int localHeight = internalHeight();
    for (int i = 0; i < localHeight; i++) {
      for (int j = seam[i]; j < localWidth - 1; j++) {
        ImageUtil.set(vertices, i, j, j + 1, transposed);
      }
      // free up memory
      ImageUtil.clear(vertices, i, localWidth - 1, transposed);
    }
    width = newWidth;
    height = newHeight;
  }


  /**
   * sequence of indices for horizontal seam
   */
  public int[] findHorizontalSeam() {
    transposed = true;
    return computeShortestPath();
  }

  /**
   * sequence of indices for vertical seam
   */
  public int[] findVerticalSeam() {
    transposed = false;
    return computeShortestPath();
  }

  /**
   * remove vertical seam from current picture
   */
  public void removeVerticalSeam(int[] seam) {
    if (seam == null) {
      throw new NullPointerException();
    }
    if (seam.length != height) {
      throw new IllegalArgumentException();
    }
    transposed = false;
    remove(seam, height, width - 1);
  }

  /**
   * remove horizontal seam from current picture
   */
  public void removeHorizontalSeam(int[] seam) {
    if (seam == null) {
      throw new NullPointerException();
    }
    if (seam.length != width) {
      throw new IllegalArgumentException();
    }
    transposed = true;
    remove(seam, height - 1, width);
  }
  
  /**
   * @return The current picture.
   */
  public Picture picture() {
    return ImageUtil.createPicture(vertices, width, height);
  }

  /**
   * @return The width of current picture
   */
  public int width() {
    return width;
  }

  /**
   * @return The height of current picture.
   */
  public int height() {
    return height;
  }

  private int internalWidth() {
    return (transposed) ? height : width;
  }

  private int internalHeight() {
    return (transposed) ? width : height;
  }
  private double getEnergy(int row, int col) {
    return (transposed) ? vertices[col][row].energy : vertices[row][col].energy;
  }

  /**
   * Uses the dual-gradient energy formula.
   * @return Energy of pixel at column x and row y.
   */
  public double energy(int x, int y) {
    return vertices[y][x].energy;
  }
}