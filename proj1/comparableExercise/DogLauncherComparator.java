package comparableExercise;

import java.util.Comparator;

public class DogLauncherComparator {
    public static void main(String[] args) {
        Dog[] dogs = {
                new Dog("Elyse", 3),
                new Dog("Sture", 9),
                new Dog("Arte", 15)
        };

        /* compare dogs[0] and dogs[1] by name */
        Comparator<Dog> nameComparator = Dog.getNameComparator();
        int cmpName = nameComparator.compare(dogs[0], dogs[1]);
        if (cmpName < 0) {
            dogs[0].bark();
        } else if (cmpName > 0) {
            dogs[1].bark();
        } else {
            dogs[0].bark();
            dogs[1].bark();
        }

        /* compare dogs[0] and dogs[1] by size */
        Comparator<Dog> sizeComparator = Dog.getSizeComparator();
        int cmpSize = sizeComparator.compare(dogs[0], dogs[1]);
        if (cmpSize < 0) {
            dogs[0].bark();
        } else if (cmpSize > 0) {
            dogs[1].bark();
        } else {
            dogs[0].bark();
            dogs[1].bark();
        }
    }
}
