package algs4.shortestweightedpath;

import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

/**
 * Utility class for image operations.
 * 
 * @author Ronny A. Pena
 */
public class ImageUtil {

  static void saveImage(Picture picture, Vertex[][] vertices) {
    for (int i = 0; i < vertices.length; i++) {
      for (int j = 0; j < vertices[i].length; j++) {
        int rgb = picture.get(j, i).getRGB();
        double energy = energy(picture, j, i);
        vertices[i][j] = new Vertex(rgb, energy);
      }
    }
  }

  static Picture createPicture(Vertex[][] vertices, int width, int height) {
    Picture newPicture = new Picture(width, height);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newPicture.set(j, i, new Color(vertices[i][j].color));
      }
    }
    return newPicture;
  }

  private static double energy(Picture picture, int x, int y) {
    int width = picture.width();
    int height = picture.height();
    if (x < 0 || x >= width || y < 0 || y >= height) {
      throw new IndexOutOfBoundsException();
    }
    if (x - 1 < 0 || x + 1 >= width || y - 1 < 0 || y + 1 >= height) {
      return 1_000.00;
    }
    Color left = picture.get(x - 1, y);
    Color right = picture.get(x + 1, y);
    int xgradient = getGradient(left, right);
    Color down = picture.get(x, y - 1);
    Color up = picture.get(x, y + 1);
    int ygradient = getGradient(down, up);
    return Math.sqrt(xgradient + ygradient);
  }

  private static int getGradient(Color o1, Color o2) {
    int red = o2.getRed() - o1.getRed();
    int green = o2.getGreen() - o1.getGreen();
    int blue = o2.getBlue() - o1.getBlue();
    return (int) (Math.pow(red, 2) + Math.pow(green, 2) + Math.pow(blue, 2));
  }

  static void clear(Vertex[][] vertices, int i, int j, boolean transposed) {
    if (transposed) {
      vertices[j][i] = null;
    } else {
      vertices[i][j] = null;
    }
  }

  static void set(Vertex[][] vertices, int i, int j, int k, boolean transposed) {
    if (transposed) {
      vertices[j][i] = vertices[k][i];
    } else {
      vertices[i][j] = vertices[i][k];
    }
  }
}
