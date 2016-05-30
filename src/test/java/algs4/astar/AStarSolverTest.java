package algs4.astar;

import algs4.astar.AStarSolver;
import algs4.astar.Board;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import org.junit.Ignore;
import org.junit.Test;

public class AStarSolverTest {

  @Test
  public void testBasic() {
    int[][] blocks = new int[][] { {0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
    Board initial = new Board(blocks);

    // solve the puzzle
    AStarSolver solver = new AStarSolver(initial);

    // print solution to standard output
    StdOut.println("Minimum number of moves = " + solver.moves());
    for (Board board : solver.solution()) {
      StdOut.println(board);
    }

    assertTrue(solver.isSolvable());
    assertEquals(4, solver.moves());
  }

  @Test
  public void testPuzzle2x2Unsolvable1() {
    int[][] blocks = new int[][] { {1, 0}, {2, 3}};
    Board initial = new Board(blocks);

    // solve the puzzle
    AStarSolver solver = new AStarSolver(initial);

    // print solution to standard output
    assertFalse(solver.isSolvable());
  }

  @Test
  public void testPuzzle2x206() {
    int[][] blocks = new int[][] { {0, 3}, {2, 1}};
    Board initial = new Board(blocks);

    // solve the puzzle
    AStarSolver solver = new AStarSolver(initial);

    // print solution to standard output
    StdOut.println("Minimum number of moves = " + solver.moves());
    for (Board board : solver.solution()) {
      StdOut.println(board);
    }

    assertTrue(solver.isSolvable());
    assertEquals(6, solver.moves());
  }

  @Test
  public void testManhattanPriority() {
    int[][] blocks = new int[][] { {8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    Board initial = new Board(blocks);
    assertEquals(10, initial.manhattan());
  }

  @Test
  public void testManhattanPriorityPuzzle27() {
    int[][] blocks = new int[][] { {5, 8, 7}, {1, 4, 6}, {3, 0, 2}};
    Board initial = new Board(blocks);
    assertEquals(17, initial.manhattan());
    assertEquals(7, initial.hamming());
  }

  @Test
  public void testManhattanPriorityPuzzle2x2Unsovable1() {
    int[][] blocks = new int[][] { {1, 0}, {2, 3}};
    Board initial = new Board(blocks);
    assertEquals(3, initial.manhattan());
    assertEquals(2, initial.hamming());
  }


  @Test
  public void testHammingPriority() {

    int[][] blocks = new int[][] { {8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    Board initial = new Board(blocks);
    assertEquals(5, initial.hamming());
  }

  @Test
  public void tesBoardToString() {
    int[][] blocks = new int[][] { {8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    Board initial = new Board(blocks);
    System.out.println(initial);
  }

  @Test
  public void testPuzzle7() {
    int[][] blocks = new int[][] { {1, 2, 3}, {0, 7, 6}, {5, 4, 8}};
    Board initial = new Board(blocks);

    // solve the puzzle
    AStarSolver solver = new AStarSolver(initial);

    // print solution to standard output
    StdOut.println("Minimum number of moves = " + solver.moves());
    for (Board board : solver.solution()) {
      StdOut.println(board);
    }

    assertTrue(solver.isSolvable());
    assertEquals(7, solver.moves());
  }

  @Test
  public void testPuzzle8() {
    int[][] blocks = new int[][] { {2, 3, 5}, {1, 0, 4}, {7, 8, 6}};
    Board initial = new Board(blocks);

    // solve the puzzle
    AStarSolver solver = new AStarSolver(initial);

    // print solution to standard output
    StdOut.println("Minimum number of moves = " + solver.moves());
    for (Board board : solver.solution()) {
      StdOut.println(board);
    }

    assertTrue(solver.isSolvable());
    assertEquals(8, solver.moves());
  }

  @Test
  public void testPuzzle3() {
    int[][] blocks = new int[][] { {2, 0}, {1, 3}};
    Board initial = new Board(blocks);

    // solve the puzzle
    AStarSolver solver = new AStarSolver(initial);

    // print solution to standard output
    StdOut.println("Minimum number of moves = " + solver.moves());
    for (Board board : solver.solution()) {
      StdOut.println(board);
    }

    assertTrue(solver.isSolvable());
    assertEquals(3, solver.moves());
  }

  @Test
  public void testPuzzle2x204() {
    int[][] blocks = new int[][] { {2, 3}, {1, 0}};
    Board initial = new Board(blocks);

    // solve the puzzle
    AStarSolver solver = new AStarSolver(initial);

    // print solution to standard output
    StdOut.println("Minimum number of moves = " + solver.moves());
    for (Board board : solver.solution()) {
      StdOut.println(board);
    }

    assertTrue(solver.isSolvable());
    assertEquals(4, solver.moves());
  }

  @Test
  @Ignore
  public void testAPuzzles() {

    // create initial board from file
    In in = new In("./src/test/resources/8puzzle/");
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        blocks[i][j] = in.readInt();
      }
    }
    Board initial = new Board(blocks);

    // solve the puzzle
    AStarSolver solver = new AStarSolver(initial);

    // print solution to standard output
    if (!solver.isSolvable()) {
      StdOut.println("No solution possible");
    } else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution()) {
        StdOut.println(board);
      }
    }
  }
}
