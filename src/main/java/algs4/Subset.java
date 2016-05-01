package algs4;

import algs4.datastructures.RandomizedQueue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author Ronny A. Pena
 */
public class Subset {

  public static void main(String[] args) {
    approach2(args);
  }

  // works and uses O(k)
  private static void approach2(String[] args) {
    int k = Integer.parseInt(args[0]);
    String[] input = new String[k];
    for (int i = 0, j = 0; !StdIn.isEmpty();) {
      j = StdRandom.uniform(i + 1);
      String s1 = StdIn.readString();
      insertSwapIfEmpty(input, s1, i, j, k);
      i++;
    }
    print(input);
  }

  // works but uses O(n) space.
  private static void approach1(String[] args) {
    int k = Integer.parseInt(args[0]);
    RandomizedQueue<String> q = new RandomizedQueue<>();
    while (!StdIn.isEmpty()) {
      q.enqueue(StdIn.readString());
    }
    for (int i = 0; i < k; i++) {
      StdOut.println(q.dequeue());
    }
  }

  private static void insertSwapIfEmpty(String[] input, String s1, int i, int j, int k) {
    if (j < k) {
      String temp = input[j];
      input[j] = s1;
      if (i < k && temp != null) {
        input[i] = temp;
      }
    }
  }

  private static void print(String[] input) {
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    for (String s1 : input) {
      if (s1 != null) {
        queue.enqueue(s1);
      }
    }
    for (String val : queue) {
      StdOut.println(val);
    }
  }
}
