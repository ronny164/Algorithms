package algs4.shortestweightedpath;

import static org.junit.Assert.assertEquals;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Stopwatch;

import org.junit.Test;

import java.util.Arrays;

public class SeamCarverTest {

  @Test
  public void testFindSeam10x10() {
    String path = "./src/test/resources/seamCarving/10x10.png";
    assertEquals("[6, 7, 7, 7, 7, 7, 8, 8, 7, 6]", findVertical(path));
    assertEquals("[0, 1, 2, 3, 3, 3, 3, 2, 1, 0]", findHorizontal(path));
  }

  @Test
  public void testFindSeam10x12() {
    String path = "./src/test/resources/seamCarving/10x12.png";
    assertEquals("[5, 6, 7, 8, 7, 7, 6, 7, 6, 5, 6, 5]", findVertical(path));
    assertEquals("[8, 9, 10, 10, 10, 9, 10, 10, 9, 8]", findHorizontal(path));
  }

  @Test
  public void testFindSeam12x10() {
    String path = "./src/test/resources/seamCarving/12x10.png";
    assertEquals("[6, 7, 7, 6, 6, 7, 7, 7, 8, 7]", findVertical(path));
    assertEquals("[7, 8, 7, 8, 7, 6, 5, 6, 6, 5, 4, 3]", findHorizontal(path));
  }

  @Test
  public void testFindSeam3x4() {
    String path = "./src/test/resources/seamCarving/3x4.png";
    assertEquals("[0, 1, 1, 0]", findVertical(path));
    assertEquals("[1, 2, 1]", findHorizontal(path));
  }

  @Test
  public void testFindSeam3x7() {
    String path = "./src/test/resources/seamCarving/3x7.png";
    assertEquals("[0, 1, 1, 1, 1, 1, 0]", findVertical(path));
    assertEquals("[1, 2, 1]", findHorizontal(path));
  }

  @Test
  public void testFindSeam4x6() {
    String path = "./src/test/resources/seamCarving/4x6.png";
    assertEquals("[1, 2, 1, 1, 2, 1]", findVertical(path));
    assertEquals("[1, 2, 1, 0]", findHorizontal(path));
  }

  @Test
  public void testFindSeam7x10() {
    String path = "./src/test/resources/seamCarving/7x10.png";
    assertEquals("[2, 3, 4, 3, 4, 3, 3, 2, 2, 1]", findVertical(path));
    assertEquals("[6, 7, 7, 7, 8, 8, 7]", findHorizontal(path));
  }

  private String findVertical(String path) {
    Picture picture = new Picture(path);
    SeamCarver carver = new SeamCarver(picture);
    int[] seam = carver.findVerticalSeam();
    PrintSeams.printSeam(carver, seam, PrintSeams.VERTICAL);
    return Arrays.toString(seam);
  }

  private String findHorizontal(String path) {
    Picture picture = new Picture(path);
    SeamCarver carver = new SeamCarver(picture);
    int[] seam = carver.findHorizontalSeam();
    PrintSeams.printSeam(carver, seam, PrintSeams.HORIZONTAL);
    return Arrays.toString(seam);
  }

  @Test
  public void testRemoveVertical3x4() {
    String path = "./src/test/resources/seamCarving/3x4.png";
    Picture picture = new Picture(path);
    SeamCarver carver = new SeamCarver(picture);
    int[] seam = new int[] {1, 1, 1, 1};
    PrintSeams.printSeam(carver, seam, PrintSeams.VERTICAL);
    carver.removeVerticalSeam(seam);
    PrintSeams.printSeam(carver, seam, PrintSeams.VERTICAL);
    assertEquals(4, carver.height());
    assertEquals(2, carver.width());
  }

  @Test
  public void testRemoveVertical10x10() {
    String path = "./src/test/resources/seamCarving/10x10.png";
    Picture picture = new Picture(path);
    SeamCarver carver = new SeamCarver(picture);
    int[] seam = new int[] {1, 2, 3, 4, 3, 2, 1, 2, 3, 2};
    PrintSeams.printSeam(carver, seam, PrintSeams.VERTICAL);
    carver.removeVerticalSeam(seam);
    PrintSeams.printSeam(carver, seam, PrintSeams.VERTICAL);
    assertEquals(10, carver.height());
    assertEquals(9, carver.width());
  }

