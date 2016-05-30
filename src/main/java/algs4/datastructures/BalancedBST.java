package algs4.datastructures;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Left-leaning Red-Black BST implementation.
 * 
 * @author Ronny A. Pena
 */
public class BalancedBST<T> implements Iterable<T> {

  static final class TreeNode<T> {
    T val;
    TreeNode<T> left;
    TreeNode<T> right;
    private boolean isRed;

    public TreeNode(T val) {
      this.val = val;
      this.isRed = true;
    }
  }

  TreeNode<T> root;
  private int size;
  private Comparator<T> cmp = Collections.reverseOrder(Collections.reverseOrder());

  public void add(T val) {
    if (val == null) {
      throw new NullPointerException();
    }
    if(root == null) {
      size++;
      root = new TreeNode<>(val);
      return;
    }
    root = add(root, val);
  }

  private TreeNode<T> add(TreeNode<T> parent, T val) {
    if (parent == null) {
      size++;
      return new TreeNode<>(val);
    }
    int compare = cmp.compare(val, parent.val);
    if (compare == 0) {
      return parent;
    } else if (compare < 0) {
      parent.left = add(parent.left, val);
    } else {
      parent.right = add(parent.right, val);
    }

    if (isRed(parent.right) && !isRed(parent.left)) {
      parent = rotateLeft(parent);
    }
    if (isRed(parent.left) && isRed(parent.left.left)) {
      parent = rotateRight(parent);
    }
    if (isRed(parent.left) && isRed(parent.right)) {
      flipColors(parent);
    }
    return parent;
  }

  private boolean isRed(TreeNode<T> current) {
    return current != null && current.isRed;
  }

  private TreeNode<T> rotateLeft(TreeNode<T> oldParent) {
    TreeNode<T> newParent = oldParent.right;
    oldParent.right = oldParent.right.left;
    oldParent.isRed = true;
    newParent.left = oldParent;
    newParent.isRed = false;
    return newParent;
  }

  private TreeNode<T> rotateRight(TreeNode<T> oldParent) {
    TreeNode<T> newParent = oldParent.left;
    oldParent.left = oldParent.left.right;
    oldParent.isRed = true;
    newParent.right = oldParent;
    newParent.isRed = false;
    return newParent;
  }

  private void flipColors(TreeNode<T> parent) {
    parent.isRed = true;
    parent.left.isRed = false;
    parent.right.isRed = false;
  }

  public void remove(T val) {

  }

  public boolean contains(T val) {
    return false;
  }

  public T removeMax() {
    return null;
  }

  public T removeMin() {
    return null;
  }

  public T peekMax() {
    TreeNode<T> current = root;
    while (current.right != null) {
      current = current.right;
    }
    return current.val;
  }

  public T peekMin() {
    TreeNode<T> current = root;
    while (current.left != null) {
      current = current.left;
    }
    return current.val;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public String toString() {
    return BalancedBSTPrint.printTree(this);
  }
  
  @Override
  public Iterator<T> iterator() {
    return new InorderIterator(this.root);
  }

  private static final class InorderIterator<T> implements Iterator<T> {

    private Stack<TreeNode<T>> stack;

    public InorderIterator(TreeNode<T> root) {
      stack = new Stack<>();
      TreeNode<T> current = root;
      addNodes(current);
    }

    private void addNodes(TreeNode<T> current) {
      while (current != null) {
        stack.push(current);
        current = current.left;
      }
    }

    @Override
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      TreeNode<T> current = stack.pop();
      if (current.right != null) {
        addNodes(current.right);
      }
      return current.val;
    }
  }

  public int maxHeight() {
    return maxHeight(root);
  }
  
  private int maxHeight(TreeNode<T> current) {
    if (current == null) {
      return 0;
    }
    return 1 + Math.max(maxHeight(current.left), maxHeight(current.right));
  }
}
