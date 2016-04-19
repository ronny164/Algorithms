package edu.princeton.disjointsets;

/**
 * Basic Disjoint-sets data structure with path compression.
 * 
 * @author Ronny A. Pena
 */
public class DisjointSet {

  private int sets;
  private int[] tree;

  public DisjointSet(int capacity) {
    if (capacity < 2) {
      throw new IllegalArgumentException();
    }
    this.sets = capacity;
    tree = new int[capacity];
    for (int i = 0; i < capacity; i++) {
      tree[i] = i;
    }
  }

  public DisjointSet(int capacity, int[][] edges) {
    this(capacity);
    if (edges == null || edges.length < 1 || edges[0].length != 2) {
      throw new IllegalArgumentException();
    }
    for (int[] edge : edges) {
      union(edge[0], edge[1]);
    }
  }

  /**
   * @param p the element in the sets.
   * @return the root parent of element p.
   */
  public int findRoot(int p) {
    if (p < 0 || p >= tree.length) {
      throw new IllegalArgumentException();
    }
    int parent = tree[p];
    while (parent != tree[parent]) {
      tree[parent] = tree[tree[parent]]; // path compression
      parent = tree[parent];
    }
    return parent;
  }

  public void union(int p, int q) {
    int pRoot = findRoot(p);
    int qRoot = findRoot(q);
    if (pRoot != qRoot) {
      tree[pRoot] = qRoot;
      sets--;
    }
  }

  public boolean isConnected(int p, int q) {
    return findRoot(p) == findRoot(q);
  }

  /**
   * @return the number of components.
   */
  public int sets() {
    return sets;
  }
}
