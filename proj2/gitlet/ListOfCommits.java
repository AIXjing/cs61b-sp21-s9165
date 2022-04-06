package gitlet;


import java.io.Serializable;


/* A linked BinaryTree structure to organize all commits
* If only one branch, then only one node will be  used, and the other will be set to null */

public class ListOfCommits implements Serializable {
    private CommitNode<Commit> commitNode;
    // for text
    public int size;

    // construct a null commitNode and store it in a file
    public ListOfCommits() {
        this.commitNode = new CommitNode<>(null, null, null);
        size = 0;
        // write this instance to a file
//        writeCommitToFile();
    }

    // add commit on one branch
    public void addLast (Commit commit) {
        CommitNode<Commit> parentCommitNode = this.commitNode;
        if (parentCommitNode != null) {
            parentCommitNode.isHEAD = false;
        }
        this.commitNode = new CommitNode<>(commit, parentCommitNode, null);
        size += 1;
    }

    // add commit on a new branch
    // indicated by add parent commitNode on stepNode
    public void addLastOnNewBranch (Commit commit) {
        CommitNode<Commit> newCommitNode
                = new CommitNode<>(commit, null, this.commitNode);
    }

    // get last commitNode given it is on main branch
    public CommitNode<Commit> getLast() {
        if (commitNode == null) {
            return null;
        } else {
            return commitNode;
        }
    }

    // get last commit
    public Commit getLastCommit() {
        if (commitNode == null) {
            return null;
        } else {
            return getLast().item;
        }
    }



    /* nested class: basic node structure */
    public static class CommitNode<T> implements Serializable{
        private final T item;
        private boolean isHEAD;
        private CommitNode<T> mainNode;
        private CommitNode<T> stepNode;


        public CommitNode<T> getMainNode() {
            return mainNode;
        }

        public CommitNode<T> getStepNode() {
            return stepNode;
        }


        public boolean isHEAD() {
            return isHEAD;
        }

        private CommitNode(T i, CommitNode<T> mainNode, CommitNode<T> stepNode) {
            this.item = i;
            this.isHEAD = true; // a new commit will always have the HEAD.
            this.mainNode = mainNode;
            this.stepNode = stepNode;
        }

        public T getItem() {
            return item;
        }

        public void setHEADTo(boolean b) {
            this.isHEAD = b;
        }
    }
}

