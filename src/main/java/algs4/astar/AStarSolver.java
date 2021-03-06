package algs4.astar;

import algs4.datastructures.DoublyLinkedList;
import java.util.PriorityQueue;

/**
 * @author Ronny A. Pena
 */
public class AStarSolver {

  private SearchNode solution;
  private boolean solvable;

  /**
   * Find a solution to the initial board (using the A* algorithm).
   *
   * @param initial The initial board.
   */
  public AStarSolver(Board initial) {

    if (initial == null) {
      throw new NullPointerException();
    }

    PriorityQueue<SearchNode> minQueue = new PriorityQueue<>();
    minQueue.add(new SearchNode(initial, null, 0, false));
    minQueue.add(new SearchNode(initial.twin(), null, 0, true));

    while (!minQueue.isEmpty()) {
      SearchNode searchNode = minQueue.remove();
      for (Board neighbor : searchNode.board.neighbors()) {
        if (searchNode.previous == null || !searchNode.previous.board.equals(neighbor)) {
          minQueue.add(new SearchNode(neighbor, searchNode, searchNode.moves + 1, searchNode.twin));
        }
      }
      // did we find a solution?
      if (searchNode.board.isGoal()) {
        solvable = !searchNode.twin;
        solution = searchNode;
        return;
      }
    }
  }

  /**
   * Is the initial board solvable?
   * @return The answer. 
   */
  public boolean isSolvable() {
    return this.solvable;
  }

  /**
   * @return The minimal number of moves to solve initial board; -1 if unsolvable.
   */
  public int moves() {
    if (this.solvable) {
      return this.solution.moves;
    }
    return -1;
  }

  /**
   * @return The sequence of boards in a shortest solution; null if none.
   */
  public Iterable<Board> solution() {
    if (this.solvable) {
      DoublyLinkedList<Board> path = new DoublyLinkedList<>();
      SearchNode temp = this.solution;
      if (temp != null) {
        SearchNode node = temp;
        while (node != null) {
          path.addFirst(node.board);
          node = node.previous; // back tracking
        }
      }
      return path;
    }
    return null;
  }
}
