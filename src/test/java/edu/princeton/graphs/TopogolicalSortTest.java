package edu.princeton.graphs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;

public class TopogolicalSortTest {

  /**
  <pre>
      (A)----->(B)<-----(C)----->(D) 
       ^\       ^       ^|\       |  
       |  \     |      / |  \     |  
       |    \   |    /   |    \   |  
       |      \ |  /     |      \ |  
       |       v|/       v       vv  
      (E)----->(F)----->(G)<-----(H)
  </pre>
   */
  @Test
  public void testBasic() {
    MultiMap<Character, Character> graph = new MultiMap<>();
    graph.put('A', Arrays.asList('B', 'F'));
    graph.put('C', Arrays.asList('B', 'D', 'G', 'H'));
    graph.put('D', 'H');
    graph.put('E', Arrays.asList('A', 'F'));
    graph.put('F', Arrays.asList('B', 'C', 'G'));
    graph.put('H', 'G');
    System.out.println(graph);
    assertEquals("[E, A, F, C, D, H, G, B]", TopologicalSort.sort(graph).toString());
  }
}
