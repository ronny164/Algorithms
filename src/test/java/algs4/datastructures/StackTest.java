package algs4.datastructures;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StackTest {

  @Test
  public void testBasic() {
    Stack<Character> stack = new Stack<>();
    stack.push('a');
    stack.push('b');
    stack.push('c');
    stack.push('d');
    assertEquals("[d, c, b, a]",stack.toString());
  }
}
