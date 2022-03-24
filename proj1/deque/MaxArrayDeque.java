package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /* Governed by the comparator given through constructor */
    // return the maximum element in this arrayDeque
    public T max() {
        if (isEmpty()) {
            return null;
        }
        int size = size();
        T max = get(0);
        for (int i = 1; i < size; i++) {
            if (comparator.compare(max, get(i)) < 0) {
                max = get(i);
            }
        }
        return max;
    }

    /* Governed by the comparator given by input */
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int size = size();
        T max = get(0);
        for (int i = 1; i < size; i++) {
            if (c.compare(max, get(i)) < 0) {
                max = get(i);
            }
        }
        return max;
    }
}
