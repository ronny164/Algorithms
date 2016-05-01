package edu.princeton.list;

import java.util.Iterator;

public class ArrayList<T> implements Iterable<T> {
  private static final int THRESHOLD = 16;
  private T[] arr;
  private int size;
  private int capacity;

  public ArrayList() {
    this(THRESHOLD);
  }

  public ArrayList(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException();
    }
    this.arr = (T[]) new Object[initialCapacity];
    this.capacity = initialCapacity;
  }

  private void validateIndex(int index) {
    if (index < 0 || index >= size) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  private void amortized(int newSize) {
    if (newSize > capacity) {
      capacity *= 2;
      move();
    } else if (newSize <= (capacity / 4) && newSize > THRESHOLD) {
      capacity /= 2;
      move();
    }
  }

  private void move() {
    T[] newArr = (T[]) new Object[capacity];
    System.arraycopy(arr, 0, newArr, 0, size);
    this.arr = newArr;
  }

  private void shiftRight(int index) {
    if (index != size - 1) {
      System.arraycopy(arr, index, arr, index + 1, size - (index + 1));
    }
  }
  
  private void shiftLeft(int index) {
    if (index != size - 1) {
      System.arraycopy(arr, index + 1, arr, index, size - (index + 1));
    }
  }
  
  public void add(T val) {
      amortized(size + 1);
      arr[size++] = val;
  }

  public void add(int index, T val) {
    validateIndex(index); 
    amortized(size + 1);
    shiftRight(index);
    arr[index] = val;
    size++;
  }
  
  public void remove(int index) {
    validateIndex(index);
    T temp = arr[index];
    shiftLeft(index);
    arr[size--] = null;
    amortized(size);
  }

  public boolean remove(T val) {
    int index = indexOf(val);
    if (index == -1) { 
      return false;
    }
    remove(index);
    return true;
  }

  public int indexOf(T val) {
    for (int i = 0; i < size; i++) {
      T element = arr[i];
      if (element != null && element.equals(val)) {
        return i;
      }
    }
    return -1;
  }

  public boolean contains(T val) {
    return indexOf(val) != -1;
  }

  @Override
  public Iterator<T> iterator() {
    return new ListIterator<T>(this);
  }

  public int size() {
    return size;
  }
  
  public int capacity() {
    return capacity;
  }

  public T get(int index) {
    validateIndex(index);
    return arr[index];
  }
  
  public static <T> void swap(ArrayList<T> list, int i, int j) {
    T temp = list.arr[i];
    list.arr[i] = list.arr[j];
    list.arr[j] = temp;
  }

  private static class ListIterator<T> implements Iterator<T> {
    private int index;
    private int size;
    private ArrayList<T> list;
    public ListIterator(ArrayList<T> arrayList) {
      this.list = arrayList;
      this.index = 0;
      this.size = arrayList.size();
    }

    @Override
    public boolean hasNext() {
      return index < size;
    }

    @Override
    public T next() {
      return list.get(index++);
    }
  }

  @Override
  public String toString() {

    Iterator<T> it = iterator();
    if (! it.hasNext()) {
      return "[]";
    }

    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for (;;) {
        T e = it.next();
        sb.append(e == this ? "(this Collection)" : e);
        if (! it.hasNext()) {
          return sb.append(']').toString();
        }
        sb.append(',').append(' ');
    }
  }
}