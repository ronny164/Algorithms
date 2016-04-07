package edu.princeton.maxflowmincut;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.In;

import java.util.Map;

/**
 * @author Ronny A. Pena
 */
public class MaxFlowUtil {

  static void loadTeamData(In inputFile, Map<String, Integer> names, Team[] teams) {
    int n = teams.length;
    for (int team = 0; inputFile.hasNextLine(); team++) {
      String line = inputFile.readLine();
      Counter counter = new Counter("");
      String name = getNextWord(line, counter);
      int wins = getNextInt(line, counter);
      int loses = getNextInt(line, counter);
      int remaining = getNextInt(line, counter);
      int[] remainingPerTeam = new int[n];
      for (int i = 0; i < n; i++) {
        remainingPerTeam[i] = getNextInt(line, counter);
      }
      teams[team] = new Team(team, wins, loses, remaining, remainingPerTeam);
      names.put(name, team);
    }
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
   * @return The next int in the string. Separated by spaces.
   */
  static int getNextInt(String line, Counter counter) {
    return Integer.parseInt(getNextWord(line, counter));
  }

  
  /**
   * Combinations without Repetition.
   * @param n Things to choose from.
   * @param r Choosing r of them.
   * @return The number of possibilities.
   */
  static int choose(int n, int r) {
    int total = 1;
    for (int i = 0; i < r; i++) {
      total = (total * (n - i)) / (i + 1);
    }
    return total;
  }
  
}
