package ComparableExercise;

public class MaximizerDog {

    /* This method works only for Dog class */
    public static Dog max(Dog[] dogs) {
        int maxDogIndex = 0;
        for (int i = 1; i < dogs.length; i++) {
            if (dogs[i].compareTo(dogs[maxDogIndex]) > 0) {
                maxDogIndex = i;
            }
        }
       return dogs[maxDogIndex];
    }

    public static void main(String[] args) {
        Dog[] dogs = {
                new Dog("Elyse", 3),
                new Dog("Sture", 9),
                new Dog("Arte", 15)
        };
        Dog maxDog = max(dogs);
        maxDog.bark();
    }
}
