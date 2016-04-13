package edu.princeton.shortestancestor;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * WordNet is a semantic lexicon for the English language that is used extensively by computational
 * linguists and cognitive scientists; for example, it was a key component in IBM's Watson.
 * WordNet groups words into sets of synonyms called synsets and describes semantic relationships
 * between them. One such relationship is the is-a relationship, which connects a
 * hyponym (more specific synset) to a hypernym (more general synset).
 * For example, a plant organ is a hypernym of carrot and plant organ is a hypernym of plant root.
 *
 * @author Ronny A. Pena
 */
public class WordNet {

  private BiMultiMap<String, Integer> wordMap;
  private ShortestAncestralPath sap;

  /**
   * Constructor takes the name of the two input files. The generated graph is validated.
   *
   * @param synsetsFileName The synsets file name.
   * @param hypernymsFileName The hypernyms file name.
   */
  public WordNet(String synsetsFileName, String hypernymsFileName) {
    if (synsetsFileName == null || hypernymsFileName == null) {
      throw new NullPointerException();
    }
    this.wordMap = new BiMultiMap<>();
    readWords(synsetsFileName);
    Digraph dag = new Digraph(wordMap.size());
    readHyphens(dag, hypernymsFileName);
    this.sap = new ShortestAncestralPath(dag);
  }

  private void readWords(String synsetsFileName) {
    In synsetsIn = new In(synsetsFileName);
    while (synsetsIn.hasNextLine()) {
      String line = synsetsIn.readLine();
      String[] split = line.split(",");
      int synseId = Integer.parseInt(split[0]);
      split = split[1].split(" ");
      for (String synset : split) {
        wordMap.put(synset, synseId);
      }
    }
  }

  private void readHyphens(Digraph dag, String hypernymsFileName) {
    In hypernymsIn = new In(hypernymsFileName);

    Set<Integer> startVertices = new HashSet<>();
    while (hypernymsIn.hasNextLine()) {
      String line = hypernymsIn.readLine();
      String[] split = line.split(",");
      int vertexV = Integer.parseInt(split[0]);
      startVertices.add(vertexV);
      if (vertexV >= dag.V() || vertexV < 0) {
        throw new IllegalArgumentException();
      }
      for (int i = 1; i < split.length; i++) {
        int vertexW = Integer.parseInt(split[i]);
        if (vertexW >= dag.V() || vertexW < 0) {
          throw new IllegalArgumentException();
        }
        dag.addEdge(vertexV, vertexW);
        startVertices.remove(vertexW);
      }
    }
    validateGraph(dag, startVertices);
  }

  private void validateGraph(Digraph dag, Set<Integer> startVertices) {

    // Using depth first search.
    Deque<Integer> stack = new LinkedList<>();
    boolean[] visited = new boolean[dag.V()];
    Set<Integer> roots = new HashSet<>();
    for (int v : startVertices) {
      stack.add(v);
    }
    while (!stack.isEmpty()) {
      int currentVertex = stack.pop();
      Iterable<Integer> adjs = dag.adj(currentVertex);
      if (adjs != null) {
        for (int w : adjs) {
          if (!visited[w]) {
            visited[w] = true;
            stack.push(w);
          }
        }
      }

      // this is a root
      if (dag.outdegree(currentVertex) == 0) {
        roots.add(currentVertex);
      }
    }

    if (roots.size() != 1) {
      // no roots or more than one root
      throw new IllegalArgumentException();
    }
  }

  /**
   * Returns all WordNet nouns.
   * @return all WordNet nouns.
   */
  public Iterable<String> nouns() {
    return wordMap.keySet();
  }

  /**
   * Is the word a WordNet noun?
   *
   * @param word The word to check.
   * @return whether the word is a noun.
   */
  public boolean isNoun(String word) {
    if (word == null) {
      throw new NullPointerException();
    }
    return wordMap.containsKey(word);
  }

  /**
   * Generates the distance of shortest ancestral path between nounA and nounB.
   *
   * @param nounA The nounA noun.
   * @param nounB The nounB noun.
   * @return The distance of the ancestor or throws an exception if not found.
   */
  public int distance(String nounA, String nounB) {
    if (isNoun(nounA) && isNoun(nounB)) {
      return sap.length(wordMap.getValues(nounA), wordMap.getValues(nounB));
    }
    throw new IllegalArgumentException();
  }

  /**
   * Generates a synset that is the common ancestor of nounA and nounB
   * that participates in a shortest ancestral path.
   *
   * @param nounA The nounA noun.
   * @param nounB The nounB noun.
   * @return The ancestor or throws an exception if not found.
   */
  public String sap(String nounA, String nounB) {
    if (isNoun(nounA) && isNoun(nounB)) {
      int ancestor = sap.ancestor(wordMap.getValues(nounA), wordMap.getValues(nounB));
      if (ancestor == -1) {
        throw new IllegalArgumentException();
      }
      Iterator<String> keys = wordMap.getKeys(ancestor).iterator();
      StringBuilder sb = new StringBuilder(keys.next());
      while (keys.hasNext()) {
        sb.append(" ").append(keys.next());
      }
      return sb.toString();
    }
    throw new IllegalArgumentException();
  }
}
