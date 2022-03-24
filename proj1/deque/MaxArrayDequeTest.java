package deque;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {
    private <T> Comparator<T> createCMP(Function<T, Integer> keyGetter) {
        return (c1, c2) -> keyGetter.apply(c1) - keyGetter.apply(c2);
    }

    @Test
    public void maxTest1() {
        Comparator<Integer> arrayDequeComparator = new ArrayDequeComparator();
        MaxArrayDeque<Integer> maxDeque =
                new MaxArrayDeque<>(arrayDequeComparator);
        /*
            Other approach:
            1. new MaxArrayDeque<>(createCMP(a -> a));
            2. new MaxArrayDeque<>((c1, c2) -> c1 - c2);
         */
        for (int i = 0; i < 10; i++) {
            maxDeque.addFirst(i);
        }
        for (int i = 20; i < 30; i++) {
            maxDeque.addLast(i);
        }
        assertEquals(Integer.valueOf(29), maxDeque.max());
    }

    @Test
    public void maxTest2() {
        MaxArrayDeque<Integer> ald1 = new MaxArrayDeque<Integer>(new ArrayDequeComparator());
        for (int i = 0; i < 1000000; i++) {
            ald1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) ald1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) ald1.removeLast(), 0.0);
        }
        ald1.printDeque();
        System.out.println(ald1.max());
    }

    @Test
    public void maxStudentTest() {
        MaxArrayDeque<Student> maxDeque =
                new MaxArrayDeque<Student>(Comparator.comparingInt(Student::score).reversed());
//                new MaxArrayDeque<>((c1, c2) -> c1.score() - c2.score());
//                new MaxArrayDeque<Student>(createCMP(a -> a.score()));
    }

    private static class ArrayDequeComparator implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

    private record Student(
            String name,
            int score,
            int age) {
    }
}

