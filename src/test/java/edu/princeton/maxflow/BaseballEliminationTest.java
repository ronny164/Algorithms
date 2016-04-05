package edu.princeton.maxflow;

import static org.junit.Assert.assertEquals;
import edu.princeton.cs.algs4.Counter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class BaseballEliminationTest {

  private static final class Result {
    private int count = 0;
    private List<String> teams = new ArrayList<>();

  }

  private Result runFile(String fileName) {
    System.out.println(fileName);
    BaseballElimination problem = new BaseballElimination(fileName);
    Result result = new Result();
    for (String team : problem.teams()) {
      if (problem.isEliminated(team)) {
        System.out.println(team + ": " + problem.certificateOfElimination(team) + ", eliminated: "
            + problem.isEliminated(team));
        result.teams.add(team);
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
    Result print = runFile("./src/test/resources/baseball/teams4.txt");
    assertEquals(2, print.count);
    assertEquals("[Philadelphia, Montreal]", print.teams.toString());
  }

  @Test
  public void testTeams4a() {
    Result print = runFile("./src/test/resources/baseball/teams4a.txt");
    assertEquals(2, print.count);
    assertEquals("[Ghaddafi, Bin_Ladin]", print.teams.toString());
  }

  /**
   * <pre>
   * 
   *      teams4b.txt
   *      fails for Hufflepuff
   *      - student   isEliminated() = false
   *      - reference isEliminated() = true
   *      
   *       teams4b.txt
   *        - false negative in certificate of elimination for 'Hufflepuff'
   *        - student   certificate of elimination = null
   *        - reference certificate of elimination = { Gryffindor }
   * </pre>
   */
  @Test
  public void testTeams4b() {
    Result print = runFile("./src/test/resources/baseball/teams4b.txt");
    assertEquals(1, print.count);
    assertEquals("[Hufflepuff]", print.teams.toString());
  }

  @Test
  public void testTeams5() {
    Result print = runFile("./src/test/resources/baseball/teams5.txt");
    assertEquals(1, print.count);
    assertEquals("[Detroit]", print.teams.toString());
  }

  @Test
  public void testTeams7() {
    Result print = runFile("./src/test/resources/baseball/teams7.txt");
    assertEquals(2, print.count);
    assertEquals("[Ireland, China]", print.teams.toString());
  }

  @Test
  public void testTeams32() {
    Result print = runFile("./src/test/resources/baseball/teams32.txt");
    assertEquals(9, print.count);
    assertEquals("[Team2, Team3, Team12, Team13, Team14, Team17, Team25, Team28, Team29]",
        print.teams.toString());
  }

  /**
   * <pre>
   *   teams12-allgames.txt
   *      fails for Team0
   *      - student   isEliminated() = false
   *      - reference isEliminated() = true
   *      
   *   teams12-allgames.txt
   *      - false negative in certificate of elimination for 'Team0'
   *      - student   certificate of elimination = null
   *      - reference certificate of elimination = { Team1 }
   * ==> FAILED
   * <pre>
   */
  @Test
  public void teamAllGames() {
    Result print = runFile("./src/test/resources/baseball/teams12-allgames.txt");
    assertEquals(1, print.count);
    assertEquals("[Team1]", print.teams.toString());
  }

}
