package edu.princeton.tries;

import edu.princeton.tries.ArrayTrie.TrieNode;

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

  private ArrayTrie dict;
  private char lowerBound = 'A';
  
  //storing all these variables in global scope to reduce the size of the stack frame when using dfs.
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
    this.dict = new ArrayTrie('Z' + 1 - lowerBound, lowerBound);
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
    StringBuilder path = new StringBuilder(dict.maxWordSize);
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        dfs(i, j, dict.root, path);
      }
    }
    return new HashSet<>(paths); // remove duplicates.
  }

  private void dfs(int row, int col, TrieNode parent, StringBuilder path) {

    // with in the boundary of the game or the location has been only used once.
    if (row < 0 || col < 0 || row >= m || col >= n || visited[row][col]) {
      return;
    }

    // Optimization: if this is word is not part of the dictionary, don't go down that path
    if (parent == null || parent.children == null) {
        return;
    }
    
    char letter = board.getLetter(row, col);
    TrieNode child = parent.children[letter - lowerBound];
    if (child == null) {
        return;
    }
    // build the current word
    child = push(path, letter, child);
    if (child == null) {
        pop(path, letter);
        return;
    }
    
    // collect the word if its found in the board and it part of the dictionary
    if (child.isWord) {
        paths.add(path.toString());
    }

    visited[row][col] = true;
    // following all adjacent squares.
    dfs(row - 1, col - 1, child, path);
    dfs(row - 1, col    , child, path);
    dfs(row - 1, col + 1, child, path);
    dfs(row,     col + 1, child, path);
    dfs(row + 1, col + 1, child, path);
    dfs(row + 1, col    , child, path);
    dfs(row + 1, col - 1, child, path);
    dfs(row,     col - 1, child, path);
    visited[row][col] = false;
    pop(path, letter);
  }

private TrieNode push(StringBuilder path, char letter, TrieNode child) {
    path.append(letter);
    if (letter == 'Q') { // special Qu case.
        path.append('U');
        return child.children['U' - lowerBound];
    }
    return child;
}

  private void pop(StringBuilder currentPath, char letter) {
    if (letter == 'Q') {  // special Qu case.
      currentPath.setLength(currentPath.length() - 1);
    }
    currentPath.setLength(currentPath.length() - 1);
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