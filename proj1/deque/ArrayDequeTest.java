package deque;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
  public void removeLastTest() {
    ArrayDeque<String> arrDeque = new ArrayDeque<>();
    arrDeque.addLast("addLast1");
    arrDeque.addLast("addLast2");
    assertEquals("addLast2", arrDeque.removeLast());
    assertEquals("addLast1", arrDeque.removeLast());
    assertNull(arrDeque.removeLast());
  }

  @Test
  public void addFirst() {
    ArrayDeque<String> arrDeque = new ArrayDeque<>();
    arrDeque.addFirst("1");
    arrDeque.addFirst("2");
    arrDeque.addFirst("3");
  }

  @Test
  public void removeFillupTest() {
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
    for (int j = 0; j < 10000; j++) {
      if (Math.random() < 0.3) arrDeque.removeFirst();
      else if (Math.random() < 0.6) arrDeque.removeLast();
      else if (Math.random() < 0.8) arrDeque.addFirst(j * 10 + "addFirst");
      else arrDeque.addLast(j * 20 + "addLast");
    }
    arrDeque.printDeque();
  }

  @Test
  public void removeFirstItem1() {
    ArrayDeque<String> arrDeque = new ArrayDeque<>();
    arrDeque.addFirst("first");
    arrDeque.addFirst("second");
    assertEquals("second", arrDeque.removeFirst());
    assertEquals("first", arrDeque.removeFirst());
    assertEquals(0, arrDeque.head);
    assertEquals(1, arrDeque.tail);
  }

  @Test
  public void removeFillupAssertTest() {
    ArrayDeque<Integer> arrDeque = new ArrayDeque<>();
    for (int i = 0; i < 1000; i++) {
      arrDeque.addLast(i);
    }
    System.out.println(arrDeque.capacity());
    assertEquals(1000, arrDeque.size());

    for (int i = -1; i > -1000; i--) {
      arrDeque.addFirst(i);
    }
    System.out.println(arrDeque.capacity());
    assertEquals(1999, arrDeque.size());

    for (int i = -999; i < 1000; i++) {
      Integer first = arrDeque.removeFirst();
      assertEquals(i, first.intValue());
    }
    assertEquals(0, arrDeque.size());
      System.out.println(arrDeque.capacity());
  }

  @Test
  public void removeFillupAssertTest2() {
    ArrayDeque<Integer> arrDeque = new ArrayDeque<>();
    for (int i = 0; i < 1000; i++) {
      arrDeque.addLast(i);
    }
    assertEquals(1000, arrDeque.size());
    for (int i = -1; i > -1000; i--) {
      arrDeque.addFirst(i);
    }
    assertEquals(1999, arrDeque.size());
    for (int i = 999; i > -1000; i--) {
      Integer first = arrDeque.removeLast();
      assertEquals(i, first.intValue());
    }
    assertEquals(0, arrDeque.size());
  }
}
