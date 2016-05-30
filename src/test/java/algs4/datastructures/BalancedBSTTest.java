package algs4.datastructures;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BalancedBSTTest {

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
  public void testAddBalance() {
    int n = 7;
    ArrayList<ArrayList<Integer>> permutations = TestUtils.generatePermutations(n);
    for (ArrayList<Integer> permutation : permutations) {
      System.out.println(permutation);
      BalancedBST<Integer> bst = new BalancedBST<>();
      for (Integer val : permutation) {
        bst.add(val);
        BalancedBSTPrint.printTree(bst);
        System.out.println("--------");
      }
      System.out.println(bst);
      assertEquals(n, bst.size());
      assertEquals(3, bst.maxHeight());

      BalancedBSTPrint.printTree(bst);
    }
  }
}
