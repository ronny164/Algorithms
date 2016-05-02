package algs4.graphs;

import static org.junit.Assert.assertEquals;
import algs4.datastructures.ArrayList;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Using a regular Heap instead of a IndexHeap. 
 * @author Ronny A. Pena
 */
public class DijkstraShortestPath<T> {

  static class Graph<T> {
    private HashMap<T, ArrayList<WeightedEdge<T>>> adjList;
    private Graph(ArrayList<WeightedEdge<T>> edges) {
      if (edges == null || edges.size() == 0) {
        throw new IllegalArgumentException();
      }
      adjList = new HashMap<>();
      for (WeightedEdge<T> weightedEdge : edges) {
        ArrayList<WeightedEdge<T>> adj = adjList.get(weightedEdge.from);
        if (adj == null) {
          adj = new ArrayList<>();
          adjList.put(weightedEdge.from, adj);
        }
        adj.add(weightedEdge);
      }
    }
  }
  
  static class WeightedEdge<T> {
    private T from;
    private T to;
    private int weight;
    public WeightedEdge(T from, T to, int weight) {
      super();
      this.from = from;
      this.to = to;
      this.weight = weight;
    }

  }
  
  private HashMap<T, Integer> distanceTo = new HashMap<>();
  private HashMap<T, T> edgeTo = new HashMap<>();

  public Iterable<T> computeShorteshPath(Graph<T> graph, T start, T target) {
    if (graph == null || graph.adjList.isEmpty() || start == null || target == null) {
      throw new IllegalArgumentException();
    }
    distanceTo.clear();
    distanceTo.put(start, 0);
    edgeTo.clear();
    edgeTo.put(start, null);
    PriorityQueue<WeightedEdge<T>> pq = new PriorityQueue<>((o1, o2) -> {
      return o1.weight - o2.weight;
    });
    addEdges(graph.adjList.get(start), pq);
    while (!pq.isEmpty()) {
      WeightedEdge<T> smallest = pq.remove();
      addEdges(graph.adjList.get(smallest.to), pq);
    }
    return buildPath(target);
  }

  private Iterable<T> buildPath(T target) {
    LinkedList<T> path = new LinkedList<>();
    T current = target;
    path.push(current);
    while (current != null) {
      current = edgeTo.get(current);
      if (current == null) {
        break;
      }
      path.push(current);
    }
    return path;
  }

  private void relax(WeightedEdge<T> edge) {
    int fromDistance = distanceTo.get(edge.from);
    if (!edgeTo.containsKey(edge.to)) {
      distanceTo.put(edge.to, fromDistance + edge.weight);
      edgeTo.put(edge.to, edge.from);
    } else {
      int toDistance = distanceTo.get(edge.to);
      if (toDistance > fromDistance + edge.weight) {
        distanceTo.put(edge.to, fromDistance + edge.weight);
        edgeTo.put(edge.to, edge.from);
      }
    }
  }

  private void addEdges(ArrayList<WeightedEdge<T>> edges, PriorityQueue<WeightedEdge<T>> pq) {
    if(edges != null) {
      for (int i = 0; i < edges.size(); i++) {
        WeightedEdge<T> edge = edges.get(i);
        pq.add(edge);
        relax(edge);
      }
    }
  }

/**
<pre>
    (A)<-----16-----(B)<-----1------(C)------34---->(D) 
     ^              ^^              ^^\              |  
     |             / |             / | \             |  
     |            /  |            /  |  \            |  
     |           /   |           /   |   \           |  
     |          /    |          /    |    \          |  
     |         /     |         /     |     \         |  
     |        /      |        /      |      \        |  
     20      6       12      21      7       71      30 
     |      /        |      /        |        \      |  
     |     /         |     /         |         \     |  
     |    /          |    /          |          \    |  
     |   /           |   /           |           \   |  
     |  /            |  /            |            \  |  
     | /             | /             |             \ |  
     |/              |/              |              vv  
    (E)<-----4------(F)------11---->(G)------80---->(H) 
</pre>
 */
  @Test
  public void testHomeworkAssignment() {
    ArrayList<WeightedEdge<Character>> edges =
        ArrayList.asList(
            new WeightedEdge<>('B', 'A', 16),
            new WeightedEdge<>('C', 'B', 01),
            new WeightedEdge<>('C', 'D', 34),
            new WeightedEdge<>('C', 'H', 71),
            new WeightedEdge<>('D', 'H', 30),
            new WeightedEdge<>('E', 'A', 20),
            new WeightedEdge<>('E', 'B', 06),
            new WeightedEdge<>('F', 'B', 12),
            new WeightedEdge<>('F', 'C', 21),
            new WeightedEdge<>('F', 'E', 04),
            new WeightedEdge<>('F', 'G', 11),
            new WeightedEdge<>('G', 'C', 07),
            new WeightedEdge<>('G', 'H', 80));
    Graph<Character> graph = new Graph<>(edges);
    DijkstraShortestPath<Character> algo = new DijkstraShortestPath<>();
    assertEquals("[F, G, C, D, H]", algo.computeShorteshPath(graph, 'F', 'H').toString());
    assertEquals(82, algo.distanceTo.get('H').intValue());
  }

  @Test
  public void testLectureExample() {
    ArrayList<WeightedEdge<Integer>> edges =
        ArrayList.asList(
            new WeightedEdge<>(0, 1,  5),
            new WeightedEdge<>(0, 4,  9),
            new WeightedEdge<>(0, 7,  8),
            new WeightedEdge<>(1, 2, 12),
            new WeightedEdge<>(1, 3, 15),
            new WeightedEdge<>(1, 7,  4),
            new WeightedEdge<>(2, 3, 03),
            new WeightedEdge<>(2, 6, 11),
            new WeightedEdge<>(3, 6,  9),
            new WeightedEdge<>(4, 5, 04),
            new WeightedEdge<>(4, 6, 20),
            new WeightedEdge<>(4, 7, 05),
            new WeightedEdge<>(5, 2, 01),
            new WeightedEdge<>(5, 6, 13),
            new WeightedEdge<>(7, 5,  6),
            new WeightedEdge<>(7, 2,  7));
    Graph<Integer> graph = new Graph<>(edges);
    DijkstraShortestPath<Integer> algo = new DijkstraShortestPath<>();
    assertEquals("[0, 4, 5, 2, 6]", algo.computeShorteshPath(graph, 0, 6).toString());
    assertEquals(25, algo.distanceTo.get(6).intValue());
  }
}