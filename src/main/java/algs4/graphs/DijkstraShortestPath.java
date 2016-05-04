package algs4.graphs;

import algs4.datastructures.ArrayList;

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
    public Graph(ArrayList<WeightedEdge<T>> edges) {
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
    // to handle cyclic graphs, we could use a 'Set<Vertex> visited' to mark the visited vertices. 
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
    if (edgeTo.containsKey(edge.to)) {
      int toDistance = distanceTo.get(edge.to);
      if (toDistance > fromDistance + edge.weight) {
        distanceTo.put(edge.to, fromDistance + edge.weight);
        edgeTo.put(edge.to, edge.from);
      }
    } else {
      // initial, we don't have to use infinity because we are using sets. 
      // if vertices don't exist, that's the equivalent of infinity.
      distanceTo.put(edge.to, fromDistance + edge.weight);
      edgeTo.put(edge.to, edge.from);
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
  
  public int getTotalDistance(T vertex){
    if (vertex == null || !distanceTo.containsKey(vertex)) {
      throw new IllegalArgumentException();
    }
    return distanceTo.get(vertex).intValue();
  }
}