package algs4.tries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import algs4.datastructures.ArrayTrieSet;

import org.junit.Test;

public class ArrayTrieTest {

  @Test
  public void testBasic() {
    ArrayTrieSet set = new ArrayTrieSet('z' + 1 - 'a', 'a');
    assertFalse(set.contains("ronny"));
    set.add("ronny");
    assertEquals("[ronny]", set.values().toString());
    assertTrue(set.contains("ronny"));
    assertFalse(set.contains("ronn"));
    assertFalse(set.contains("other"));
    set.add("ronnie");
    assertEquals("[ronnie, ronny]", set.values().toString());
    assertTrue(set.contains("ronny"));
    assertFalse(set.contains("ronn"));
    assertFalse(set.contains("ronnypena"));
    assertTrue(set.contains("ronnie"));
    set.add("cat");
    set.add("flat");
    set.add("flatten");
    set.add("equation");
    set.add("equations");
    assertTrue(set.contains("equation"));
    assertTrue(set.contains("equations"));
    assertTrue(set.contains("flatten"));
    assertEquals("[ronnie, ronny]", set.valuesWithPrefix("ron").toString());
    assertEquals("[cat, equation, equations, flat, flatten, ronnie, ronny]", set.values()
        .toString());
  }
}