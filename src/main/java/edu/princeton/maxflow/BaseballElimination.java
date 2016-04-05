package edu.princeton.maxflow;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Solving the baseball elimination using the maxflow algorithm.
 * 
 * Given the standings in a sports division at some point during the season, determine which teams 
 * have been mathematically eliminated from winning their division.
 *  
 * The baseball elimination problem.   In the baseball elimination problem, there is a division 
 * consisting of N teams. At some point during the season, team i has w[i] wins, l[i] losses, r[i] 
 * remaining games, and g[i][j] games left to play against team j. A team is mathematically 
 * eliminated if it cannot possibly finish the season in (or tied for) first place. The goal is to 
 * determine exactly which teams are mathematically eliminated. For simplicity, we assume that no 
 * games end in a tie (as is the case in Major League Baseball) and that there are no rainouts 
 * (i.e., every scheduled game is played).
 * 
 * @author Ronny A. Pena
 */
public class BaseballElimination {

  private static final class NetworkSetup {
    private FlowNetwork network;
    private double total;

    public NetworkSetup(FlowNetwork network, double total) {
      super();
      this.network = network;
      this.total = total;
    }
  }

  private Map<String, Integer> teamNames;
  private int[] wins;
  private int[] loses;
  private int[] remainingPerTeam;
  private int[][] remainingTeamPairs;
  private boolean[] eliminated;
  private Map<Integer, List<String>> certificates;

  /**
   * Create a baseball division from given filename in format specified below.
   * @param filename The file input data.
   */
  public BaseballElimination(String filename) {

    In inputFile = new In(filename);
    int n = Integer.parseInt(inputFile.readLine());
    teamNames = new LinkedHashMap<>();
    wins = new int[n];
    loses = new int[n];
    remainingPerTeam = new int[n];
    remainingTeamPairs = new int[n][n];
    eliminated = new boolean[n];
    certificates = new HashMap<>();

    for (int team = 0; inputFile.hasNextLine(); team++) {
      String line = inputFile.readLine();
      Counter counter = new Counter("");
      teamNames.put(getNextWord(line, counter), team);
      wins[team] = Integer.parseInt(getNextWord(line, counter));
      loses[team] = Integer.parseInt(getNextWord(line, counter));
      remainingPerTeam[team] = Integer.parseInt(getNextWord(line, counter));
      for (int i = 0; i < n; i++) {
        remainingTeamPairs[team][i] = Integer.parseInt(getNextWord(line, counter));
      }
    }
    computeMaxFlow(n);
  }

  /**
   * Computes the max flow (mathematical eliminations) for team team.
   * @param vertices The number of teams.
   */
  private void computeMaxFlow(int vertices) {
    int requiredNetworkVertices = choose(vertices - 1, 2) + vertices + 2;
    int source = vertices;
    int sink = vertices + 1;
    for (int team = 0; team < vertices; team++) {
      NetworkSetup setup = buildNetwork(source, team, sink, vertices, requiredNetworkVertices);
      FordFulkerson maxflow = new FordFulkerson(setup.network, source, sink);
      eliminated[team] = maxflow.value() < setup.total;
      if (eliminated[team]) {
        createCertificateCut(maxflow, team);
      }
    }
  }

  /**
   * Builds the flow network.
   */
  private NetworkSetup buildNetwork(int source, int currentTeam, int sink, int vertices,
      int requiredNetworkVertices) {
    int gameVertex = vertices + 2;
    double total = 0;
    FlowNetwork network = new FlowNetwork(requiredNetworkVertices);
    for (int team1 = 0; team1 < vertices; team1++) {
      if (team1 != currentTeam) {
        for (int team2 = team1 + 1; team2 < vertices; team2++) {
          if (team1 != team2 && team2 != currentTeam) {
            int reminder = addSourceEdges(network, source, gameVertex, team1, team2);
            total += reminder;
            gameVertex++;
          }
        }
        addSinkEdges(network, sink, currentTeam, team1);
      }
    }
    return new NetworkSetup(network, total);
  }

  private int addSourceEdges(FlowNetwork network, int source, int gameVertex, int team1, int team2) {
    int reminder = remainingTeamPairs[team1][team2];
    network.addEdge(new FlowEdge(source, gameVertex, reminder));
    network.addEdge(new FlowEdge(gameVertex, team1, Double.POSITIVE_INFINITY));
    network.addEdge(new FlowEdge(gameVertex, team2, Double.POSITIVE_INFINITY));
    return reminder;
  }

