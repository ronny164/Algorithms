package edu.princeton.stackqueue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RandomSetTest {


  @Test
  public void testBasic() {
    RandomSet<Character> set = new RandomSet<>();
    for (char val = 'A'; val <= 'Z'; val++) {
      set.add(val);
    }
    for (char val = 'A'; val <= 'Z'; val++) {
      System.out.print(set.deleteRandom());
    }
    System.out.println();
    assertEquals(0, set.size());
    assertEquals(0, set.size());

    for (char val = 'A'; val <= 'Z'; val++) {
      set.add(val);
    }
    for (char val = 'A'; val <= 'Z'; val++) {
      System.out.print(set.delete(val));
    }
    System.out.println();
    assertEquals(0, set.size());
    assertEquals(0, set.size());
  }
}
