package gitlet;

public class TreeNodeTest<Integer> {
    private TreeNode<Integer> treeNode;
    // for test
    public int size;

    public TreeNodeTest() {
        this.treeNode = new TreeNode<>(null, null, null);
        size = 0;
    }

    // add commit on one branch
    public void addLast(Integer n) {
        TreeNode<Integer> parentTreeNode = this.treeNode;
        TreeNode<Integer> updatedTreeNode = new TreeNode<Integer>(n, parentTreeNode, null);
        this.treeNode = updatedTreeNode;
        size += 1;
    }


    // get last commitNode
    public Integer getLastTree() {
        if (treeNode == null) {
            return null;
        } else {
            return treeNode.integer;
        }
    }


    // nested class
    private static class TreeNode<Integer> {
        private final Integer integer;
        private final TreeNode<Integer> mainNode;
        private final TreeNode<Integer> stepNode;

        public TreeNode(Integer integer, TreeNode<Integer> mainNode, TreeNode<Integer> stepNode) {
            this.integer = integer;
            this.mainNode = mainNode;
            this.stepNode = stepNode;
        }
    }
}



