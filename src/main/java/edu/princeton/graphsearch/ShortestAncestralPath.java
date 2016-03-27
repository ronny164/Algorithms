package edu.princeton.graphsearch;

import edu.princeton.cs.algs4.Digraph;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * An ancestral path between two vertices v and w in a directed graph is a directed path from v to
 * a common ancestor x, together with a directed path from w to the same ancestor x. A shortest
 * ancestral path is an ancestral path of minimum total length.
 *
 * @author Ronny A. Pena
 */
public class ShortestAncestralPath {

  private static final class Vertex {
    private int index;
    private int[] distanceTo;

    public Vertex(int index, int[] distTo) {
      super();
      this.index = index;
      this.distanceTo = distTo;
    }
  }

  private Digraph graph;
  private int[] distanceToV;
  private int[] distanceToW;
  private Collection<Integer> dirty;

  /**
   * Constructor takes a directed graph (not necessarily a DAG).
   *
   * @param graph The graph.
   */
  public ShortestAncestralPath(Digraph graph) {
    if (graph == null) {
      throw new NullPointerException();
    }
    this.graph = new Digraph(graph);
  }

  /**
   * Generates the length of shortest ancestral path between v and w.
   *
   * @param vertexV The v vertex.
   * @param vertexW The w vertex.
   * @return The length of the ancestor or -1 if no such path.
   */
  public int length(int vertexV, int vertexW) {
    return length(Arrays.asList(vertexV), Arrays.asList(vertexW));
  }

  /**
   * Generates the length of shortest ancestral path between any vertex in v and any vertex in w.
   *
   * @param verticesV The v vertices.
   * @param verticesW The w vertices.
   * @return The length of the ancestor or -1 if no such path.
   */
  public int length(Iterable<Integer> verticesV, Iterable<Integer> verticesW) {
    int ancestor = ancestor(verticesV, verticesW);
    if (ancestor == -1) {
      return -1;
    }
    return distanceToV[ancestor] + distanceToW[ancestor];
  }

  /**
   * Generates a common ancestor of v and w that participates in a shortest ancestral path.
   *
   * @param vertexV The v vertex.
   * @param vertexW The w vertex.
   * @return The ancestor or -1 if no such ancestor exist.
   */
  public int ancestor(int vertexV, int vertexW) {
    return ancestor(Arrays.asList(vertexV), Arrays.asList(vertexW));
  }

  /**
   * Generates a common ancestor that participates in shortest ancestral path.
   *
   * @param verticesV The v vertices.
   * @param verticesW The w vertices.
   * @return The ancestor or -1 if no such ancestor exist.
   */
  public int ancestor(Iterable<Integer> verticesV, Iterable<Integer> verticesW) {
    if (verticesV == null || verticesW == null) {
      throw new NullPointerException();
    }
    return bsf(verticesV, verticesW);
  }

  private void reset() {

    // lazy load the fields.
    if (dirty == null) {
      dirty = new HashSet<>();
    }

    if (this.distanceToV == null) {
      this.distanceToV = new int[graph.V()];
      Arrays.fill(distanceToV, -1);
    }

    if (this.distanceToW == null) {
      this.distanceToW = new int[graph.V()];
      Arrays.fill(distanceToW, -1);
    }

    // Optimization 4. Instead of resetting all vertices, only reset the ones that were used.
    for (int vertex : dirty) {
      distanceToV[vertex] = -1;
      distanceToW[vertex] = -1;
    }
    dirty.clear();
  }

  private void addVertex(Iterable<Integer> vertices, Queue<Vertex> queue, int[] distanceTo) {
    
    //adding all the vertices for path search..
    for (int vertex : vertices) {
      if (vertex < 0 || vertex >= graph.V()) {
        throw new IndexOutOfBoundsException();
      }
      distanceTo[vertex] = 0; // distance and visited
      dirty.add(vertex);
      queue.add(new Vertex(vertex, distanceTo));
    }
  }

  private int bsf(Iterable<Integer> verticesV, Iterable<Integer> verticesW) {

    reset();
    Queue<Vertex> queue = new LinkedList<>();
    addVertex(verticesV, queue, distanceToV);
    addVertex(verticesW, queue, distanceToW);
    int ancestor = -1;
    int minDistance = Integer.MAX_VALUE;

    while (!queue.isEmpty()) {
      Vertex vertex = queue.remove();
      int[] distanceTo = vertex.distanceTo;
      int vertexIndex = vertex.index;

      // Optimization 1, keep track of the ancestor with the minimal distance.
      if (distanceToV[vertexIndex] != -1 && distanceToW[vertexIndex]  != -1) {
        int currentDistance = distanceToV[vertexIndex] + distanceToW[vertexIndex];
        if (currentDistance < minDistance) {
          ancestor = vertexIndex;
          minDistance = currentDistance;

          // Optimization 2. If the distance is zero, It means that two nodes are the same.
          if (currentDistance == 0) {
            return ancestor;
          }
        }
      }

      // Optimization 3. If the current distance is getting bigger,
      // we are passing over the shortest ancestor.
      if (distanceTo[vertexIndex] >= minDistance) {
        return ancestor;
      }

      Iterable<Integer> adjs = graph.adj(vertexIndex);
      if (adjs != null) {
        for (int adj : adjs) {
          if (distanceTo[adj] == -1) { //not visited
            distanceTo[adj] = distanceTo[vertexIndex] + 1;
            dirty.add(adj);
            queue.add(new Vertex(adj, distanceTo));
          }
        }
      }
    }
    return ancestor;
  }
}