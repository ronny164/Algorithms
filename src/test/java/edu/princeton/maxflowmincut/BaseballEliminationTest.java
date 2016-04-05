package edu.princeton.maxflowmincut;

import static org.junit.Assert.assertEquals;
import edu.princeton.cs.algs4.Counter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseballEliminationTest {

  private static final class Result {
    private int count = 0;
    private List<String> teams = new ArrayList<>();
    private Map<String, Iterable<String>> eliminations = new HashMap<>();
  }

  private Result runFile(String fileName) {
    System.out.println(fileName);
    BaseballElimination problem = new BaseballElimination(fileName);
    Result result = new Result();
    for (String team : problem.teams()) {
      if (problem.isEliminated(team)) {
        Iterable<String> cert = problem.certificateOfElimination(team);
        System.out.println(team + ": " + cert + ", eliminated: " + problem.isEliminated(team));
        result.teams.add(team);
        result.eliminations.put(team, cert);
        result.count++;
      }
    }
    System.out.println();
    return result;
  }

  @Test
  public void testNextWord() {
    Counter counter = new Counter("");
    String line = "hi there, you know me?";
    assertEquals("hi", BaseballElimination.getNextWord(line, counter));
    assertEquals("there,", BaseballElimination.getNextWord(line, counter));
    assertEquals("you", BaseballElimination.getNextWord(line, counter));
    assertEquals("know", BaseballElimination.getNextWord(line, counter));
    assertEquals("me?", BaseballElimination.getNextWord(line, counter));
  }

  /**
   * <pre>
   * 0              1  2  4  5 6 7 8
   * Atlanta       83 71  8  0 1 6 1
   * Philadelphia  80 79  3  1 0 0 2
   * New_York      78 78  6  6 0 0 0
   * Montreal      77 82  3  1 2 0 0
   * </pre>
   */
  @Test
  public void testTeams4() {
    Result result = runFile("./src/test/resources/baseball/teams4.txt");
    assertEquals(2, result.count);
    assertEquals("[Philadelphia, Montreal]", result.teams.toString());
    assertEquals("{Montreal=[Atlanta], Philadelphia=[Atlanta, New_York]}",
        result.eliminations.toString());
  }

  @Test
  public void testTeams4a() {
    Result result = runFile("./src/test/resources/baseball/teams4a.txt");
    assertEquals(2, result.count);
    assertEquals("[Ghaddafi, Bin_Ladin]", result.teams.toString());
    assertEquals("{Ghaddafi=[CIA, Obama], Bin_Ladin=[Obama]}",
        result.eliminations.toString());
  }

  @Test
  public void testTeams4b() {
    Result result = runFile("./src/test/resources/baseball/teams4b.txt");
    assertEquals(3, result.count);
    assertEquals("[Hufflepuff, Ravenclaw, Slytherin]", result.teams.toString());
  }

  @Test
  public void testTeams5() {
    Result result = runFile("./src/test/resources/baseball/teams5.txt");
    assertEquals(1, result.count);
    assertEquals("[Detroit]", result.teams.toString());
    assertEquals("{Detroit=[New_York, Baltimore, Boston, Toronto]}", result.eliminations.toString());
  }

  @Test
  public void testTeams7() {
    Result result = runFile("./src/test/resources/baseball/teams7.txt");
    assertEquals(2, result.count);
    assertEquals("[Ireland, China]", result.teams.toString());
  }

  @Test
  public void testTeams32() {
    Result result = runFile("./src/test/resources/baseball/teams32.txt");
    assertEquals(9, result.count);
    assertEquals("[Team2, Team3, Team12, Team13, Team14, Team17, Team25, Team28, Team29]",
        result.teams.toString());
  }

  @Test
  public void teamAllGames() {
    Result result = runFile("./src/test/resources/baseball/teams12-allgames.txt");
    assertEquals(10, result.count);
    assertEquals("[Team0, Team1, Team2, Team3, Team5, Team6, Team7, Team8, Team9, Team10]",
        result.teams.toString());
  }

}
