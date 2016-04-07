package edu.princeton.graphs;

import static org.junit.Assert.assertEquals;

import edu.princeton.cs.algs4.Digraph;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Ronny A. Pena
 *
 */
public class TopologicalSort {

  public static Iterable<Integer> sort(Digraph graph) {
    if (graph == null || graph.V() == 0) {
      throw new IllegalArgumentException();
    }
    Deque<Integer> ordered = new LinkedList<>();
    List<Integer> startVertices = new LinkedList<>();
    boolean[] visited = new boolean[graph.V()];
    for (int root = graph.V() - 1; root >= 0; root--) {
      if (graph.indegree(root) == 0) {
        dfs(graph, root, visited, ordered);
      }
    }
    return ordered;
  }

  private static void dfs(Digraph graph, int v, boolean[] visited, Deque<Integer> ordered) {
    visited[v] = true;
    Iterable<Integer> adj = graph.adj(v);
    if (adj != null) {
      for (int w : adj) {
        if (!visited[w]) {
          dfs(graph, w, visited, ordered);
        }
      }
    }
    ordered.push(v);
  }
  
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
    assertEquals("[4, 0, 5, 2, 3, 7, 6, 1]", sort(graph).toString());
  }
}