package edu.princeton.graphs;

import static org.junit.Assert.assertEquals;

import edu.princeton.cs.algs4.Digraph;

import org.junit.Test;

public class TopogolicalSortTest {

  private static Digraph createGraph(int[][] adjList) {
    Digraph graph = new Digraph(adjList.length);
    int v = 0;
    for (int[] currentVertex : adjList) {
      for (int i = currentVertex.length - 1; i >= 0; i--) {
        int w = currentVertex[i];
        graph.addEdge(v, w);
      }
      v++;
    }
    return graph;
  }
  
  @Test
  public void testBasic() {
    Digraph graph = createGraph(new int[][]{{1,5},{}, {3,1,6,7},{7}, {0,5}, {6,1,2}, {}, {6}});
    System.out.println(graph);
    assertEquals("[4, 0, 5, 2, 3, 7, 6, 1]", TopologicalSort.sort(graph).toString());
  }
}
