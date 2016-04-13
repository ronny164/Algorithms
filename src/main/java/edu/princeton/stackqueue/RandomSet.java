package edu.princeton.stackqueue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * All operations work in constant time.
 * 
 * @author Ronny A. Pena
 * @param <V>
 */
public class RandomSet<V> {

    private Map<V, Integer> valueIndexes;
    private ArrayList<V> values;
    private Random rand;

    public RandomSet() {
        valueIndexes = new HashMap<>();
        values = new ArrayList<>();
        rand = new Random(System.currentTimeMillis());
    }

    public void add(V value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int key = values.size();
        valueIndexes.put(value, key);
        values.add(value);
    }

    public boolean contains(V value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return valueIndexes.containsKey(value);
    }

    public V getRandom() {
        if (valueIndexes.isEmpty()) {
            throw new NoSuchElementException();
        }
        int randomIndex = rand.nextInt(values.size());
        return values.get(randomIndex);
    }

    public V deleteRandom() {
        if (valueIndexes.isEmpty()) {
            throw new NoSuchElementException();
        }
        int randomIndex = rand.nextInt(values.size());
        return deleteValue(randomIndex);
    }

    public V delete(V value) {
        if (!contains(value)) {
            throw new NoSuchElementException();
        }
        int index = valueIndexes.get(value);
        return deleteValue(index);
    }

    private V deleteValue(int currentIndex) {
        V currentValue = values.get(currentIndex);
        int lastIndex = values.size() - 1;
        V lastVal = values.get(lastIndex);
        Collections.swap(values, currentIndex, lastIndex);
        valueIndexes.put(lastVal, currentIndex);
        values.remove(lastIndex);
        valueIndexes.remove(currentValue);
        return currentValue;
    }

    @Test
    public void testBasic() {
        RandomSet<Character> set = new RandomSet<>();
        for (char val = 'A'; val <= 'Z'; val++) {
            set.add(val);
        }
        for (char val = 'A'; val <= 'Z'; val++) {
            System.out.print(set.deleteRandom());
        }
        System.out.println();
        assertEquals(0, set.valueIndexes.size());
        assertEquals(0, set.values.size());

        for (char val = 'A'; val <= 'Z'; val++) {
            set.add(val);
        }
        for (char val = 'A'; val <= 'Z'; val++) {
            System.out.print(set.delete(val));
        }
        System.out.println();
        assertEquals(0, set.valueIndexes.size());
        assertEquals(0, set.values.size());
    }
}