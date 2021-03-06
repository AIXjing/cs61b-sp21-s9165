package deque;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Performs some basic linked list tests.
 */
public class LinkedListDequeTest {

    @Test
    /**
     * Adds a few things to the list, checking isEmpty() and size() are correct, finally printing the
     * results.
     *
     * <p>&& is the "and" operation.
     */
    public void addIsEmptySizeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
        //        lld1.addFirst("1");
        //        lld1.addFirst("2");
        //        lld1.addLast("last1");
        //        lld1.addLast("last2");

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    public void removeLastTestSelfMade() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
        lld1.addFirst("a");
        lld1.addLast("b");
        lld1.addFirst("c");
        lld1.addFirst("d");
        lld1.addFirst("e");
        lld1.printDeque();
        System.out.println("Remove " + lld1.removeFirst() + ", the current size is " + lld1.size());
        System.out.println("Remove " + lld1.removeFirst() + ", the current size is " + lld1.size());
        System.out.println("Remove " + lld1.removeLast() + ", the current size is " + lld1.size());
        System.out.println("Remove " + lld1.removeLast() + ", the current size is " + lld1.size());
        System.out.println("Remove " + lld1.removeLast() + ", the current size is " + lld1.size());
    }

    @Test
    public void getTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
        lld1.addFirst("a");
        lld1.addLast("b");
        lld1.addFirst("c");
        lld1.addFirst("d");
        lld1.addFirst("e");
        lld1.printDeque();
        System.out.println("get(0): " + lld1.get(0));
        System.out.println("get(1): " + lld1.get(1));
        System.out.println("getRecursive(0) " + lld1.getRecursive(0));
        System.out.println("getRecursive(1) " + lld1.getRecursive(1));
        System.out.println("getRecursive(3) " + lld1.getRecursive(3));
        System.out.println("getRecursive(10) " + lld1.getRecursive(10));
        //        System.out.println("get(3): " + lld1.get(3));
        //        System.out.println("get(10): " + lld1.get(10));
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {


        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double> lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    public void TestIterator() {
        LinkedListDeque<String> linkedDeque = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) linkedDeque.addFirst(i + ". addFirst");
            else linkedDeque.addLast(i + ". addLast");
        }
        for (String s : linkedDeque) {
            System.out.println(s);
        }
    }

    @Test
    public void TestEquals() {
        LinkedListDeque<String> linkedDeque1 = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) linkedDeque1.addFirst(i + ". addFirst");
            else linkedDeque1.addLast(i + ". addLast");
        }

        LinkedListDeque<String> linkedDeque2 = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) linkedDeque2.addLast(i + ". addLast");
            else linkedDeque2.addFirst(i + ". addFirst");
        }
        Assert.assertEquals(false, linkedDeque1.equals(linkedDeque2));
        linkedDeque1.get(9);
        Assert.assertEquals(true, linkedDeque2.equals(linkedDeque2));
    }
}
