package algs4;

public class BinarySearch {

  public static <T extends Comparable> int indexOf(T[] arr, T search) {

    if (arr == null) {
      return -1;
    }

    int start = 0;
    int end = arr.length - 1;
    while (start <= end) {
      int mid = (end + start) / 2;
      int compareTo = arr[mid].compareTo(search);
      if (compareTo == 0) {
        return mid;
      } else if (compareTo < 0) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return -1;
  }
}
