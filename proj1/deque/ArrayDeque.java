package deque;

public class ArrayDeque<T> implements Deque<T> {
  private final double REFACTOR = 2.0;
  protected int head;
  protected int tail;
  private T[] items;
  private int size;

  /** Creates an empty list. */
  public ArrayDeque() {
    this.items = (T[]) new Object[8];
    this.head = 0;
    this.tail = 1;
    this.size = 0;
  }

  /** Inserts item into the front of the list. */
  public void addFirst(T item) {
    items[head] = item;
    head -= 1;
    if (head == -1) {
      head = items.length - 1;
    }
    size += 1;
    if (head == tail) {
      resize((int) (items.length * REFACTOR));
    }
  }

  /** Inserts X into the back of the list. */
  public void addLast(T item) {
    items[tail] = item;
    tail += 1;
    if (tail == items.length) {
      tail = 0;
    }
    size += 1;
    if (head == tail) {
      resize((int) (items.length * REFACTOR));
    }
  }

  /** Remove item from the front of the list. */
  public T removeFirst() {
    if (isEmpty()) return null;
    head = (head + 1) % items.length;
    T firstItem = items[head];
    items[head] = null;
    size -= 1;

      // resizing
      if ((size < items.length / 4) && (size > 8)) {
        resize(items.length / 2);
      }

    return firstItem;
  }

  /** Deletes item from back of the list and returns deleted item. */
  public T removeLast() {
    if (isEmpty()) return null;
    tail = ((tail - 1) + items.length) % items.length;
    T lastItem = items[tail];
    items[tail] = null;
    size -= 1;

    if ((size < items.length / 4) && (size > 8)) {
        resize(items.length / 2);
      }

    return lastItem;
  }

  public void printDeque() {
    if (isEmpty()) {
      System.out.println("empty");
      return;
    }
    for (int i = 0; i < size; i++) {
      System.out.print(get(i) + " ");
    }
    System.out.println();
  }

//  public boolean isEmpty() {
//    if (size == 0) return true;
//    return false;
//  }

  /** Gets the ith item in the list from the frontIndex. */
  public T get(int i) {
    if (isEmpty()) return null;
    return items[head + i + 1];
  }

  /** Returns the number of items in the list. */
  public int size() {
    return this.size;
  }

  public int capacity() {
    return this.items.length;
  }

  /** Resizes the underlying array to the target capacity. */
  private void resize(int capacity) {
    if (capacity < size + 2) capacity = size + 2;

    T[] a = (T[]) new Object[capacity];
    if (head >= tail && !isEmpty()) {
      // System.out.println("head: " + head + ", tail: " + tail + ", size: " + size);
      System.arraycopy(items, head, a, 0, items.length - head);
      System.arraycopy(items, 0, a, items.length - head, tail);
    } else {
      System.arraycopy(items, head, a, 0, size + 1);
    }
    items = a;
    head = 0;
    tail = size + 1;
  }
}
