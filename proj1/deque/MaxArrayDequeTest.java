package deque;

import org.apache.commons.collections.comparators.ComparableComparator;
import org.junit.Test;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {
    private<T> Comparator<T> createCMP(Function<T, Integer> keyGetter) {
        return (c1,c2) -> keyGetter.apply(c1) - keyGetter.apply(c2);
    }

    @Test
    public void maxTest() {
        MaxArrayDeque<Integer> maxDeque =
//                new MaxArrayDeque<>(createCMP(a -> a));
                new MaxArrayDeque<>((c1, c2) -> c1 - c2);
        new MaxArrayDeque<>(new ComparableComparator());
        for (int i = 0; i < 10; i++) {
            maxDeque.addFirst(i);
        }
        assertEquals(Integer.valueOf(9), maxDeque.max());
    }


    @Test
    public void maxStudentTest() {
        MaxArrayDeque<Student> maxDeque =
                new MaxArrayDeque<Student>(Comparator.comparingInt(Student::score).reversed());
//                new MaxArrayDeque<>((c1, c2) -> c1.score() - c2.score());
//                new MaxArrayDeque<Student>(createCMP(a -> a.score()));
    }
}

record Student(
        String name,
        int score,
        int age
){}
