package deque;

import org.junit.Test;

import java.util.Comparator;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {
    @Test
    public void maxTest() {
        MaxArrayDeque<Integer> maxDeque =
                new MaxArrayDeque<>(Comparator.comparingInt(Integer::intValue));
        for (int i = 0; i < 10; i++) {
            maxDeque.addFirst(i);
        }
        assertEquals(Integer.valueOf(9), maxDeque.max());
    }
}
