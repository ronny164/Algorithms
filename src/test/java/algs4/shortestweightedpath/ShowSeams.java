package algs4.shortestweightedpath;


import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class ShowSeams {

  private static void showHorizontalSeam(SeamCarver sc) {
    Picture picture = SCUtility.toEnergyPicture(sc);
    int[] horizontalSeam = sc.findHorizontalSeam();
    Picture overlay = SCUtility.seamOverlay(picture, true, horizontalSeam);
    overlay.show();
  }


  private static void showVerticalSeam(SeamCarver sc) {
    Picture picture = SCUtility.toEnergyPicture(sc);
    int[] verticalSeam = sc.findVerticalSeam();
    Picture overlay = SCUtility.seamOverlay(picture, false, verticalSeam);
    overlay.show();
  }

  public static void main(String[] args) {

    // Picture picture = new Picture("./src/test/resources/seamCarving/10x12.png");
    Picture picture = new Picture("./src/test/resources/seamCarving/HJocean.png");
    StdOut.printf("image is %d columns by %d rows\n", picture.width(), picture.height());
    picture.show();
    SeamCarver sc = new SeamCarver(picture);

    StdOut.printf("Displaying horizontal seam calculated.\n");
    showHorizontalSeam(sc);

    StdOut.printf("Displaying vertical seam calculated.\n");
    showVerticalSeam(sc);

  }

}
