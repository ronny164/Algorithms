package edu.princeton.shortestpath;

import edu.princeton.shortestpath.WordNet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;


public class WordNetTest {

  private WordNet wordnet;

  @Before
  public void setup() {
    wordnet =
        new WordNet("./src/test/resources/wordnet/synsets.txt",
            "./src/test/resources/wordnet/hypernyms.txt");
  }

  @Test
  public void testWordNetBasic() {
    assertEquals(5, wordnet.distance("worm", "bird"));
    assertEquals(0, wordnet.distance("worm", "insect"));
    String actual = wordnet.sap("worm", "bird");
    assertNotNull(actual);
    assertEquals("animal animate_being beast brute creature fauna", actual);
  }

  @Test
  public void testWordNetLongDistance() {
    assertEquals(23, wordnet.distance("white_marlin", "mileage"));
    assertEquals(33, wordnet.distance("Black_Plague", "black_marlin"));
    assertEquals(27, wordnet.distance("American_water_spaniel", "histology"));
    assertEquals(29, wordnet.distance("Brown_Swiss", "barrel_roll"));
  }

  @Test
  public void testWordNetTest1_4() {
    assertEquals(15, wordnet.distance("spark_advance", "scale_value"));
    assertEquals("abstraction abstract_entity", wordnet.sap("anchylosis", "shite"));
  }

  @Test
  public void testWordNetTest4_1() {
    assertEquals(6, wordnet.distance("primrose", "sneezer"));
    assertEquals("organism being", wordnet.sap("primrose", "sneezer"));
  }

  @Test
  public void testWordNetTest4_2() {
    assertEquals(13, wordnet.distance("Rhagoletis_pomonella", "Marianas"));
    assertEquals("object physical_object", wordnet.sap("Rhagoletis_pomonella", "Marianas"));
  }

  @Test
  public void testWordNetTest4_3() {
    assertEquals(15, wordnet.distance("carriage_wrench", "acerola"));
    assertEquals("whole unit", wordnet.sap("carriage_wrench", "acerola"));
  }
}
