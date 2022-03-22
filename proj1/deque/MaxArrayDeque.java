package deque;

import java.util.Comparator;
import java.util.HashMap;

public class MaxArrayDeque<T> implements Comparator<T> {
    Comparator<ArrayDeque<T>> comparator;

    public MaxArrayDeque(Comparator<ArrayDeque<T>> c) {
        this.comparator = c;
    }

    // return the maximum element in this arrayDeque
    public T max() {
        HashMap<T, Integer> map = new HashMap<>();
//        int size = this.comparator.size();
//        for (int i = 0; i < this.comparator.size())

        return null;
    }

    @Override
    public int compare(T o1, T o2) {
        return 0;
    }
}
