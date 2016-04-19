package edu.princeton.heap;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ronny A. Pena
 */
public class Board {

  private char[] blocks;
  private int location;
  private int dimension;
  private int hammingDistance;
  private int manhattanDistance;

  private Board(char[] blocks, int dimension, int location) {
    super();
    this.blocks = blocks;
    this.dimension = dimension;
    this.location = location;
  }

  /**
   * Construct a board from an N-by-N array of blocks 
   * (where blocks[i][j] = block in row i, column j). 
   * @param initialBlocks The initial blocks.
   */
  public Board(int[][] initialBlocks) {

    if (initialBlocks == null) {
      throw new NullPointerException();
    }

    this.dimension = initialBlocks.length;
    blocks = new char[dimension * dimension];

    // converting 2d int array to 1d char array to save space.
    for (int i = 0; i < dimension; i++) {
      if (initialBlocks[i].length != dimension) {
        throw new IllegalArgumentException("blocks must be N-by-N.");
      }
      for (int j = 0; j < dimension; j++) {
        char currentVal = (char) initialBlocks[i][j];
        int col = (dimension * i) + j;
        if (currentVal == 0) {
          this.location = col;
        }
        blocks[col] = currentVal;
      }
    }
    computeDistance();
  }

  private void computeDistance() {
    manhattan();
    hamming();
  }

  /**
   * @return The board dimension.
   */
  public int dimension() {
    return this.dimension;
  }

  /**
   * @return The number of blocks out of place.
   */
  public int hamming() {
    if (hammingDistance == 0) {
      for (int i = 0; i < blocks.length; i++) {
        if (blocks[i] != 0 && blocks[i] != i + 1) {
          hammingDistance++;
        }
      }
    }
    return this.hammingDistance;
  }

  /**
   * @return The sum of Manhattan distances between blocks and goal.
   */
  public int manhattan() {
    if (manhattanDistance == 0) {
      for (int i = 0; i < blocks.length; i++) {
        int start = blocks[i] - 1;
        int target = i;
        if (start != -1 && start != target) {
          int columnDistance = Math.abs((start % dimension) - (target % dimension));
          int rowDistance = Math.abs((start / dimension) - (target / dimension));
          manhattanDistance += columnDistance;
          manhattanDistance += rowDistance;
        }
      }
    }
    return this.manhattanDistance;
  }

  private static void printArray(int[] display) {
    for (int i = 0; i < display.length; i++) {
      System.out.print((i + 1) + " ");
    }
    System.out.println();
    for (int j : display) {
      System.out.print("- ");
    }
    System.out.println();
    for (int j : display) {
      System.out.print(j + " ");
    }
    System.out.println();
  }

  /**
   * Is this board the goal board?
   * @return Determines is the board is the goal board.
   */
  public boolean isGoal() {
    return manhattanDistance == 0 && hammingDistance == 0;
  }

  /**
   * @return A board that is obtained by exchanging any pair of blocks.
   */
  public Board twin() {
    do {
      Board twin = copy();
      int i = 0;
      int j = 1;
      while (twin.blocks[i] == 0 || twin.blocks[j] == 0) {
        if (twin.blocks[i] == 0) {
          i = StdRandom.uniform(twin.blocks.length);
        } else if (twin.blocks[j] == 0) {
          j = StdRandom.uniform(twin.blocks.length);
        } else {
          break;
        }
      }
      swap(twin.blocks, i, j);
      twin.computeDistance();
      if (!twin.equals(this)) {
        return twin;
      }
    } while (true);
  }

  private Board copy() {
    char[] copy = new char[this.blocks.length];
    System.arraycopy(this.blocks, 0, copy, 0, this.blocks.length);
    return new Board(copy, dimension, location);
  }

  /**
   * @return All neighboring boards.
   */
  public Iterable<Board> neighbors() {
    List<Board> neighbors = new ArrayList<>();

    // up
    int newLocation = location - dimension;
    if (!(newLocation < 0)) { // prevent out of bound on the top.
      neighbors.add(move(newLocation));
    }

    // left
    newLocation = location - 1;
    if (newLocation >= 0 && (location) % dimension != 0) {
      // prevent out of bound on the left
      neighbors.add(move(newLocation));
    }

    // right
    newLocation = location + 1;
    if (newLocation % dimension != 0) { // prevent out of bound on the right
      neighbors.add(move(newLocation));
    }

    // down
    newLocation = location + dimension;
    if (newLocation < this.blocks.length) { // prevent out of bound on the bottom.
      neighbors.add(move(newLocation));
    }

    return neighbors;
  }

  private Board move(int newLocation) {
    Board copy = this.copy();
    copy.location = newLocation;
    swap(copy.blocks, location, newLocation);
    copy.computeDistance();
    return copy;
  }

  private static void swap(char[] array, int i, int j) {
    char temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Board other = (Board) obj;
    if (!Arrays.equals(blocks, other.blocks)) {
      return false;
    }
    return true;
  }

  /**
   * String representation of this board (in the output format specified below).
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(dimension + "\n");
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        int val = blocks[(dimension * i) + j];
        sb.append(String.format("%2d ", val));
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
