package algs4.graphs;

import algs4.datastructures.MultiMap;

import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author Ronny A. Pena
 */
public class TopologicalSort {

  public static <V> Iterable<V> sort(MultiMap<V, V> graph) {
    if (graph == null || graph.size() == 0) {
      throw new IllegalArgumentException();
    }
    Deque<V> stack = new LinkedList<>();
    Set<V> visited = new HashSet<>();
    for (V root : graph.keySet()) {
      if (!visited.contains(root)) {
        dfs(root, graph, stack, visited);
      }
    }
    return stack;
  }

  private static <V> void dfs(V root, MultiMap<V, V> graph, Deque<V> stack, Set<V> visited) {
    visited.add(root);
    Collection<V> adjVertices = graph.getValues(root);
    if (adjVertices != null) { // has children.
      for (V w : adjVertices) {
        if (!visited.contains(w)) {
          dfs(w, graph, stack, visited);
        }
      }
    }
    stack.push(root);
  }
}
