package algs4.datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BalancedBSTTest {

  @Test
  public void testAdd21() {
    BalancedBST<Integer> bst = new BalancedBST<>();

    bst.add(5);
    System.out.println(bst);
    bst.add(1);
    System.out.println(bst);
    bst.add(6);
    System.out.println(bst);
    bst.add(2);
    System.out.println(bst);
    bst.add(4);
    System.out.println(bst);
    bst.add(3);
    System.out.println(bst);
    bst.add(7);
    System.out.println(bst);


    assertEquals(7, bst.size());
    assertEquals(4, bst.maxHeight());
  }

  @Test
  public void testAdd2() {
    BalancedBST<Integer> bst = new BalancedBST<>();

    bst.add(2);
    System.out.println(bst);
    bst.add(5);
    System.out.println(bst);
    bst.add(4);
    System.out.println(bst);
    bst.add(3);
    System.out.println(bst);
    bst.add(6);
    System.out.println(bst);
    bst.add(7);
    System.out.println(bst);
    bst.add(1);
    System.out.println(bst);


    assertEquals(7, bst.size());
    assertEquals(3, bst.maxHeight());
  }

  @Test
  public void testAdd() {
    BalancedBST<Integer> bst = new BalancedBST<>();
    bst.add(4);
    bst.add(2);
    bst.add(6);
    bst.add(1);
    bst.add(3);
    bst.add(5);
    bst.add(7);
    System.out.println(bst);

    BalancedBSTPrint.printTree(bst);
    assertEquals(7, bst.size());
    assertEquals(3, bst.maxHeight());
  }

  @Test
  public void testAddAnscending() {
    BalancedBST<Integer> bst = new BalancedBST<>();
    bst.add(1);
    bst.add(2);
    bst.add(3);
    bst.add(4);
    bst.add(5);
    bst.add(6);
    bst.add(7);
    System.out.println(bst);

    BalancedBSTPrint.printTree(bst);
    assertEquals(7, bst.size());
    assertEquals(3, bst.maxHeight());
  }


  @Test
  public void testAddDescending() {
    BalancedBST<Integer> bst = new BalancedBST<>();
    bst.add(7);
    bst.add(6);
    bst.add(5);
    bst.add(4);
    bst.add(3);
    bst.add(2);
    bst.add(1);
    System.out.println(bst);

    BalancedBSTPrint.printTree(bst);
    assertEquals(7, bst.size());
    assertEquals(3, bst.maxHeight());
  }

  @Test
  public void testAddBalancePermutations() {
    int n = 7;
    ArrayList<ArrayList<Integer>> permutations = TestUtils.generatePermutations(n);
    for (ArrayList<Integer> permutation : permutations) {
      System.out.println(permutation);
      BalancedBST<Integer> bst = new BalancedBST<>();
      for (Integer val : permutation) {
        bst.add(val);
        System.out.println(bst);
      }
      System.out.println(bst);
      assertEquals(n, bst.size());
      int h = bst.maxHeight();
      assertTrue(3 <= h && h <= 4);

      BalancedBSTPrint.printTree(bst);
      System.out.println("--------");

    }
  }
}
