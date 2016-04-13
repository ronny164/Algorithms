package edu.princeton.shortestweightedpath;

/**
 * used internally by seam carver.
 * @author Ronny A. Pena
 */
class Vertex {
  int color;
  double energy;

  public Vertex(int color, double energy) {
    super();
    this.color = color;
    this.energy = energy;
  }
}
