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
    if (it.hasNext()) {
      sb.append(it.next());
    }
    while (it.hasNext()) {
      sb.append(',').append(it.next());
    }
    sb.append(']');
    return sb.toString();
  }

  public static <T> String printTree(BalancedBST<T> tree) {
    int spaceCount = getSpaceCount(tree.maxHeight());
    StringBuilder sb = new StringBuilder();
    Queue<TreeNode<?>> currentLevel = new Queue<>();
    Queue<TreeNode<?>> nextLevel = new Queue<>();
    currentLevel.enqueue(tree.root);
    TreeNode<String> BLANK = new TreeNode<>("__");
    int linkCount = 0;
    while (!currentLevel.isEmpty()) {
      TreeNode<?> current = currentLevel.dequeue();
      if (current != BLANK) {
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
        linkCount++;
      }
      printValue(spaceCount, sb, current);
      
      if (currentLevel.isEmpty()) {
        sb.append("\n");
        for (int i = 0; i < linkCount; i++) {
          printLink(spaceCount, sb);
        }
        sb.append("\n");
        linkCount = 0;
        spaceCount /= 2;
        Queue<TreeNode<?>> swap = currentLevel;
        currentLevel = nextLevel;
        nextLevel = swap;
      }
    }
    return sb.toString();
  }

  private static <T> void printValue(int s, StringBuilder sb, TreeNode<T> current) {
    addSpaces(s, sb);
    if (current.val instanceof Integer) {
      sb.append(String.format("%02d", current.val));
    } else {
      sb.append(current.val);
    }
    addSpaces(s, sb);
  }

  private static <T> void printLink(int s, StringBuilder sb) {
    addSpaces(s - 1, sb);
    sb.append("/  \\");
    addSpaces(s - 1, sb);
  }

  private static void addSpaces(int spaceCount, StringBuilder sb) {
    for (int i = 0; i < spaceCount; i++) {
      sb.append(' ');
    }
  }

  private static int getSpaceCount(int height) {
    return (int) Math.pow(2, height + 1) - 1;
  }

  private static int log2(int n) {
    if (n < 1) {
      throw new IllegalArgumentException();
    }
    return (int) (Math.log(n) / Math.log(2));
  }

  @Test
  public void testLogic() {
    System.out.println("s for 7: " + getSpaceCount(7));
    System.out.println("s for 15: " + getSpaceCount(15));
    System.out.println("s for 16: " + getSpaceCount(16));
    System.out.println("s for 32: " + getSpaceCount(32));
  }

  @Test
  public void testPrint8() {
    BalancedBST<Integer> bst = new BalancedBST<>();
    bst.add(4);
    bst.add(2);
    bst.add(6);
    bst.add(1);
    bst.add(3);
    bst.add(5);
    bst.add(7);
    System.out.println(printTree(bst));
  }

  @Test
  public void testPrint16() {
    BalancedBST<Integer> bst = new BalancedBST<>();
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
  public void testPrint32() {
    BalancedBST<Integer> bst = new BalancedBST<>();
    bst.add(16);

    bst.add(8);
    bst.add(24);

    bst.add(4);
    bst.add(12);
    bst.add(18);
    bst.add(22);
    bst.add(26);

    bst.add(2);
    bst.add(6);
    bst.add(10);
    bst.add(14);
    bst.add(20);
    bst.add(28);
    bst.add(30);

    bst.add(1);
    bst.add(3);
    bst.add(5);
    bst.add(7);
    bst.add(9);
    bst.add(11);
    bst.add(13);
    bst.add(15);
    bst.add(17);
    bst.add(19);
    bst.add(21);
    bst.add(23);
    bst.add(25);
    bst.add(27);
    bst.add(29);
    bst.add(31);

    System.out.println(printTree(bst));
  }
}
