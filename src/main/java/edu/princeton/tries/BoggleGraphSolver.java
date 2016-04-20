package edu.princeton.tries;

import edu.princeton.graphs.MultiMap;
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
public class BoggleGraphSolver {

  private ArrayTrie dict;
  private char lowerBound = 'A';

  // Initializes the data structure using the given array of strings as the dictionary.
  // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
  public BoggleGraphSolver(String[] dictionary) {
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
    MultiMap<Vertex, Vertex> graph = new MultiMap<>();
    // Optimization: build an adjacency list and traverse the using dfs.
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        graph.put(new Vertex(i, j), getAdj(i, j, m, n));
      }
    }
    for (Vertex vertex : graph.keySet()) {
      dfs(vertex, graph, visited, path, paths, board, dict.root);
    }
    return paths;
  }

  private void dfs(Vertex vertex, MultiMap<Vertex, Vertex> graph, boolean[][] visited,
      StringBuilder path, Collection<String> paths, BoggleBoard board, TrieNode parent) {
    int row = vertex.row;
    int col = vertex.col;
    if (visited[row][col]) {
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

    // collect the word if its found in the board and it part of the dictionary
    if (child != null && child.isWord) {
      paths.add(path.toString());
    }

    visited[row][col] = true;
    // following all adjacent squares.
    Collection<Vertex> ajd = graph.getValues(vertex);
    if (ajd != null) {
      for (Vertex childVertex : ajd) {
        dfs(childVertex, graph, visited, path, paths, board, child);
      }
    }
    visited[row][col] = false;
    pop(path, letter);
  }

  private Collection<Vertex> getAdj(int row, int col, int rowLimit, int colLimit) {
    Collection<Vertex> adj = new LinkedList<>();
    if (row - 1 >= 0) {
      if (col - 1 >= 0) {
        adj.add(new Vertex(row - 1, col - 1));
      }
      adj.add(new Vertex(row - 1, col));
      if (col + 1 < colLimit) {
        adj.add(new Vertex(row - 1, col + 1));
      }
    }
    if (row + 1 < rowLimit) {
      if (col + 1 < colLimit) {
        adj.add(new Vertex(row + 1, col + 1));
      }
      adj.add(new Vertex(row + 1, col));
      if (col - 1 >= 0) {
        adj.add(new Vertex(row + 1, col - 1));
      }
    }
    if (col + 1 < colLimit) {
      adj.add(new Vertex(row, col + 1));
    }
    if (col - 1 >= 0) {
      adj.add(new Vertex(row, col - 1));
    }
    return adj;
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
    if (letter == 'Q') { // special Qu case.
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

final class Vertex {
  int row;
  int col;

  public Vertex(int row, int col) {
    super();
    this.row = row;
    this.col = col;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + col;
    result = prime * result + row;
    return result;
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
    Vertex other = (Vertex) obj;
    if (col != other.col) {
      return false;
    }
    if (row != other.row) {
      return false;
    }
    return true;
  }

}