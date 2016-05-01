package algs4.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class PointSET {

  private Set<Point2D> setOfPoints;

  public PointSET() { // construct an empty set of points
    setOfPoints = new HashSet<>();
  }

  public boolean isEmpty() { // is the set empty?
    return setOfPoints.isEmpty();
  }

  public int size() { // number of points in the set
    return setOfPoints.size();
  }

  public void insert(Point2D point) { // add the point to the set (if it is not already in the set)
    if (point == null) {
      throw new NullPointerException();
    }
    setOfPoints.add(point);
  }

  public boolean contains(Point2D point) { // does the set contain point?
    if (point == null) {
      throw new NullPointerException();
    }
    return setOfPoints.contains(point);
  }

  public void draw() { // draw all points to standard draw
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setPenRadius(.01);
    for (Point2D point2d : setOfPoints) {
      // StdDraw.point(point2d.x(), point2d.y());
      point2d.draw();
      // point2d.
    }
  }

  public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle
    if (rect == null) {
      throw new NullPointerException();
    }
    List<Point2D> selection = new LinkedList<>();
    for (Point2D point2d : setOfPoints) {
      if (rect.contains(point2d)) {
        selection.add(point2d);
      }
    }
    return selection;
  }

  public Point2D nearest(Point2D point) { // a nearest neighbor in the set to point; null if the set
    // is empty
    if (point == null) {
      throw new NullPointerException();
    }
    Point2D nearest = null;
    double min = Double.MAX_VALUE;
    for (Point2D point2d : setOfPoints) {
      double distanceTo = point.distanceSquaredTo(point2d);
      if (distanceTo < min) {
        min = distanceTo;
        nearest = point2d;
      }
    }
    return nearest;
  }
}
