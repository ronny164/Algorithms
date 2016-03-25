package edu.princeton.graphsearch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WordNetInvalidTest {

  @Test(expected = IllegalArgumentException.class)
  public void test3InvalidTwoRoots() {
    new WordNet("./src/test/resources/wordnet/synsets.txt",
        "./src/test/resources/wordnet/hypernyms3InvalidTwoRoots.txt");
  }

  @Test(expected = IllegalArgumentException.class)
  public void test3InvalidCycle() {
    new WordNet("./src/test/resources/wordnet/synsets.txt",
        "./src/test/resources/wordnet/hypernyms3InvalidCycle.txt");
  }

  @Test(expected = IllegalArgumentException.class)
  public void test6InvalidTwoRoots() {
    new WordNet("./src/test/resources/wordnet/synsets.txt",
        "./src/test/resources/wordnet/hypernyms6InvalidTwoRoots.txt");
  }

  @Test(expected = IllegalArgumentException.class)
  public void test6InvalidCycle() {
    new WordNet("./src/test/resources/wordnet/synsets.txt",
        "./src/test/resources/wordnet/hypernyms6InvalidCycle.txt");
  }

  @Test(expected = IllegalArgumentException.class)
  public void test6InvalidCycle_Path() {
    new WordNet("./src/test/resources/wordnet/synsets.txt",
        "./src/test/resources/wordnet/hypernyms6InvalidCycle+Path.txt");
  }

  @Test(expected = IllegalArgumentException.class)
  public void test8ManyAncestors() {
    new WordNet("./src/test/resources/wordnet/synsets6.txt",
        "./src/test/resources/wordnet/hypernyms8ManyAncestors.txt");
  }

  @Test
  public void test6TwoAncestors() {
    WordNet wordnet =
        new WordNet("./src/test/resources/wordnet/synsets6.txt",
            "./src/test/resources/wordnet/hypernyms6TwoAncestors.txt");
    assertEquals("a", wordnet.sap("b", "f"));
    assertEquals(2, wordnet.distance("b", "f"));
  }
}
