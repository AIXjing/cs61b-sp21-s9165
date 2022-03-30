package gitlet;


public class BinaryTreeNode<T> {
    private final T item;
    private final IntNode child1;
    private IntNode child2 = null;

    public BinaryTreeNode(T item, IntNode parent1, IntNode parent2) {
        this.item = item;
        this.child1 = parent1;
        this.child2 = parent2;
    }

    /* method to get item, child1 and child2 */
    public T getItem() {
        return item;
    }

    public IntNode getChild1() {
        return child1;
    }

    public IntNode getChild2() {
        return child2;
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

