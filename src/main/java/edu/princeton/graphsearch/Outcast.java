package edu.princeton.graphsearch;

/**
 * Finds words that are outcast in the  wordNet.
 * @author Ronny A. Pena
 */
public class Outcast {

  private final WordNet wordNet;

  /**
   * Constructor for Outcast.
   * @param wordnet the wordnet object.
   */
  public Outcast(WordNet wordnet) {
    if (wordnet == null) {
      throw new NullPointerException();
    }
    this.wordNet = wordnet;
  }

  /**
   * Returns the outcast for the list of words.
   * @param nouns Given an array of WordNet nouns.
   * @return Returns an outcast.
   */
  public String outcast(String[] nouns) {
    if (nouns == null) {
      throw new NullPointerException();
    }
    if (nouns.length == 0) {
      throw new IllegalArgumentException();
    }
    
    int[] distances = new int[nouns.length];
    int maxDistanceSum = Integer.MIN_VALUE;
    String maxWord = null;
    for (int i = 0; i < nouns.length; i++) {
      String wordA = nouns[i];
      for (int j = 0; j < nouns.length; j++) {
        String wordB = nouns[j];
        if (i != j && wordNet.isNoun(wordA) && wordNet.isNoun(wordB)) {
          int distance = wordNet.distance(wordA, wordB);
          distances[i] += distance;
        }
      }
      // keep the one with the maximum distance.
      if (distances[i] > maxDistanceSum) {
        maxDistanceSum = distances[i];
        maxWord = wordA;
      }
    }
    return maxWord;
  }
}