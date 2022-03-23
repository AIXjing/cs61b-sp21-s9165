package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
  Comparator<T> comparator;

  public MaxArrayDeque(Comparator<T> c) {
    this.comparator = c;
  }

  // return the maximum element in this arrayDeque
  public T max() {
    if (isEmpty()) return null;
    int size = size();
    T max = get(0);
    for (int i = 1; i < size; i++) {
      if (comparator.compare(max, get(i)) < 0) {
        max = get(i);
      }
    }
    return max;
  }

  public T max(Comparator<T> c) {
    if (isEmpty()) return null;
    int size = size();
    T max = get(0);
    for (int i = 1; i < size; i++) {
      if (comparator.compare(max, get(i)) < 0) {
        max = get(i);
      }
    }
    return max;
  }
}
