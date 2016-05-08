package algs4.datastructures;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Iterator;

public class HashtableTest {

  @Test
  public void testBasic() {
    Hashtable<Character, Integer> table = new Hashtable<>();
    for (char c = '0'; c <= 'z'; c++) {
      table.put(c, c - '0' + 1);
    }
    assertEquals(75, table.size());
    assertEquals(18, table.get('A').intValue());
    assertEquals(43, table.get('Z').intValue());
    Iterator<Character> keySet = table.keySet().iterator();
    ArrayList<Character> list = new ArrayList<>();
    for (Character c : table.keySet()) {
      System.out.println(c);
      list.add(c);
    }
    System.out.println("count: " + list.size());

    assertEquals(table.size(), list.size());
  }
}
