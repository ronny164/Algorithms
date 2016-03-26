package edu.princeton.heap;

/**
 * This class is only used by the solver. Allowing package fields since is not public.
 * 
 * @author Ronny A. Pena
 */
class SearchNode implements Comparable<SearchNode> {
  
  Board board;
  SearchNode previous;
  int moves;
  boolean twin;

  SearchNode(Board board, SearchNode previous, int moves, boolean twin) {
    super();
    this.board = board;
    this.previous = previous;
    this.moves = moves;
    this.twin = twin;
  }

  int getPriority() {
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