package algs4.graphs;

import static org.junit.Assert.assertEquals;
import algs4.datastructures.DoublyLinkedList;
import algs4.datastructures.MultiMap;
import algs4.datastructures.Queue;
import algs4.datastructures.Stack;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphSearch {

  public static <Vertex> Iterable<Vertex> findPathDfs(MultiMap<Vertex, Vertex> graph, Vertex start,
      Vertex end) {

    Stack<Vertex> stack = new Stack<>();
    Set<Vertex> visited = new HashSet<>();
    Map<Vertex, Vertex> edgeTo = new HashMap<>();
    stack.push(start);
    while (!stack.isEmpty()) {
      Vertex from = stack.pop();
      visited.add(from);
      Iterable<Vertex> adj = graph.getValues(from);
      if (adj != null) {
        for (Vertex to : adj) {
          if (!visited.contains(to)) {
            edgeTo.put(to, from);
            if (to.equals(end)) {
              return buildPath(edgeTo, end);
            }
            stack.push(to);
          }
        }
      }
    }
    return Collections.emptyList();
  }

  public static <Vertex> Iterable<Vertex> findPathBfs(MultiMap<Vertex, Vertex> graph, Vertex start,
      Vertex end) {
    Queue<Vertex> queue = new Queue<>();
    Set<Vertex> visited = new HashSet<>();
    Map<Vertex, Vertex> edgeTo = new HashMap<>();
    queue.enqueue(start);
    while (!queue.isEmpty()) {
      Vertex from = queue.dequeue();
      visited.add(from);
      Iterable<Vertex> adj = graph.getValues(from);
      if (adj != null) {
        for (Vertex to : adj) {
          if (!visited.contains(to)) {
            edgeTo.put(to, from);
            if (to.equals(end)) {
              return buildPath(edgeTo, end);
            }
            queue.enqueue(to);
          }
        }
      }
    }
    return Collections.emptyList();
  }

  private static <Vertex> Iterable<Vertex> buildPath(Map<Vertex, Vertex> edgeTo, Vertex end) {
    DoublyLinkedList<Vertex> path = new DoublyLinkedList<>();
    path.addFirst(end);
    Vertex to = end;
    while (true) {
      Vertex from = edgeTo.get(to);
      if (from == null) {
        return path;
      }
      path.addFirst(from);
      to = from;
    }
  }

  @Test
  public void testSearch() throws Exception {
    MultiMap<Character, Character> graph = new MultiMap<>();
    graph.put('S', 'X');
    graph.put('X', 'Y');
    graph.put('Y', 'T');
    graph.put('S', 'A');
    graph.put('A', 'B');
    graph.put('B', 'C');
    graph.put('C', 'D');
    graph.put('D', 'T');
    graph.put('T', 'S');
    assertEquals("[S, X, Y, T]", findPathBfs(graph, 'S', 'T').toString());
    assertEquals("[S, A, B, C, D, T]", findPathDfs(graph, 'S', 'T').toString());
  }
}
