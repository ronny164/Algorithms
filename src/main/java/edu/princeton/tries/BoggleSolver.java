package edu.princeton.tries;

import java.util.Collection;
import java.util.HashSet;

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

  // Initializes the data structure using the given array of strings as the dictionary.
  // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
  public BoggleSolver(String[] dictionary) {
    if (dictionary == null || dictionary.length == 0) {
      throw new IllegalArgumentException();
    }
    this.dict = new ArrayTrie('Z' + 1 - 'A', 'A');
    for (String word : dictionary) {
      if (word.length() >= 3) {
        dict.add(word);
      }
    }
  }

  // Returns the set of all valid words in the given Boggle board, as an Iterable.
  public Iterable<String> getAllValidWords(BoggleBoard board) {
    if (board == null || (board.rows() == 0 && board.cols() == 0)) {
      throw new IllegalArgumentException();
    }
    int m = board.rows();
    int n = board.cols();
    Collection<String> paths = new HashSet<>();
    boolean[][] visited = new boolean[m][n];
    StringBuilder path = new StringBuilder();
    // Optimization todo: build an adjacency list and traverse the using bfs.
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        dfs(board, i, j, path, paths, visited);
      }
    }
    return paths;
  }

  private void dfs(BoggleBoard board, int row, int col, StringBuilder path,
      Collection<String> paths, boolean[][] visited) {

    // with in the boundary of the game or the location has been only used once.
    if (row < 0 || col < 0 || row >= board.rows() || col >= board.cols() || visited[row][col]) {
      return;
    }

    // build the current word
    char letter = board.getLetter(row, col);
    push(path, letter);

    // Optimization todo: pass the trieNode by reference
    // and compute the new child based on current letter.

    if (path.length() >= 3) {
      // Optimization: if this is word is not part of the dictionary,
      // don't go down that path
      ArrayTrie.TrieNode node = dict.get(path);
      if (node == null) {
        pop(path, letter);
        return;
      }
      // collect the word if its found in the board and it part of the dictionary
      if (node.isWord) {
        paths.add(path.toString());
      }
    }

    visited[row][col] = true;
    // following all adjacent squares.
    dfs(board, row - 1, col - 1, path, paths, visited);
    dfs(board, row - 1, col    , path, paths, visited);
    dfs(board, row - 1, col + 1, path, paths, visited);
    dfs(board, row    , col + 1, path, paths, visited);
    dfs(board, row + 1, col + 1, path, paths, visited);
    dfs(board, row + 1, col    , path, paths, visited);
    dfs(board, row + 1, col - 1, path, paths, visited);
    dfs(board, row    , col - 1, path, paths, visited);
    visited[row][col] = false;
    pop(path, letter);

  }

  private void push(StringBuilder currentPath, char letter) {
    currentPath.append(letter);
    if (letter == 'Q') {
      currentPath.append('U');
    }
  }

  private void pop(StringBuilder currentPath, char letter) {
    if (letter == 'Q') {
      currentPath.setLength(currentPath.length() - 1);
    }
    currentPath.setLength(currentPath.length() - 1);
  }

  // Returns the score of the given word if it is in the dictionary, zero otherwise.
  // (You can assume the word contains only the uppercase letters A through Z.)
  public int scoreOf(String word) {
    if (word != null) {
      int n = word.length();
      if (n > 2 && dict.contains(word)) {
        if (n <= 4) {
          return 1;
        } else if (n == 5) {
          return 2;
        } else if (n == 6) {
          return 3;
        } else if (n == 7) {
          return 5;
        } else {
          return 11;
        }
      }
    }
    return 0;
  }
}