package edu.princeton.shortestpath;

import static org.junit.Assert.assertEquals;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.shortestancestor.ShortestAncestralPath;

import org.junit.Test;

public class SapTest {

  @Test
  public void testSapDiaGraph1() {
    Digraph graph = new Digraph(new In("./src/test/resources/wordnet/digraph1.txt"));
    ShortestAncestralPath sap = new ShortestAncestralPath(graph);
    assertEquals(1, sap.ancestor(3, 11));
    assertEquals(4, sap.length(3, 11));

    assertEquals(5, sap.ancestor(9, 12));
    assertEquals(3, sap.length(9, 12));

    assertEquals(4, sap.length(7, 2));
    assertEquals(0, sap.ancestor(7, 2));

    assertEquals(-1, sap.length(1, 6));
    assertEquals(-1, sap.ancestor(1, 6));
  }

  @Test
  public void testSapDiaGraph1_169() {
    Digraph graph = new Digraph(new In("./src/test/resources/wordnet/digraph1.txt"));
    ShortestAncestralPath sap = new ShortestAncestralPath(graph);
    assertEquals(0, sap.length(3, 3));
    assertEquals(3, sap.ancestor(3, 3));
  }

  @Test
  public void testSapDiaGraph2() {
    Digraph graph = new Digraph(new In("./src/test/resources/wordnet/digraph2.txt"));
    ShortestAncestralPath sap = new ShortestAncestralPath(graph);
    assertEquals(0, sap.ancestor(1, 5));
    assertEquals(2, sap.length(1, 5));
  }

  @Test
  public void testSapDiaGraph2_36() {
    Digraph graph = new Digraph(new In("./src/test/resources/wordnet/digraph2.txt"));
    ShortestAncestralPath sap = new ShortestAncestralPath(graph);
    assertEquals(4, sap.length(2, 0));
    assertEquals(0, sap.ancestor(2, 0));
  }

  @Test
  public void testSapDiaGraph3_2() {
    Digraph graph = new Digraph(new In("./src/test/resources/wordnet/digraph3.txt"));
    ShortestAncestralPath sap = new ShortestAncestralPath(graph);
    assertEquals(10, sap.ancestor(10, 7));
    assertEquals(3, sap.length(10, 7));
  }

  @Test
  public void testSapDiaGraph4_31() {
    Digraph graph = new Digraph(new In("./src/test/resources/wordnet/digraph4.txt"));
    ShortestAncestralPath sap = new ShortestAncestralPath(graph);
    assertEquals(3, sap.length(1, 4));
  }

  @Test
  public void testSapDiaGraph5_6() {
    Digraph graph = new Digraph(new In("./src/test/resources/wordnet/digraph5.txt"));
    ShortestAncestralPath sap = new ShortestAncestralPath(graph);
    assertEquals(-1, sap.length(15, 5));
    assertEquals(-1, sap.ancestor(15, 5));
  }

  @Test
  public void testSapDiaGraph6_27() {
    Digraph graph = new Digraph(new In("./src/test/resources/wordnet/digraph6.txt"));
    ShortestAncestralPath sap = new ShortestAncestralPath(graph);
    assertEquals(4, sap.length(5, 1));
  }

  @Test
  public void testSapDiaGraph9_1() {
    Digraph graph = new Digraph(new In("./src/test/resources/wordnet/digraph9.txt"));
    ShortestAncestralPath sap = new ShortestAncestralPath(graph);
    assertEquals(3, sap.length(0, 4));
  }
}
