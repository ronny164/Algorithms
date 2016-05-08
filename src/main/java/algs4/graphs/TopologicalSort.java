package algs4.graphs;

import algs4.datastructures.MultiMap;
import algs4.datastructures.Stack;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ronny A. Pena
 */
public class TopologicalSort {

  public static <V> Iterable<V> sort(MultiMap<V, V> graph) {
    if (graph == null || graph.size() == 0) {
      throw new IllegalArgumentException();
    }
    Stack<V> stack = new Stack<>();
    Set<V> visited = new HashSet<>();
    for (V root : graph.keySet()) {
      if (!visited.contains(root)) {
        dfs(root, graph, stack, visited);
      }
    }
    return stack;
  }

  private static <V> void dfs(V root, MultiMap<V, V> graph, Stack<V> stack, Set<V> visited) {
    visited.add(root);
    Iterable<V> adjVertices = graph.getValues(root);
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
