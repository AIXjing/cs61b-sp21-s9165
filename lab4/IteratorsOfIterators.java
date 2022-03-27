/**
 * Problem description: Implement an IteratorOfIterators which will accept as an argument a List of Iterator objects containing Integers.
 * The first call to next() should return the first item from the first iterator in the list.
 * The second call to next() should return the first item from the second iterator in the list.
 * If the list contained n iterators, the n+1th time that we can next(), we would return the second item of the first iterator in the list.
 * NOTE: if an iterator is empty in this process, we continue to the next iterator.
 * Then, once all the iterators are empty, hasNext should return false.
 * For example, if we had 3 Iterators A, B, and C such that A contains the values [1,3,4,5], B was empty, and C contains the values[2],
 * calls to next() for our IteratorOfIterators would return [1,2,3,4,5]
 * */


import java.util.*;

public class IteratorsOfIterators implements Iterator<Integer> {
    public LinkedList<Iterator<Integer>> iteratorList;

    public IteratorsOfIterators (List<Iterator<Integer>> a) {
        this.iteratorList = new LinkedList<>();
        for (Iterator<Integer> iterator : a) {
            if (iterator.hasNext()) {
                iteratorList.addLast(iterator);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !iteratorList.isEmpty();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        // step 1: extract the next element from the next iterator
        Iterator<Integer> nextIterator = iteratorList.removeFirst();
        int ans = nextIterator.next();
        // step 2: prepare for the next time Iterator;
        if (nextIterator.hasNext()) {
            iteratorList.addLast(nextIterator);
        }
        // step 3: return the next element
        return ans;
    }

    public static void main (String args[]) {git
        Integer[] listA = {1,3,4,5};
        Integer[] listB = {};
        Integer[] listC = {2};
        List<Iterator<Integer>> listOfArray = new ArrayList<>();
        listOfArray.add(Arrays.stream(listA).iterator());
        listOfArray.add(Arrays.stream(listB).iterator());
        listOfArray.add(Arrays.stream(listC).iterator());
        IteratorsOfIterators iteratorsOfIterators = new IteratorsOfIterators(listOfArray);
        for (int i = 0; i < 5; i++) {
            System.out.println(iteratorsOfIterators.next() + " ");
        }
    }
}


