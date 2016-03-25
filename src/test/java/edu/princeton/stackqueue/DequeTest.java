package edu.princeton.stackqueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DequeTest {

  @Test
  public void testAddFirstRemoveFirst() {
    // dummy data
    Deque<String> d = new Deque<>();

    // invoke code
    d.addFirst("a");
    d.addFirst("b");
    d.addFirst("c");

    // verify results;
    assertFalse(d.isEmpty());
    assertEquals(3, d.size());

    checkIterator(d);

    assertEquals("c", d.removeFirst());
    assertEquals("b", d.removeFirst());
    assertEquals("a", d.removeFirst());


    assertTrue(d.isEmpty());
    assertEquals(0, d.size());

    d.addFirst("d");
    assertEquals("d", d.removeFirst());

    assertTrue(d.isEmpty());
    assertEquals(0, d.size());
  }

  private void checkIterator(Deque<String> d) {
    int size = d.size();
    for (String s : d) {
      System.out.println(s);
      size--;
    }
    assertEquals(0, size);
  }

  @Test
  public void testAddFirstRemoveLast() {
    // dummy data
    Deque<String> d = new Deque<>();

    // invoke code
    d.addFirst("a");
    d.addFirst("b");
    d.addFirst("c");

    // verify results;
    assertFalse(d.isEmpty());
    assertEquals(3, d.size());

    checkIterator(d);

    assertEquals("a", d.removeLast());
    assertEquals("b", d.removeLast());
    assertEquals("c", d.removeLast());

    assertTrue(d.isEmpty());
    assertEquals(0, d.size());
  }

  @Test
  public void testAddLastRemoveFirst() {
    // dummy data
    Deque<String> d = new Deque<>();

    // invoke code
    d.addLast("a");
    d.addLast("b");
    d.addLast("c");

    // verify results;
    assertFalse(d.isEmpty());
    assertEquals(3, d.size());

    checkIterator(d);

    assertEquals("a", d.removeFirst());
    assertEquals("b", d.removeFirst());
    assertEquals("c", d.removeFirst());


    assertTrue(d.isEmpty());
    assertEquals(0, d.size());
  }

  @Test
  public void testAddLastRemoveLast() {
    // dummy data
    Deque<String> d = new Deque<>();

    // invoke code
    d.addLast("a");
    d.addLast("b");
    d.addLast("c");

    // verify results;
    assertFalse(d.isEmpty());
    assertEquals(3, d.size());

    checkIterator(d);

    assertEquals("c", d.removeLast());
    assertEquals("b", d.removeLast());
    assertEquals("a", d.removeLast());

    assertTrue(d.isEmpty());
    assertEquals(0, d.size());
  }

  @Test
  public void testAddFirstRemoveLastOne() {
    // dummy data
    Deque<String> d = new Deque<>();
    assertTrue(d.isEmpty());

    // invoke code
    d.addFirst("a");

    // verify results;
    assertFalse(d.isEmpty());
    assertEquals("a", d.removeLast());

    assertTrue(d.isEmpty());
    assertEquals(0, d.size());
  }

  @Test
  public void testAddLastRemoveFirstOne() {
    // dummy data
    Deque<String> d = new Deque<>();
    assertTrue(d.isEmpty());

    // invoke code
    d.addLast("a");

    // verify results;
    assertFalse(d.isEmpty());
    assertEquals("a", d.removeFirst());

    assertTrue(d.isEmpty());
    assertEquals(0, d.size());
  }

  @Test
  public void testAddFirstAddLastRemoveFirst() {
    // dummy data
    Deque<String> d = new Deque<>();
    assertTrue(d.isEmpty());

    // invoke code
    d.addFirst("a");
    d.addLast("b");

    // verify results;
    assertFalse(d.isEmpty());
    assertEquals("a", d.removeFirst());
    assertEquals("b", d.removeFirst());

    assertTrue(d.isEmpty());
    assertEquals(0, d.size());
  }
}
