package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int frontIndex;
    private int backIndex;
    private int size;

    /** Creates an empty list. */
    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.frontIndex = 0;
        this.backIndex = 0;
        this.size = 0;
    }

    /** Inserts item into the front of the list. */
    public void addFirst(T item){
        if (size < items.length) {
            if (frontIndex != 0) {
                frontIndex = frontIndex - 1;
                items[frontIndex] = item;
            }
            else {
                backIndex = backIndex + 1;
                items[backIndex] = item;
            }
        }
        else if (size == items.length) {
            resize((int) (size * 1.25));
            backIndex = backIndex + 1;
            items[backIndex] = item;
        }
        size += 1;
    }

    /** Inserts X into the back of the list. */
    public void addLast(T item) {
        if (size == items.length) {
            resize((int) (size * 1.25));
        }
        items[size] = item;
        backIndex = size;
        size = size + 1;
    }

    /** Remove item from the front of the list. */
    public T removeFirst() {
        if(isEmpty()) return null;
        T firstItem = items[frontIndex];
        items[frontIndex] = null;
        size -= 1;
        if (size == 0) {
            frontIndex = 0;
            backIndex = 0;
            resize(8);
        }
        else {
            frontIndex += 1;
            // resizing
            if ((size < items.length / 4) && (size > 8) ) {
                T[] a = (T[]) new Object[items.length / 4];
                System.arraycopy(items, frontIndex, a, 0, size);
                items = a;
                frontIndex = 0;
                backIndex = size - 1;
            }
        }
        return firstItem;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public T removeLast() {
        if(isEmpty()) return null;
        T lastItem = items[backIndex];
        items[backIndex] = null;
        size -= 1;
        if (size == 0) {
            backIndex = 0;
            frontIndex = 0;
            resize(8);
        }
        else {
          backIndex -= 1;
            if ((size < items.length / 4) && (size > 8) ) {
                T[] a = (T[]) new Object[items.length / 4];
                System.arraycopy(items, frontIndex, a, 0, size);
                items = a;
                frontIndex = 0;
                backIndex = size - 1;
            }
        }
        return lastItem;
    }

    public void printDeque(){
        if(isEmpty()) {
            System.out.println("empty");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public boolean isEmpty(){
        if (size == 0) return true;
        return false;
    }

    /** Gets the ith item in the list from the frontIndex. */
    public T get(int i) {
        if(isEmpty()) return null;
        return items[i + frontIndex];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return this.size;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }
}