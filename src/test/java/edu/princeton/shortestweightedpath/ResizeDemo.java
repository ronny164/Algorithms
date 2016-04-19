package edu.princeton.shortestweightedpath;


import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ResizeDemo {
  public static void main(String[] args) {
    // if (args.length != 3) {
    // StdOut.println("Usage:\njava ResizeDemo [image filename] [num cols to remove] [num rows to remove]");
    // return;
    // }

    // String path = "./src/test/resources/seamCarving/10x12.png";
    String path = "./src/test/resources/seamCarving/HJocean.png";
    Picture inputImg = new Picture(path);
    // int removeColumns = Integer.parseInt(args[1]);
    // int removeRows = Integer.parseInt(args[2]);
    int removeColumns = 100;
    int removeRows = 100;

    StdOut.printf("image is %d columns by %d rows\n", inputImg.width(), inputImg.height());
    SeamCarver sc = new SeamCarver(inputImg);

    Stopwatch sw = new Stopwatch();

    for (int i = 0; i < removeRows; i++) {
      int[] horizontalSeam = sc.findHorizontalSeam();
      sc.removeHorizontalSeam(horizontalSeam);
    }

    for (int i = 0; i < removeColumns; i++) {
      int[] verticalSeam = sc.findVerticalSeam();
      sc.removeVerticalSeam(verticalSeam);
    }
    Picture outputImg = sc.picture();

    StdOut.printf("new image size is %d columns by %d rows\n", sc.width(), sc.height());

    StdOut.println("Resizing time: " + sw.elapsedTime() + " seconds.");
    inputImg.show();
    outputImg.show();
  }

}
