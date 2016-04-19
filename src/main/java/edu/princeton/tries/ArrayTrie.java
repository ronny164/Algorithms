package edu.princeton.tries;

import java.util.LinkedList;
import java.util.List;

/**
 * Standard ArrayTrie, Could be extended to work as a Map. Uses iteration instead of recursion for
 * common operations. Works with a String or StringBuilder.
 * 
 * @author Ronny A. Pena
 */
public class ArrayTrie {

  // Allowing package access to enable BoggleSolver optimizations.
  static class TrieNode {
    TrieNode[] children;
    boolean isWord;
  }

  private TrieNode root;
  private int radix;
  private char lowerBound;

  /**
   * @param radix The range of possible characters. Example: ('Z' + 1) - 'A' to fit 26 character 
   *        alphabet.
   * @param lowerBound Character could be 'a' for lowercase alphabet, 'A' for uppercase, or '0' for
   *        a numeric alphabet. 
   */
  public ArrayTrie(int radix, char lowerBound) {
    root = new TrieNode();
    this.radix = radix;
    this.lowerBound = lowerBound;
  }

  /**
   * Adds the string to the trie. 
   * @param value The string to add.
   */
  public void add(CharSequence value) {
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
    current.isWord = true;
  }

  // Allowing package access to enable BoggleSolver optimizations.
  TrieNode get(CharSequence value) {
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

  private Iterable<String> collect(CharSequence prefix, TrieNode node) {
    if (prefix == null) {
      throw new IllegalArgumentException();
    }
    List<String> collection = new LinkedList<>();
    StringBuilder sb = new StringBuilder(prefix);
    collect(node, sb, collection);
    return collection;
  }

  private void collect(TrieNode current, StringBuilder sb, List<String> collection) {
    if (current.isWord) {
      collection.add(sb.toString());
    }
    if (current.children != null) {
      int n = current.children.length;
      for (int i = 0; i < n; i++) {
        TrieNode entry = current.children[i];
        if (entry != null) {
          sb.append(getCharFromInt(i));
          collect(entry, sb, collection);
          sb.setLength(sb.length() - 1);
        }
      }
    }
  }

  public boolean contains(CharSequence value) {
    TrieNode node = get(value);
    return node != null && node.isWord;
  }

  public boolean containsPrefix(CharSequence value) {
    return get(value) != null;
  }

  public Iterable<String> valuesWithPrefix(CharSequence prefix) {
    return collect(prefix, get(prefix));
  }

  public Iterable<String> values() {
    return collect("", root);
  }

  private int getArrayIndex(CharSequence value, int charIndex) {
    if (charIndex == value.length()) {
      return -1;
    }
    return value.charAt(charIndex) - lowerBound;
  }

  private char getCharFromInt(int i) {
    return (char) (lowerBound + i);
  }
}
