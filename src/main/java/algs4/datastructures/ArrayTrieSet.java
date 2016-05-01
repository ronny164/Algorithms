package algs4.datastructures;

import java.util.LinkedList;
import java.util.List;

/**
 * Standard ArrayTrie, Could be extended to work as a Map. Uses iteration instead of recursion for
 * common operations. Works with a String or StringBuilder.
 * 
 * @author Ronny A. Pena
 */
public class ArrayTrieSet {

  // Purposely allowing package access to enable BoggleSolver optimizations.
  public static class TrieNode {
    public TrieNode[] children;
    public String word;
  }

  public TrieNode root;
  private int radix;
  private char lowerBound;

  /**
   * @param radix The range of possible characters. Example: ('Z' + 1) - 'A' to fit 26 character 
   *        alphabet.
   * @param lowerBound Character could be 'a' for lowercase alphabet, 'A' for uppercase, or '0' for
   *        a numeric alphabet. 
   */
  public ArrayTrieSet(int radix, char lowerBound) {
    root = new TrieNode();
    this.radix = radix;
    this.lowerBound = lowerBound;
  }

  /**
   * Adds the string to the trie. 
   * @param value The string to add.
   */
  public void add(String value) {
    if (value == null || value.length() == 0) {
      throw new IllegalArgumentException();
    }
    int charIndex = 0;
    TrieNode current = root;
    int arrayIndex = getArrayIndex(value, charIndex);
    while (arrayIndex != -1) {
      if (current.children == null) {
        current.children = new TrieNode[radix];
      }
      TrieNode child = null;
      child = current.children[arrayIndex];
      if (child == null) {
        child = new TrieNode();
        current.children[arrayIndex] = child;
      }
      current = child;
      arrayIndex = getArrayIndex(value, ++charIndex);
    }
    current.word = value;
  }

  private TrieNode get(String value) {
    if (value == null || value.length() == 0) {
      throw new IllegalArgumentException();
    }
    int charIndex = 0;
    TrieNode current = root;
    int arrayIndex = getArrayIndex(value, charIndex);
    while (arrayIndex != -1) {
      if (current.children == null) {
        return null;
      }
      TrieNode child = current.children[arrayIndex];
      if (child == null) {
        return null;
      }
      current = child;
      arrayIndex = getArrayIndex(value, ++charIndex);
    }
    return current;
  }

  private Iterable<String> collect(TrieNode node) {
    if (node == null) {
      throw new IllegalArgumentException();
    }
    List<String> collection = new LinkedList<>();
    collect(node, collection);
    return collection;
  }

  private void collect(TrieNode current, List<String> collection) {
    if (current.word != null) {
      collection.add(current.word);
    }
    if (current.children != null) {
      int n = current.children.length;
      for (int i = 0; i < n; i++) {
        TrieNode entry = current.children[i];
        if (entry != null) {
          collect(entry, collection);
        }
      }
    }
  }

  public boolean contains(String value) {
    TrieNode node = get(value);
    return node != null && node.word != null;
  }

  public boolean containsPrefix(String value) {
    return get(value) != null;
  }

  public Iterable<String> valuesWithPrefix(String prefix) {
    return collect(get(prefix));
  }

  public Iterable<String> values() {
    return collect(root);
  }

  private int getArrayIndex(String value, int charIndex) {
    if (charIndex == value.length()) {
      return -1;
    }
    return value.charAt(charIndex) - lowerBound;
  }

  private char getCharFromInt(int i) {
    return (char) (lowerBound + i);
  }
}