  @Test
  public void testRemove10x10() {
    String path = "./src/test/resources/seamCarving/10x10.png";
    Picture picture = new Picture(path);
    SeamCarver carver = new SeamCarver(picture);
    int[] seam = null;

    int removeSize = 5;
    for (int i = 0; i < removeSize; i++) {
      seam = carver.findVerticalSeam();
      System.out.println("generated seam: " + Arrays.toString(seam));
      PrintSeams.printSeam(carver, seam, PrintSeams.VERTICAL);
      carver.removeVerticalSeam(seam);
    }
    for (int i = 0; i < removeSize; i++) {
      seam = carver.findHorizontalSeam();
      System.out.println("generated seam: " + Arrays.toString(seam));
      PrintSeams.printSeam(carver, seam, PrintSeams.HORIZONTAL);
      carver.removeHorizontalSeam(seam);
    }
  }

  @Test
  public void testRemove12x10() {
    String path = "./src/test/resources/seamCarving/12x10.png";
    Picture picture = new Picture(path);
    SeamCarver carver = new SeamCarver(picture);
    int[] seam = null;

    for (int i = 0; i < 5; i++) {
      seam = carver.findVerticalSeam();
      System.out.println("generated seam: " + Arrays.toString(seam));
      PrintSeams.printSeam(carver, seam, PrintSeams.VERTICAL);
      carver.removeVerticalSeam(seam);
    }
    for (int i = 0; i < 7; i++) {
      seam = carver.findHorizontalSeam();
      System.out.println("generated seam: " + Arrays.toString(seam));
      PrintSeams.printSeam(carver, seam, PrintSeams.HORIZONTAL);
      carver.removeHorizontalSeam(seam);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSeamTooBig() {
    String path = "./src/test/resources/seamCarving/3x4.png";
    Picture picture = new Picture(path);
    SeamCarver carver = new SeamCarver(picture);
    carver.removeVerticalSeam(new int[] {-1, 9, 7, 8, 8, 8, 6, 6, 5, -1});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSeamOutOfBound() {
    String path = "./src/test/resources/seamCarving/3x4.png";
    Picture picture = new Picture(path);
    SeamCarver carver = new SeamCarver(picture);
    carver.removeVerticalSeam(new int[] {1, 0, 2, 1});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSeamOutOfBound2() {
    String path = "./src/test/resources/seamCarving/3x4.png";
    Picture picture = new Picture(path);
    SeamCarver carver = new SeamCarver(picture);
    carver.removeVerticalSeam(new int[] {1, 1, 4, 1});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSeamOutOfBound3() {
    String path = "./src/test/resources/seamCarving/3x4.png";
    Picture picture = new Picture(path);
    SeamCarver carver = new SeamCarver(picture);
    carver.removeVerticalSeam(new int[] {1, 2, 3, 2});
  }


  @Test(expected = IllegalArgumentException.class)
  public void testSeamOutOfBound4() {
    String path = "./src/test/resources/seamCarving/10x10.png";
    Picture picture = new Picture(path);
    SeamCarver carver = new SeamCarver(picture);
    carver.removeVerticalSeam(new int[] {9, 10, 10, 9, 8, 7, 7, 7, 7, 7});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSeamBigDistance() {
    String path = "./src/test/resources/seamCarving/3x4.png";
    Picture picture = new Picture(path);
    SeamCarver carver = new SeamCarver(picture);
    carver.removeHorizontalSeam(new int[] {1, 1, 3, 3});
  }

  @Test
  public void testRemoveHJocean20() {
    System.out.println("starting.");
    String path = "./src/test/resources/seamCarving/HJocean.png";
    Picture picture = new Picture(path);
    SeamCarver carver = new SeamCarver(picture);
    System.out.println("size: " + carver.width() + " width, and " + carver.height() + " height");
    int[] seam = null;

    Stopwatch sw = new Stopwatch();
    for (int i = 0; i < 20; i++) {
      seam = carver.findVerticalSeam();
      carver.removeVerticalSeam(seam);
    }
    for (int i = 0; i < 20; i++) {
      seam = carver.findHorizontalSeam();
      carver.removeHorizontalSeam(seam);
    }
    System.out.println("time: " + sw.elapsedTime());
    System.out.println("removed 20 rows and 20 cols");
    System.out.println("size: " + carver.width() + " width, and " + carver.height() + " height");
  }

  @Test
  public void testRemoveHJocean200() {
    System.out.println("starting.");
    String path = "./src/test/resources/seamCarving/HJocean.png";
    Picture picture = new Picture(path);
    SeamCarver carver = new SeamCarver(picture);
    int[] seam = new int[carver.height()];

    Stopwatch sw = new Stopwatch();
    for (int i = 0; i < 200; i++) {
      carver.removeVerticalSeam(seam);
    }
    seam = new int[carver.width()];
    for (int i = 0; i < 200; i++) {
      carver.removeHorizontalSeam(seam);
    }
    System.out.println("time: " + sw.elapsedTime());
  }
}
