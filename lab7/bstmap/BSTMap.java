package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V> {

    private TreeNode tree;
    int size;

    BSTMap() {
        this.tree = null;
        this.size = 0;
    }

    // prints out your BSTMap in order of increasing Key
    public void printInOrder(){
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        size = 0;
        tree = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (this.tree != null) {
            TreeNode currTreeNode = this.tree;
            while (currTreeNode != null) {
                if (currTreeNode.key.compareTo(key) == 0) {
                    return true;
                } else if (currTreeNode.key.compareTo(key) > 0) {
                    currTreeNode = currTreeNode.left;
                } else {
                    currTreeNode = currTreeNode.right;
                }
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (this.tree != null) {
            TreeNode currTreeNode = this.tree;
            while (currTreeNode != null) {
                if (currTreeNode.key.compareTo(key) == 0) {
                    return currTreeNode.value;
                } else if (currTreeNode.key.compareTo(key) > 0) {
                    currTreeNode = currTreeNode.left;
                } else {
                    currTreeNode = currTreeNode.right;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        // make a new TreeNode
        TreeNode newNode = new TreeNode(key, value,null,null);
        // if this tree is empty without root node
        if (this.tree == null) {
            this.tree = newNode;
        } else {
            TreeNode currTreeNode = this.tree;
            /* loop */
            while (true) {
                if (currTreeNode.key.compareTo(key) > 0) {
                    if (currTreeNode.left != null) {
                        if (!currTreeNode.left.key.equals(key)) {
                            currTreeNode = currTreeNode.left;
                        } else {
                            currTreeNode.left.value = value;
                            break;
                        }
                    } else {
                        currTreeNode.left = newNode;
                        break;
                    }
                } else if (currTreeNode.key.compareTo(key) < 0) {
                    if (currTreeNode.right != null) {
                        if (!currTreeNode.right.key.equals(key)) {
                            currTreeNode = currTreeNode.right;
                        } else {
                            currTreeNode.right.value = value;
                            break;
                        }
                    } else {
                        currTreeNode.right = newNode;
                        break;
                    }
                } else {
                    currTreeNode = newNode;
                }
            }
        }
        size += 1;
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    // nested class for tree node, which is the basic element for BSTMap
    private class TreeNode {
        K key;
        V value;
        TreeNode left;
        TreeNode right;
        public TreeNode (K k, V v, TreeNode l, TreeNode r){
            key = k;
            value = v;
            left = l;
            right = r;
        }

    }
}
