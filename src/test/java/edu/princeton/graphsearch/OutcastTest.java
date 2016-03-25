package edu.princeton.graphsearch;

import static org.junit.Assert.assertEquals;
import edu.princeton.cs.algs4.In;

import org.junit.Before;
import org.junit.Test;

public class OutcastTest {

  private static final String WORDNET_SYNSETS_LOCATION = "./src/test/resources/wordnet/synsets.txt";
  private WordNet wordnet;
  private Outcast outcast;

  @Before
  public void setUp() {
    wordnet = new WordNet(WORDNET_SYNSETS_LOCATION,"./src/test/resources/wordnet/hypernyms.txt");
    outcast = new Outcast(wordnet);
  }
  
  @Test
  public void testOutcastBasic() {
    String[] fileNames = new String[] {
        "./src/test/resources/wordnet/outcast5.txt", 
        "./src/test/resources/wordnet/outcast8.txt",
        "./src/test/resources/wordnet/outcast11.txt"};
    String[] expected = new String[] {"table", "bed", "potato"};
    
    for (int i = 0; i < fileNames.length; i++) {
      String fileName = fileNames[i];
      In in = new In(fileName);
      String[] nouns = in.readAllStrings();
      String outcastVal = outcast.outcast(nouns);
      System.out.println(fileName + ": " + outcastVal);
      assertEquals(expected[i], outcastVal);
    }
  }

  @Test
  public void testOutcast3() {
    In in = new In("./src/test/resources/wordnet/outcast3.txt");
    String[] nouns = in.readAllStrings();
    assertEquals("outcast-3 ", "Mickey_Mouse", outcast.outcast(nouns));
  }

  @Test
  public void testOutcast4() {
    In in = new In("./src/test/resources/wordnet/outcast4.txt");
    String[] nouns = in.readAllStrings();
    assertEquals("outcast-4 ", "probability", outcast.outcast(nouns));
  }

  @Test
  public void testOutcast5() {
    In in = new In("./src/test/resources/wordnet/outcast5.txt");
    String[] nouns = in.readAllStrings();
    assertEquals("outcast-5 ", "table", outcast.outcast(nouns));
  }
}