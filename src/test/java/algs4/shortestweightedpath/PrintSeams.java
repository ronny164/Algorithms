package algs4.shortestweightedpath;


import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class PrintSeams {
  public static final boolean HORIZONTAL = true;
  public static final boolean VERTICAL = false;

  public static void printSeam(SeamCarver carver, int[] seam, boolean direction) {
    double totalSeamEnergy = 0.0;

    for (int row = 0; row < carver.height(); row++) {
      for (int col = 0; col < carver.width(); col++) {
        double energy = carver.energy(col, row);
        String marker = " ";
        if ((direction == HORIZONTAL && row == seam[col])
            || (direction == VERTICAL && col == seam[row])) {
          marker = "*";
          totalSeamEnergy += energy;
        }
        StdOut.printf("%7.2f%s ", energy, marker);
      }
      StdOut.println();
    }
    // StdOut.println();
    StdOut.printf("Total energy = %f\n", totalSeamEnergy);
    StdOut.println();
    StdOut.println();
  }

  public static void main(String[] args) {
    String path = "./src/test/resources/seamCarving/7x10.png";
    // String path = "./src/test/resources/seamCarving/10x12.png";
    // String path = "./src/test/resources/seamCarving/10x10.png";
    // String path = "./src/test/resources/seamCarving/HJocean.png";
    Picture picture = new Picture(path);
    StdOut.printf("%s (%d-by-%d image)\n", path, picture.width(), picture.height());
    StdOut.println();
    StdOut.println("The table gives the dual-gradient energies of each pixel.");
    StdOut.println("The asterisks denote a minimum energy vertical or horizontal seam.");
    StdOut.println();

    SeamCarver carver = new SeamCarver(picture);

    StdOut.printf("Vertical seam: { ");
    int[] verticalSeam = carver.findVerticalSeam();
    for (int x : verticalSeam) {
      StdOut.print(x + " ");
    }
    StdOut.println("}");
    printSeam(carver, verticalSeam, VERTICAL);

    StdOut.printf("Horizontal seam: { ");
    int[] horizontalSeam = carver.findHorizontalSeam();
    for (int y : horizontalSeam) {
      StdOut.print(y + " ");
    }
    StdOut.println("}");
    printSeam(carver, horizontalSeam, HORIZONTAL);

  }

}
