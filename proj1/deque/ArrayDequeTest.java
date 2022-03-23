package deque;

import org.junit.Assert;
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
//    arrDeque.printDeque();
    /* remove all the items from the front of the deque */
    for (int j = 0; j < 10000; j++) {
      if (Math.random() < 0.25) arrDeque.removeFirst();
      else if (Math.random() < 0.4) arrDeque.removeLast();
      else if (Math.random() < 0.8) arrDeque.addFirst(j * 10 + "addFirst");
      else arrDeque.addLast(j * 20 + "addLast");
    }
    //    arrDeque.printDeque();
    System.out.println(arrDeque.get(0));
    arrDeque.isEmpty();
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

    System.out.println("get(0): " + arrDeque.get(0));
    System.out.println("arrayDeque capacity: " + arrDeque.capacity());
    assertEquals(1999, arrDeque.size());

    for (int i = -999; i < 1000; i++) {
      Integer first = arrDeque.removeFirst();
      assertEquals(i, first.intValue());
    }
    assertEquals(0, arrDeque.size());
    System.out.println("arrayDeque capacity: " + arrDeque.capacity());
    System.out.println("get(0): " + arrDeque.get(0));
  }

  @Test
  public void addGetTest() {
    ArrayDeque<String> arrDeque = new ArrayDeque<>();
    for (int i = 0; i < 10; i++) {
      if (i % 2 == 0) arrDeque.addFirst(i + ". addFirst");
      else arrDeque.addLast(i + ". addLast");
    }
    assertEquals(10, arrDeque.size());
    arrDeque.printDeque();
    for (int i = 0; i < 10; i++) {
      System.out.println("get(" + i + "): " + arrDeque.get(i));
    }
    for (int i = 0; i < 10; i++) {
      if (i % 2 == 0) arrDeque.removeFirst();
      else arrDeque.removeLast();
    }
    System.out.println(arrDeque.get(0));
  }

  @Test
  public void TestIterator() {
    ArrayDeque<String> arrDeque = new ArrayDeque<>();
    for (int i = 0; i < 10; i++) {
      if (i % 2 == 0) arrDeque.addFirst(i + ". addFirst");
      else arrDeque.addLast(i + ". addLast");
    }
    for(String s : arrDeque) {
      System.out.println(s);
    }
  }

  @Test
  public void TestEquals() {
    ArrayDeque<String> arrDeque1 = new ArrayDeque<>();
    for (int i = 0; i < 10; i++) {
      if (i % 2 == 0) arrDeque1.addFirst(i + ". addFirst");
      else arrDeque1.addLast(i + ". addLast");
    }

    ArrayDeque<String> arrDeque2 = new ArrayDeque<>();
    for (int i = 0; i < 10; i++) {
      if (i % 2 == 0) arrDeque2.addLast(i + ". addLast");
      else arrDeque2.addFirst(i + ". addFirst");
    }
    Assert.assertEquals(false, arrDeque1.equals(arrDeque2));
    Assert.assertEquals(true, arrDeque1.equals(arrDeque1));
  }
}
