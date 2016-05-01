package edu.princeton.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ArrayListTest {
  
  @Test
  public void testBasic() {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < 50; i++) {
      list.add(i + 1);
    }
    assertEquals(64, list.capacity());
    assertEquals(50, list.size());
    assertEquals(1, list.get(0).intValue());
    assertEquals(50, list.get(49).intValue());
    list.remove(Integer.valueOf(25));
    assertFalse(list.contains(25));
    assertEquals(27, list.get(25).intValue());
    assertEquals(49, list.size());
    for (int val: list) {
      System.out.print(val + " ");
    }
    System.out.println();
  }
}
