package deque;

import org.junit.Test;

import java.util.Random;

public class ArrayDequeTest {
    @Test
    public void addLastTest() {
        ArrayDeque<String> arrDeque = new ArrayDeque<>();
        for (int i = 0; i < 50; i++) {
            arrDeque.addLast(String.valueOf(i));
        }
        for (int j = 0; j < 45; j++) {
            arrDeque.removeFirst();
        }
        arrDeque.printDeque();
    }

    @Test
    public void removeFirstItemSmallTest() {
        ArrayDeque<String> arrDeque = new ArrayDeque<>();
        /* add item 0 to 7 from the back of the deque */
        for (int i = 0; i < 10; i++) {
            if (Math.random() < 0.5) arrDeque.addFirst(String.valueOf(i));
            else {
                arrDeque.addLast(String.valueOf(i));
            }
        }
        arrDeque.printDeque();
        /* remove all the items from the front of the deque */
        for (int j = 0; j < 1000; j++) {
            if (Math.random() < 0.3) arrDeque.removeFirst();
            else if (Math.random() < 0.6) arrDeque.removeLast();
            else if (Math.random() < 0.8) arrDeque.addFirst(String.valueOf(j*10));
            else arrDeque.addLast(String.valueOf(j*20));
        }
        arrDeque.printDeque();
    }

    @Test
    public void removeFirstItem() {
        ArrayDeque<String> arrDeque = new ArrayDeque<>();
        /* add item 0 to 49 from the back of the deque */
        for (int i = 0; i < 50; i++) {
            arrDeque.addLast(String.valueOf(i));
        }
        arrDeque.printDeque();
        /* remove item 0 to 41 from the front of the deque */
        /* check whether arrayDeque resized */
        for (int j = 0; j < 42; j++) {
            arrDeque.removeFirst();
        }
        arrDeque.printDeque();
        /* remove item 47 to 49 from the back of the deque */
        System.out.println("1. Remove last " + arrDeque.removeLast());
        System.out.println("2. Remove last " + arrDeque.removeLast());
        System.out.println("3. Remove last " + arrDeque.removeLast());
    }

    @Test
    public void addFirstTest() {
        ArrayDeque<String> arrDeque = new ArrayDeque<>();
        /* add item 0 to 9 from the back of the deque */
        for (int i = 0; i < 10; i++) {
            arrDeque.addLast(String.valueOf(i));
        }
        arrDeque.printDeque();
        /* remove item 0 to 2 from the front of the deque */
        arrDeque.removeFirst(); // remove "0"
        arrDeque.removeFirst(); // remove "1"
        arrDeque.removeFirst(); // remove "2"
        arrDeque.printDeque();
        /* add 5 items 10 to 40 from the front of the deque */
        for (int j = 1; j < 5; j++) {
            arrDeque.addFirst(String.valueOf(j*10));
        }
        arrDeque.printDeque();
    }
}
