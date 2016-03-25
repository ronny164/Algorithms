package edu.princeton.heap;

/**
 * @author Ronny A. Pena
 */
public class SearchNode implements Comparable<SearchNode> {
  Board board;
  SearchNode previous;
  int moves;
  boolean twin;

  public SearchNode(Board board, SearchNode previous, int moves, boolean twin) {
    super();
    this.board = board;
    this.previous = previous;
    this.moves = moves;
    this.twin = twin;
  }

  public int getPriority() {
    return moves + board.manhattan();
  }

  @Override
  public int compareTo(SearchNode other) {
    return this.getPriority() - other.getPriority();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("priority  = ").append(getPriority()).append(System.lineSeparator());
    builder.append("moves     = ").append(moves).append(System.lineSeparator());
    builder.append("distance  = ").append(board.manhattan()).append(System.lineSeparator());
    builder.append("twin      = ").append(twin);
    builder.append(board).append(System.lineSeparator());
    return builder.toString();
  }
}
