package ourComparableExecise;

public class DogLauncher {
    public static void main(String[] args) {
        Dog[] dogs = {
                new Dog("Elyse", 3),
                new Dog("Sture", 9),
                new Dog("Arte", 15)
        };
        Dog maxDog = (Dog) MaximizerObject.max(dogs);  // dogs are OurComparable[]
        // if using MaximizerDog class:
        // Dog maxDog = MaximizerDog.max(dogs);
        maxDog.bark();
    }
}
