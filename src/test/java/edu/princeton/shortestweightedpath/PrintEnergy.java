package edu.princeton.shortestweightedpath;


import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class PrintEnergy {

  public static void main(String[] args) {
    Picture picture = new Picture("./src/test/resources/seamCarving/10x12.png");
    StdOut
        .printf("image is %d pixels wide by %d pixels high.\n", picture.width(), picture.height());

    SeamCarver sc = new SeamCarver(picture);

    StdOut.printf("Printing energy calculated for each pixel.\n");

    printEnergy(sc);
  }

  public static void printEnergy(SeamCarver sc) {
    for (int j = 0; j < sc.height(); j++) {
      for (int i = 0; i < sc.width(); i++) {
        StdOut.printf("%9.0f ", sc.energy(i, j));
      }
      StdOut.println();
    }
  }
}
