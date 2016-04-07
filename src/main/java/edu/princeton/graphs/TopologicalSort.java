package edu.princeton.graphs;

import edu.princeton.cs.algs4.Digraph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Ronny A. Pena
 */
public class TopologicalSort {

  public static Iterable<Integer> sort(Digraph graph) {
    if (graph == null || graph.V() == 0) {
      throw new IllegalArgumentException();
    }
    Deque<Integer> ordered = new LinkedList<>();
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
}