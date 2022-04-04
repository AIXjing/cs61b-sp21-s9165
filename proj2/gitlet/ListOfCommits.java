package gitlet;


import java.io.Serializable;

import static gitlet.Repository.*;
import static gitlet.Utils.writeObject;


/* A linked BinaryTree structure to organize all commits
* If only one branch, then only one node will be  used, and the other will be set to null */

public class ListOfCommits<T> implements Serializable {  // A generic type is used here.
    private CommitNode<T> commitNode;
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
    public void addLast (T commit) {
        CommitNode<T> parentCommitNode = this.commitNode;
        CommitNode<T> updatedCommitNode = new CommitNode<>(commit, parentCommitNode, null);
        this.commitNode = updatedCommitNode;
        size += 1;
        writeObject(commits, this);
    }

    // add commit on a new branch
    // indicated by add parent commitNode on stepNode
    public void addLastOnNewBranch (T commit) {
        CommitNode<T> newCommitNode = new CommitNode<>(commit, null, this.commitNode);
    }

    // get last commitNode
    public T getLastCommit() {
        if (commitNode == null) {
            return null;
        } else {
            return commitNode.item;
        }
    }



    // nested class
    private static class CommitNode<T> implements Serializable{
        private final T item;
        private final CommitNode<T> mainNode;
        private final CommitNode<T> stepNode;

        private CommitNode(T i, CommitNode<T> mainNode, CommitNode<T> stepNode) {
            this.item = i;
            this.mainNode = mainNode;
            this.stepNode = stepNode;
        }
    }
}

