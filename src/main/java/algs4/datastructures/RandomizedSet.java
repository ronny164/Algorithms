package algs4.datastructures;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 * All operations work in constant time.
 * 
 * @author Ronny A. Pena
 */
public class RandomizedSet<T> {

  private Hashtable<T, Integer> valueIndexes;
  private ArrayList<T> values;
  private Random rand;

  public RandomizedSet() {
    valueIndexes = new Hashtable<>();
    values = new ArrayList<>();
    rand = new Random(System.currentTimeMillis());
  }

  public void add(T value) {
    if (!contains(value)) {
      int lastIndex = values.size();
      valueIndexes.put(value, lastIndex);
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
    ArrayList.swap(values, currentIndex, lastIndex);
    // removing the last element is constant
    values.remove(lastIndex);
    valueIndexes.put(lastVal, currentIndex);
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