  private void addSinkEdges(FlowNetwork network, int sink, int mainTeam, int otherTeam) {
    int avaliableToWin = wins[mainTeam] + remainingPerTeam[mainTeam] - wins[otherTeam];
    if (avaliableToWin < 0) {
      network.addEdge(new FlowEdge(otherTeam, sink, 0)); // eliminated.
    } else {
      network.addEdge(new FlowEdge(otherTeam, sink, avaliableToWin));
    }
  }

  private void createCertificateCut(FordFulkerson maxflow, int currentTeam) {
    LinkedList<String> eliminationName = new LinkedList<>();
    for (Entry<String, Integer> entry : teamNames.entrySet()) {
      if (maxflow.inCut(entry.getValue())) { // part of the cut
        eliminationName.push(entry.getKey());
      }
    }
    certificates.put(currentTeam, eliminationName);
  }

  /**
   * Combinations without Repetition.
   * @param n Things to choose from.
   * @param r Choosing r of them.
   * @return The number of possibilities.
   */
  private static int choose(int n, int r) {
    int total = 1;
    for (int i = 0; i < r; i++) {
      total = (total * (n - i)) / (i + 1);
    }
    return total;
  }

  /**
   * @return The next word in the string. Separated by spaces.
   */
  static String getNextWord(String line, Counter counter) {
    if (counter.tally() >= line.length()) {
      throw new IllegalArgumentException("Reached the end of the string.");
    }
    // move to the start of the next word.
    while (counter.tally() < line.length() && line.charAt(counter.tally()) == ' ') {
      counter.increment();
    }
    int start = counter.tally();

    // find the end of a word
    while (counter.tally() < line.length() && line.charAt(counter.tally()) != ' ') {
      counter.increment();
    }
    return line.substring(start, counter.tally());
  }

  /**
   * @return The number of teams.
   */
  public int numberOfTeams() {
    return teamNames.size();
  }

  /**
   * @return All the teams.
   */
  public Iterable<String> teams() {
    return teamNames.keySet();
  }

  /**
   * @param team The selected team.
   * @return The number of wins for given team.
   */
  public int wins(String team) {
    if (team == null || !teamNames.containsKey(team)) {
      throw new IllegalArgumentException();
    }
    return wins[teamNames.get(team)];
  }

  /**
   * @param team The selected team.
   * @return The number of losses for given team.
   */
  public int losses(String team) {
    if (team == null || !teamNames.containsKey(team)) {
      throw new IllegalArgumentException();
    }
    return loses[teamNames.get(team)];
  }

  /**
   * @param team The selected team.
   * @return number of remaining games for given team.
   */
  public int remaining(String team) {
    if (team == null || !teamNames.containsKey(team)) {
      throw new IllegalArgumentException();
    }
    return remainingPerTeam[teamNames.get(team)];
  }

  /**
   * @param team1 The selected team.
   * @param team2 The other team.
   * @return number of remaining games between team1 and team2.
   */
  public int against(String team1, String team2) {
    if (team1 == null || !teamNames.containsKey(team1)) {
      throw new IllegalArgumentException();
    }
    if (team2 == null || !teamNames.containsKey(team2)) {
      throw new IllegalArgumentException();
    }
    return remainingTeamPairs[teamNames.get(team1)][teamNames.get(team2)];
  }

  /**
   * @param team The selected team.
   * @return is given team eliminated?
   */
  public boolean isEliminated(String team) {
    if (team == null || !teamNames.containsKey(team)) {
      throw new IllegalArgumentException();
    }
    return eliminated[teamNames.get(team)];
  }

  /**
   * @param team The selected team.
   * @return subset R of teams that eliminates given team; null if not eliminated
   */
  public Iterable<String> certificateOfElimination(String team) {
    if (isEliminated(team)) {
      int teamIndex = teamNames.get(team);
      return certificates.get(teamIndex);
    }
    return null;
  }

  /**
   * Main driver.
   */
  public static void main(String[] args) {
    BaseballElimination division = new BaseballElimination(args[0]);
    for (String team : division.teams()) {
      if (division.isEliminated(team)) {
        StdOut.print(team + " is eliminated by the subset R = { ");
        for (String t : division.certificateOfElimination(team)) {
          StdOut.print(t + " ");
        }
        StdOut.println("}");
      } else {
        StdOut.println(team + " is not eliminated");
      }
    }
  }
}
