package gitlet;

public class LinkedList<T> {
    private final IntNode root;

    public LinkedList() {
        this.root = new IntNode(null, null);
    }

    // method to add a new commit
    public void addCommit (T item) {
        IntNode newNode = new IntNode(item, root);
    }


    /* Inner class: A linked list node */
    private class IntNode {
        private final T item;
        private IntNode parent;

        private IntNode(T i, IntNode parent) {
            this.item = i;
            this.parent = parent;
        }
    }

}
