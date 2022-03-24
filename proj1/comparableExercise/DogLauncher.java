package comparableExercise;

public class DogLauncher {
    public static void main(String[] args) {
        Dog[] dogs = {
                new Dog("Elyse", 3),
                new Dog("Sture", 9),
                new Dog("Arte", 15)
        };
        /* max(dogs) return Comparable Type */
        Dog maxDog = (Dog) Maximizer.max(dogs);  // dogs are Dogs[]
        maxDog.bark();
    }
}
