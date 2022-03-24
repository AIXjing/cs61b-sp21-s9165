package comparableExercise;

/*
 For more details about comparator, please refer to this video:
 https://www.youtube.com/watch?v=1oow3NGoExg&list=PL8FaHk7qbOD56r1sGUGifsfC0KRDAsuZ3&index=7
 */

import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    private String name;
    private int size;

    public Dog(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }

    public static Comparator<Dog> getSizeComparator() {
        return new SizeComparator();
    }

    public void bark() {
        System.out.println(name + " say: bark!");
    }

    @Override
    public int compareTo(Dog uddaDog) {
        return this.size - uddaDog.size;
    }

    /* Created a nested comparator class to specify the way to compare names */
    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }

    /* Created a nested comparator class to specify the way to compare names */
    private static class SizeComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            Integer sizeA = a.size;
            Integer sizeB = b.size;
            return sizeA.compareTo(sizeB); // compareTo does not work for primitive data type
        }
    }
}
