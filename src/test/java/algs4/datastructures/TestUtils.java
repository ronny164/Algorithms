package algs4.datastructures;

import java.util.Arrays;
import java.util.Random;

public class TestUtils {

  public static int[] generateRandom(int n) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = i + 1;
    }
    shuffle(arr);
    System.out.println(Arrays.toString(arr));
    return arr;
  }

  public static void shuffle(int[] arr) {
    Random rand = new Random(System.currentTimeMillis());
    for (int i = 0; i < arr.length; i++) {
      int j = rand.nextInt(i + 1);
      swap(arr, i, j);
    }
  }

  public static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static ArrayList<String> getSample(int sampleSize) {
    ArrayList<String> sample = new ArrayList<>();
    for (int i = 0; i < sampleSize; i++) {
      sample.add(i + "");
    }
    return sample;
  }

}
