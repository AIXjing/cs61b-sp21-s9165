package bstmap;

import static org.junit.Assert.*;
import org.junit.Test;

public class BSTMapTest {

    @Test
    public void BSTPutTest() {
        try {
            BSTMap<Integer, Integer> tree1 = new BSTMap<>();
            tree1.put(6,6);
            tree1.put(3,3);
            tree1.put(9,9);
            tree1.put(2,2);
            tree1.put(3,4);
            System.out.println("the value of key 3 is " + tree1.get(3));
            System.out.println("the value of key 6 is " + tree1.get(6));
            System.out.println("the value of key 10 is " + tree1.get(10));
        } catch (Exception e) {
            fail();
        }
    }

}