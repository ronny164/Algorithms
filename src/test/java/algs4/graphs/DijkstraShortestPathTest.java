package algs4.graphs;

import static org.junit.Assert.assertEquals;
import algs4.datastructures.ArrayList;
import algs4.graphs.DijkstraShortestPath.Graph;
import algs4.graphs.DijkstraShortestPath.WeightedEdge;

import org.junit.Test;

/**
 * @author Ronny A. Pena
 */
public class DijkstraShortestPathTest {
  
/**
<pre>
    (A)<-----16-----(B)<-----1------(C)------34---->(D) 
     ^              ^^              ^^\              |  
     |             / |             / | \             |  
     |            /  |            /  |  \            |  
     |           /   |           /   |   \           |  
     |          /    |          /    |    \          |  
     |         /     |         /     |     \         |  
     |        /      |        /      |      \        |  
     20      6       12      21      7       71      30 
     |      /        |      /        |        \      |  
     |     /         |     /         |         \     |  
     |    /          |    /          |          \    |  
     |   /           |   /           |           \   |  
     |  /            |  /            |            \  |  
     | /             | /             |             \ |  
     |/              |/              |              vv  
    (E)<-----4------(F)------11---->(G)------80---->(H) 
</pre>
 */
  @Test
  public void testHomeworkAssignment() {
    ArrayList<WeightedEdge<Character>> edges =
        ArrayList.asList(
            new WeightedEdge<>('B', 'A', 16),
            new WeightedEdge<>('C', 'B', 01),
            new WeightedEdge<>('C', 'D', 34),
            new WeightedEdge<>('C', 'H', 71),
            new WeightedEdge<>('D', 'H', 30),
            new WeightedEdge<>('E', 'A', 20),
            new WeightedEdge<>('E', 'B', 06),
            new WeightedEdge<>('F', 'B', 12),
            new WeightedEdge<>('F', 'C', 21),
            new WeightedEdge<>('F', 'E', 04),
            new WeightedEdge<>('F', 'G', 11),
            new WeightedEdge<>('G', 'C', 07),
            new WeightedEdge<>('G', 'H', 80));
    Graph<Character> graph = new Graph<>(edges);
    DijkstraShortestPath<Character> algo = new DijkstraShortestPath<>();
    assertEquals("[F, G, C, D, H]", algo.computeShorteshPath(graph, 'F', 'H').toString());
    assertEquals(82, algo.getTotalDistance('H'));
  }

  @Test
  public void testLectureExample() {
    ArrayList<WeightedEdge<Integer>> edges =
        ArrayList.asList(
            new WeightedEdge<>(0, 1,  5),
            new WeightedEdge<>(0, 4,  9),
            new WeightedEdge<>(0, 7,  8),
            new WeightedEdge<>(1, 2, 12),
            new WeightedEdge<>(1, 3, 15),
            new WeightedEdge<>(1, 7,  4),
            new WeightedEdge<>(2, 3, 03),
            new WeightedEdge<>(2, 6, 11),
            new WeightedEdge<>(3, 6,  9),
            new WeightedEdge<>(4, 5, 04),
            new WeightedEdge<>(4, 6, 20),
            new WeightedEdge<>(4, 7, 05),
            new WeightedEdge<>(5, 2, 01),
            new WeightedEdge<>(5, 6, 13),
            new WeightedEdge<>(7, 5,  6),
            new WeightedEdge<>(7, 2,  7));
    Graph<Integer> graph = new Graph<>(edges);
    DijkstraShortestPath<Integer> algo = new DijkstraShortestPath<>();
    assertEquals("[0, 4, 5, 2, 6]", algo.computeShorteshPath(graph, 0, 6).toString());
    assertEquals(25, algo.getTotalDistance(6));
  }
}