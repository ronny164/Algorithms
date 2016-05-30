package algs4.datastructures;

import java.util.Arrays;
import java.util.Random;

public class TestUtils {

  public static int[] generateRandom(int n) {
    int[] arr = generate(n);
    shuffle(arr);
    System.out.println(Arrays.toString(arr));
    return arr;
  }

  private static int[] generate(int n) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = i + 1;
    }
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

  public static ArrayList<String> getSample(int n) {
    ArrayList<String> sample = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      sample.add(i + "");
    }
    return sample;
  }
  
  public static ArrayList<ArrayList<Integer>> generatePermutations(int n){
    int[] arr = generate(n);
    ArrayList<ArrayList<Integer>> out = new ArrayList<>();
    generate(arr, n, out);
    return out;
  }

  private static void generate(int[] arr, int n, ArrayList<ArrayList<Integer>> out) {
    if (n == 0) {
      out.add(convert(arr));
    }
    for (int i = 0; i < n; i++) {
      swap(arr, i, n - 1);
      generate(arr, n - 1, out);
      swap(arr, i, n - 1);
    }
  }

  private static ArrayList<Integer> convert(int[] arr) {
    ArrayList<Integer> out = new ArrayList<>();
    for (int val : arr) {
      out.add(val);
    }
    return out;
  }
}