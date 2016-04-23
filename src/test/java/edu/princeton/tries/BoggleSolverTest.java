package edu.princeton.tries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import edu.princeton.cs.algs4.In;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoggleSolverTest {

  @Test
  public void testBoard4x4() {
    char[][] a ={ 
        {'A', 'T', 'E', 'E'}, 
        {'A', 'P', 'Y', 'O'}, 
        {'T', 'I', 'N', 'U'}, 
        {'E', 'D', 'S', 'E'}};
    BoggleBoard board3 = new BoggleBoard(a);
    String[] dictionary = getDictionary("dictionary-algs4.txt");
    BoggleSolver solver = new BoggleSolver(dictionary);
    Iterable<String> allValidWords = solver.getAllValidWords(board3);
    assertStats(solver, allValidWords, 29, 33, Arrays.asList("AID", "DIE", "END", "ENDS", "YOU"));

    dictionary = getDictionary("dictionary-yawl.txt");
    solver = new BoggleSolver(dictionary);
    allValidWords = solver.getAllValidWords(board3);
    assertStats(solver, allValidWords, 204, 281, Arrays.asList("AID", "DIE", "END", "ENDS", "YOU"));
  }

  @Test
  public void testBoardQ() {
    String[] dictionary = getDictionary("dictionary-algs4.txt");
    char[][] a = { 
        {'S', 'N', 'R', 'T'}, 
        {'O', 'I', 'E', 'L'}, 
        {'E', 'Q', 'T', 'T'}, 
        {'R', 'S', 'A', 'T'}};
    BoggleBoard board3 = new BoggleBoard(a);
    BoggleSolver solver = new BoggleSolver(dictionary);
    Iterable<String> allValidWords = solver.getAllValidWords(board3);
    assertStats(solver, allValidWords, 29, 84,
        Arrays.asList("EQUATION", "EQUATIONS", "QUERIES", "QUESTION", "QUESTIONS", "TRIES"));

    dictionary = getDictionary("dictionary-yawl.txt");
    solver = new BoggleSolver(dictionary);
    allValidWords = solver.getAllValidWords(board3);
    assertStats(solver, allValidWords, 259, 621,
        Arrays.asList("SATIRE", "SEITEN", "RETIE", "REQUIRE", "QUITTER"));
  }

  @Test
  public void testBoardAqua() {
    String[] dictionary = getDictionary("dictionary-yawl.txt");
    BoggleBoard board3 = new BoggleBoard("./src/test/resources/boggle/board-aqua.txt");
    System.out.println(board3);
    BoggleSolver solver = new BoggleSolver(dictionary);
    Iterable<String> allValidWords = solver.getAllValidWords(board3);
    assertStats(solver, allValidWords, 1, 1, Arrays.asList("QUA"));
  }

  @Test
  public void testBoard1Point() {
    String[] dictionary = getDictionary("dictionary-yawl.txt");
    BoggleBoard board3 = new BoggleBoard("./src/test/resources/boggle/board-points1.txt");
    BoggleSolver solver = new BoggleSolver(dictionary);
    Iterable<String> allValidWords = solver.getAllValidWords(board3);
    assertStats(solver, allValidWords, 1, 1, Arrays.asList("TWP"));
  }

  @Test
  public void testBoard2Point() {
    String[] dictionary = getDictionary("dictionary-yawl.txt");
    BoggleBoard board3 = new BoggleBoard("./src/test/resources/boggle/board-points2.txt");
    BoggleSolver solver = new BoggleSolver(dictionary);
    Iterable<String> allValidWords = solver.getAllValidWords(board3);
    assertStats(solver, allValidWords, 2, 2, Arrays.asList("PHT"));
  }

  @Test
  public void testBoard5Point() {
    String[] dictionary = getDictionary("dictionary-yawl.txt");
    BoggleBoard board3 = new BoggleBoard("./src/test/resources/boggle/board-points5.txt");
    BoggleSolver solver = new BoggleSolver(dictionary);
    Iterable<String> allValidWords = solver.getAllValidWords(board3);
    assertStats(solver, allValidWords, 5, 5, Arrays.asList("PST", "TUTS"));
  }

  @Test
  public void testBoard100Point() {
    String[] dictionary = getDictionary("dictionary-yawl.txt");
    BoggleBoard board3 = new BoggleBoard("./src/test/resources/boggle/board-points100.txt");
    BoggleSolver solver = new BoggleSolver(dictionary);
    Iterable<String> allValidWords = solver.getAllValidWords(board3);
    assertStats(solver, allValidWords, 78, 100, Arrays.asList("REFLEX", "DIMMER"));
  }

  @Test
  public void testBoard500Point() {
    String[] dictionary = getDictionary("dictionary-yawl.txt");
    BoggleBoard board3 = new BoggleBoard("./src/test/resources/boggle/board-points500.txt");
    BoggleSolver solver = new BoggleSolver(dictionary);
    Iterable<String> allValidWords = solver.getAllValidWords(board3);
    assertStats(solver, allValidWords, 281, 500, Arrays.asList("STRAINS", "INSTAR"));
  }

  @Test
  public void testBoardOneRow() {
    String[] dictionary = getDictionary("dictionary-yawl.txt");
    BoggleBoard board3 =
        new BoggleBoard("./src/test/resources/boggle/board-antidisestablishmentarianisms.txt");
    BoggleSolver solver = new BoggleSolver(dictionary);
    System.out.println(board3);
    Iterable<String> allValidWords = solver.getAllValidWords(board3);
    assertStats(solver, allValidWords, 40, 172,
        Arrays.asList("TID", "ANTIDISESTABLISHMENTARIANISMS"));
  }

  private String[] getDictionary(String dictFileName) {
    String dictFullFileName = "./src/test/resources/boggle/" + dictFileName;
    In in4 = new In(new File(dictFullFileName));
    return in4.readAllStrings();
  }

  private void assertStats(BoggleSolver solver, Iterable<String> allValidWords, int expectedCount,
      int expectedScore, List<String> list) {
    int count = 0;
    int score = 0;
    List<String> output = new ArrayList<>();
    for (String word : allValidWords) {
      System.out.println(word);
      output.add(word);
      score += solver.scoreOf(word);
      count++;
    }
    System.out.println("count: " + count);
    System.out.println("Score = " + score);

    for (String string : list) {
      assertTrue(output.contains(string));
    }

    assertEquals(expectedCount, count);
    assertEquals(expectedScore, score);
  }
}
