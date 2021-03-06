package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private final IntNode sentinel;
    private int size;

    /**
     * The first item (if it exists) is at sentinel.next.
     */
    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Adds x to the front of the list.
     */
    public void addFirst(T x) {
        IntNode p = sentinel;
        IntNode tail = sentinel.next;
        p.next = new IntNode(x, sentinel, tail);
        tail.prev = p.next;
        size = size + 1;
    }

    /**
     * Adds x to the end of the list.
     */
    public void addLast(T x) {
        IntNode last = sentinel.prev; // get the last node
        IntNode newLast = new IntNode(x, last, sentinel); // create a new last node
        last.next = newLast; // link the previous node with the new last node
        sentinel.prev = newLast; // link the new last node with sentinel.prev
        size = size + 1;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        if (isEmpty()) {
            System.out.println();
            return;
        }
        IntNode nextNode = sentinel.next;
        for (int i = 0; i < this.size; i++) {
            System.out.print(nextNode.item + " ");
            nextNode = nextNode.next;
        }
        System.out.println();
    }

    //    public boolean isEmpty() {
    //        if (size == 0) return true;
    //        return false;
    //    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        IntNode first = sentinel.next;
        T firstItem = first.item;
        IntNode newFirst = sentinel.next.next;
        sentinel.next = newFirst; // link sentinel next with new First node
        newFirst.prev = sentinel; // link new First node prev with sentinel
        size -= 1;
        first = new IntNode(null, null, null); // after unlink the old first node, null it
        //        first = new IntNode(null,null,null);
        return firstItem;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        IntNode last = sentinel.prev;
        T lastItem = last.item;
        IntNode newLast = last.prev;
        newLast.next = sentinel;
        sentinel.prev = newLast;
        size -= 1;
        last = new IntNode(null, null, null);
        return lastItem;
    }

    /* get i, return T */
    public T get(int index) {
        IntNode node = getNode(index);
        return node.item;
    }

    /* get i, return T */
    public T getRecursive(int index) {
        if (index > size) {
            return null;
        }
        return getRecursiveHelp(index, sentinel.next).item;
    }

    /* helper method for get(i), return IntNode */
    private IntNode getNode(int index) {
        if (index >= size) {
            return sentinel;
        }
        if (index == 0) {
            return sentinel.next;
        }
        IntNode nextNode = sentinel.next;
        for (int i = 1; i <= index; i++) {
            nextNode = nextNode.next;
        }
        return nextNode;
    }

    /* a help method for getRecursive, return IntNode */
    private IntNode getRecursiveHelp(int index, IntNode startNode) {
        if (index == 0) {
            return startNode;
        }
        startNode = startNode.next;
        return getRecursiveHelp(index - 1, startNode);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque<?>)) {
            return false;
        }
        if (((Deque<?>) o).size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(((Deque<?>) o).get(i))) {
                return false;
            }
        }
        return true;
    }

    /* create a nested class implements Iterator interface */
    private class LinkedListDequeIterator implements Iterator<T> {
        private int currPos;

        public LinkedListDequeIterator() {
            this.currPos = 0;
        }

        @Override
        public boolean hasNext() {
            return currPos < size();
        }

        @Override
        public T next() {
            return get(currPos++);
        }
    }

    private class IntNode {
        private final T item;
        private IntNode next;
        private IntNode prev;

        private IntNode(T i, IntNode prev, IntNode next) {
            this.item = i;
            this.prev = prev;
            this.next = next;
        }
    }
}
