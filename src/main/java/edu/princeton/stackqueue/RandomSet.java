package edu.princeton.stackqueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * All operations work in constant time.
 * 
 * @author Ronny A. Pena
 * @param <T>
 */
public class RandomSet<T> {

  private Map<T, Integer> valueIndexes;
  private List<T> values;
  private Random rand;

  public RandomSet() {
    valueIndexes = new HashMap<>();
    values = new ArrayList<>();
    rand = new Random(System.currentTimeMillis());
  }

  public void add(T value) {
    if (!contains(value)) {
      int key = values.size();
      valueIndexes.put(value, key);
      values.add(value);
    }
  }

  public boolean contains(T value) {
    if (value == null) {
      throw new NullPointerException();
    }
    return valueIndexes.containsKey(value);
  }

  public T getRandom() {
    if (valueIndexes.isEmpty()) {
      throw new NoSuchElementException();
    }
    int randomIndex = rand.nextInt(values.size());
    return values.get(randomIndex);
  }

  public T deleteRandom() {
    if (valueIndexes.isEmpty()) {
      throw new NoSuchElementException();
    }
    int randomIndex = rand.nextInt(values.size());
    return deleteValue(randomIndex);
  }

  public T delete(T value) {
    if (!contains(value)) {
      throw new NoSuchElementException();
    }
    int index = valueIndexes.get(value);
    return deleteValue(index);
  }

  private T deleteValue(int currentIndex) {
    // remove the current element in the array, swap with the last,
    // and update the new index value in the map.
    T currentValue = values.get(currentIndex);
    int lastIndex = values.size() - 1;
    T lastVal = values.get(lastIndex);
    Collections.swap(values, currentIndex, lastIndex);
    valueIndexes.put(lastVal, currentIndex);
    values.remove(lastIndex);
    valueIndexes.remove(currentValue);
    return currentValue;
  }

  public int size() {
    if (values.size() != valueIndexes.size()) {
      // should never happen.
      throw new IllegalStateException();
    }
    return values.size();
  }
}
