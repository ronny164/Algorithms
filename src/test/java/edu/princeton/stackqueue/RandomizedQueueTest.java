package edu.princeton.stackqueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RandomizedQueueTest {

  @Test
  public void testRandomizedQueue() {
    RandomizedQueue<String> q = new RandomizedQueue<>();
    assertTrue(q.isEmpty());

    int sampleSize = 8;
    List<String> sample = getSample(sampleSize);

    q.enqueue(sample.get(0));
    assertEquals(sample.get(0), q.sample());

    q.enqueue(sample.get(1));

    assertEquals(2, q.size());
    assertFalse(q.isEmpty());


    for (int i = 2; i < sampleSize; i++) {
      q.enqueue(sample.get(i));
    }
    assertEquals(sampleSize, q.size());

    for (int i = 0; i < sampleSize; i++) {
      String val = q.dequeue();
      System.out.println(val + " ");
      assertTrue(sample.remove(val));
    }
    assertEquals(0, q.size());
  }

  private List<String> getSample(int sampleSize) {
    List<String> sample = new ArrayList<>();
    for (int i = 0; i < sampleSize; i++) {
      sample.add(i + "");
    }
    return sample;
  }

  @Test
  public void testRadomizedQueueIterator() {
    RandomizedQueue<String> q = new RandomizedQueue<>();
    int sampleSize = 8;
    List<String> sample = getSample(sampleSize);

    for (int i = 0; i < sampleSize; i++) {
      q.enqueue(sample.get(i));
    }

    q.dequeue();

    int count = sampleSize - 1;
    for (String val : q) {
      System.out.println(val);
      sample.remove(val);
      count--;
    }
    assertEquals(0, count);

    System.out.println(sample);
    assertTrue((sampleSize * 3) / 4 >= sample.size());
  }
}
