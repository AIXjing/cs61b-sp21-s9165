package gitlet;



class TreeNodeTestTest {


    public static void main(String[] args) {
        TreeNodeTest<Integer> test = new TreeNodeTest<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);
        test.addLast(4);
        System.out.println(test.size);

    }

}