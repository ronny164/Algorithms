package algs4.datastructures;

import algs4.datastructures.BalancedBST.TreeNode;

import org.junit.Test;

import java.util.Iterator;

/**
 * @author Ronny A. Pena
 */
public class BalancedBSTPrint {
  
  public static <T> String printInOrderArray(BalancedBST<T> bst) {
    StringBuilder sb = new StringBuilder();
    Iterator<T> it = bst.iterator();
    sb.append('[');
    if(it.hasNext()) {
      sb.append(it.next());
    }
    while (it.hasNext()) {
      sb.append(',').append(it.next());
    }
    sb.append(']');
    return sb.toString();
  }

  public static <T> String printTree(BalancedBST<T> tree) {
    int n = tree.size();
    int spaceCount = getSpaceCount(n);
    StringBuilder sb = new StringBuilder();
    Queue<TreeNode<?>> currentLevel = new Queue<>();
    Queue<TreeNode<?>> nextLevel = new Queue<>();
    currentLevel.enqueue(tree.root);
    TreeNode<String> BLANK = new TreeNode<>("__");
    int dashCount = 0;
    while (!currentLevel.isEmpty()) {
      TreeNode<?> current = currentLevel.dequeue();
      if (current.left != null || current.right != null) {
        if (current.left != null) {
          nextLevel.enqueue(current.left);
        } else {
          nextLevel.enqueue(BLANK);
        }
        if (current.right != null) {
          nextLevel.enqueue(current.right);
        } else {
          nextLevel.enqueue(BLANK);
        }
      }
      printVal(spaceCount, sb, current);
      dashCount++;
      if (currentLevel.isEmpty()) {
        sb.append("\n");
        for (int i = 0; i < dashCount; i++) {
          printDash(spaceCount, sb);
        }
        sb.append("\n");
        dashCount = 0;
        spaceCount /= 2;
        Queue<TreeNode<?>> swap = currentLevel;
        currentLevel = nextLevel;
        nextLevel = swap;
      }
    }
    return sb.toString();
  }

  private static <T> void printVal(int s, StringBuilder sb, TreeNode<T> current) {
    if (current == null) {
      sb.append(getSpaces(s)).append(' ').append(getSpaces(s));
    } else {
      if (current.val instanceof Integer) {
        sb.append(getSpaces(s)).append(String.format("%02d", current.val)).append(getSpaces(s));
      } else {
        sb.append(getSpaces(s)).append(current.val).append(getSpaces(s));
      }
    }
  }

  private static <T> void printDash(int s, StringBuilder sb) {
    sb.append(getSpaces(s - 1)).append("/  \\").append(getSpaces(s - 1));
  }

  private static String getSpaces(int s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s; i++) {
      sb.append(' ');
    }
    return sb.toString();
  }

  private static int getSpaceCount(int n) {
    int log = log2(n);
    int top = (int) Math.pow(2, log + 2);
    int width = 3 * (top / 2);
    return (width / 2) - 2;
  }

  private static int log2(int n) {
    if (n < 1) {
      throw new IllegalArgumentException();
    }
    return (int) (Math.log(n) / Math.log(2));
  }
  
  @Test
  public void testLogic() {
    System.out.println("    1=" + log2(1));
    System.out.println(" 2- 3=" + log2(3));
    System.out.println(" 4- 7=" + log2(7));
    System.out.println(" 8-15=" + log2(15));
    System.out.println("16-31=" + log2(31));
    System.out.println("32-63=" + log2(32));
    System.out.println("s for 7: " + getSpaceCount(7));
    System.out.println("s for 15: " + getSpaceCount(15));
    System.out.println("s for 16: " + getSpaceCount(16));
  }

  @Test
  public void testPrint() {
    BalancedBST<Integer> bst = new BalancedBST<>();
    bst.add(16);
    bst.add(20);
    bst.add(28);
    bst.add(18);
    bst.add(22);
    bst.add(26);
    bst.add(30);
    bst.add(17);
    bst.add(19);
    bst.add(21);
    bst.add(23);
    bst.add(25);
    bst.add(27);
    bst.add(29);
    bst.add(31);
    
    bst.add(8);
    bst.add(4);
    bst.add(12);
    bst.add(2);
    bst.add(6);
    bst.add(10);
    bst.add(14);
    bst.add(1);
    bst.add(3);
    bst.add(5);
    bst.add(7);
    bst.add(9);
    bst.add(11);
    bst.add(13);
    bst.add(15);
    System.out.println(printTree(bst));
  }

  @Test
  public void testPrint2() {
    BalancedBST<Integer> bst = new BalancedBST<>();
    bst.add(16);
    for (int i = 1; i <= 31; i++) {
      bst.add(i);
    }
    System.out.println(printTree(bst));
  }
}