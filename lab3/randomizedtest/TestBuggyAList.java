package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> AListNoResizing = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();

        int[] intList = new int[]{4,5,6};
        for (int i = 0; i < intList.length; i++) {
            AListNoResizing.addLast(intList[i]);
            buggyAList.addLast(intList[i]);
            System.out.println("AListNoResizing[" + i + "]: " + AListNoResizing.get(i));
            System.out.println("buggyAList[" + i + "]: " + buggyAList.get(i));
        }
        System.out.println();

        System.out.println("AListNoResizing: ");
        for (int n = 0; n < AListNoResizing.size(); n++) {
            System.out.print(AListNoResizing.get(n).toString() + ", ");
        }
        System.out.println();
        System.out.println("BuggyList: ");
        for (int n = 0; n < buggyAList.size(); n++) {
            System.out.print(buggyAList.get(n).toString() + ", ");
        }
        System.out.println();

        // remove one by one
        for (int i = 0; i < intList.length; i++) {
            AListNoResizing.removeLast();
            buggyAList.removeLast();
            for (int n = 0; n < AListNoResizing.size(); n++) {
                System.out.print(AListNoResizing.get(n).toString() + ", ");
            }
            System.out.println();
            for (int n = 0; n < buggyAList.size(); n++) {
                System.out.print(buggyAList.get(n).toString() + ", ");
            }
            System.out.println();
        }
    }

    @Test
    public void randomizedTestForAListNoResizing() {
        AListNoResizing<Integer> L = new AListNoResizing<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                if (L.size() > 0) {
                    // removeLast
//                    System.out.println("removeLast: " + L.getLast());
                    L.removeLast();
//                    System.out.println("removeLast");
                }
            }
            else if (operationNumber == 2) {
                // size
                int size = L.size();
//                System.out.println("size: " + size);
            }
        }
    }

    @Test
    public void randomizedTestForBuggyList() {
        BuggyAList<Integer> L = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                if (L.size() > 0) {
                    // removeLast
//                    System.out.println("removeLast: " + L.getLast());
                    L.removeLast();
                }
            }
            else if (operationNumber == 2) {
                // size
                int size = L.size();
//                System.out.println("size: " + size);
            }
        }
    }
}
