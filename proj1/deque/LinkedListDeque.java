package deque;

import edu.princeton.cs.algs4.In;

public class LinkedListDeque<T> implements Deque<T> {
    private class IntNode {
        public T item;
        public IntNode next;
        public IntNode prev;

        public IntNode(T i, IntNode prev, IntNode next) {
            this.item = i;
            this.prev = prev;
            this.next = next;
        }
    }

    private IntNode sentinel;
    private int size;


    /** The first item (if it exists) is at sentinel.next. */
    public LinkedListDeque(){
        sentinel = new IntNode(null, null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Adds x to the front of the list. */
    public void addFirst(T x) {
        IntNode p = sentinel;
        IntNode tail = sentinel.next;
        p.next = new IntNode(x, sentinel, tail);
        tail.prev = p.next;
        size = size + 1;
    }

    /** Adds x to the end of the list. */
    public void addLast(T x) {
        IntNode last = sentinel.prev; // get the last node
        IntNode newLast = new IntNode(x, last, sentinel); // create a new last node
        last.next = newLast; // link the previous node with the new last node
        sentinel.prev = newLast; // link the new last node with sentinel.prev
        size = size + 1;
    }

    public int size(){
        return this.size;
    }

//    public boolean isEmpty() {
//        if (size == 0) return true;
//        return false;
//    }

    public void printDeque(){
        if(isEmpty()) {
            System.out.println();
            return;
        }
        IntNode nextNode = sentinel.next;
       for (int i = 0; i < this.size; i++) {
            System.out.println(nextNode.item);
            nextNode = nextNode.next;
        }
    }

    public T removeFirst() {
        if(isEmpty()) return null;
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
        if(isEmpty()) return null;
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
        if(index >= size) return null;
        return getRecursiveHelp(index, sentinel).item;
    }

    /* helper method for get(i), return IntNode */
    private IntNode getNode(int index) {
        if (index >= size || index == 0) return sentinel;
        if (index == 1) return sentinel.next;
        IntNode nextNode = sentinel.next;
        for (int i = 2; i <= index; i++) {
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
}
