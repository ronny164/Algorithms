package edu.princeton.maxflowmincut;

/**
 * Not part of any API. Used internally between baseball elimation methods. Package scoped.
 * @author Ronny A. Pena
 *
 */
class Team {
  int index;
  int wins;
  int loses;
  int remaining;
  int[] remainingPerTeam;
  boolean eliminated;
  boolean calculated;
  Iterable<String> eliminatedBy;
  public Team(int index, int wins, int loses, int remaining, int[] remainingPerTeam) {
    super();
    this.index = index;
    this.wins = wins;
    this.loses = loses;
    this.remaining = remaining;
    this.remainingPerTeam = remainingPerTeam;
  } 
}