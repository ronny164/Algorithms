package algs4.tries;

import algs4.datastructures.ArrayTrieSet;
import algs4.datastructures.ArrayTrieSet.TrieNode;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * The Boggle game. Boggle is a word game designed by Allan Turoff and distributed by Hasbro. 
 * It involves a board made up of 16 cubic dice, where each die has a letter printed on each of its 
 * sides. At the beginning of the game, the 16 dice are shaken and randomly distributed into a 
 * 4-by-4 tray, with only the top sides of the dice visible. The players compete to accumulate 
 * points by building valid words out of the dice according to the following rules:
 * <br/> A valid word must be composed by following a sequence of adjacent dice—two dice are 
 *       adjacent if they are horizontal, vertical, or diagonal neighbors.
 * <br/> A valid word can use each die at most once.
 * <br/> A valid word must contain at least 3 letters.
 * <br/> A valid word must be in the dictionary (which typically does not contain proper nouns).
 *  
 * @author Ronny A. Pena
 */
public class BoggleSolver {

  private ArrayTrieSet dict;
  private char lowerBound = 'A';

  // storing all these variables in global scope to reduce the size of the stack frame when using
  // dfs.
  private Collection<String> paths;
  private int m;
  private int n;
  private boolean[][] visited;
  private BoggleBoard board;

  /** 
   * Initializes the data structure using the given array of strings as the dictionary.
   * (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
   */
  public BoggleSolver(String[] dictionary) {
    if (dictionary == null || dictionary.length == 0) {
      throw new IllegalArgumentException();
    }
    this.dict = new ArrayTrieSet('Z' + 1 - lowerBound, lowerBound);
    for (String word : dictionary) {
      if (word.length() >= 3) {
        dict.add(word);
      }
    }
  }

  /**
   *  Returns the set of all valid words in the given Boggle board, as an Iterable.
   */
  public Iterable<String> getAllValidWords(BoggleBoard inputBoard) {
    if (inputBoard == null || (inputBoard.rows() == 0 && inputBoard.cols() == 0)) {
      throw new IllegalArgumentException();
    }
    board = inputBoard;
    m = board.rows();
    n = board.cols();
    paths = new LinkedList<>();
    visited = new boolean[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        dfs(i, j, dict.root);
      }
    }
    return new HashSet<>(paths); // remove duplicates.
  }

  private void dfs(int row, int col, ArrayTrieSet.TrieNode parent) {

    // with in the boundary of the game or the location has been only used once.
    if (row < 0 || col < 0 || row >= m || col >= n || visited[row][col]) {
      return;
    }

    // build up the current word as we go.
    char letter = board.getLetter(row, col);
    ArrayTrieSet.TrieNode child = getChild(parent, letter);

    // Optimization: if this is word is not part of the dictionary, don't go down that path
    if (child == null) {
      return;
    }

    // collect the word if its found in the board and it part of the dictionary
    if (child.word != null) {
      paths.add(child.word);
    }

    if (child.children == null) {
      return;
    }

    visited[row][col] = true;
    // following all adjacent squares.
    int up = row + 1;
    int down = row - 1;
    int left = col - 1;
    int right = col + 1;
    dfs(row, right, child);
    dfs(down, right, child);
    dfs(down, col, child);
    dfs(down, left, child);
    dfs(row, left, child);
    dfs(up, left, child);
    dfs(up, col, child);
    dfs(up, right, child);
    visited[row][col] = false;
  }

  private TrieNode getChild(TrieNode parent, char letter) {
    TrieNode child = parent.children[letter - lowerBound];
    if (child != null) {
      if (letter == 'Q') { // special Qu case.
        child = child.children['U' - lowerBound];
      }
    }
    return child;
  }

  /** 
   * Returns the score of the given word if it is in the dictionary, zero otherwise.
   * (You can assume the word contains only the uppercase letters A through Z.)
   */
  public int scoreOf(String word) {
    if (word != null) {
      int l = word.length();
      if (l > 2 && dict.contains(word)) {
        if (l <= 4) {
          return 1;
        } else if (l == 5) {
          return 2;
        } else if (l == 6) {
          return 3;
        } else if (l == 7) {
          return 5;
        } else {
          return 11;
        }
      }
    }
    return 0;
  }
}
