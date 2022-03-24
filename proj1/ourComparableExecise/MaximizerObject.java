package ourComparableExecise;

public class MaximizerObject {

    /* This method works for all objects */
    public static OurComparable max(OurComparable[] items) {
        int maxDogIndex = 0;
        for (int i = 1; i < items.length; i++) {
            if (items[i].compareTo(items[maxDogIndex]) > 0) {
                maxDogIndex = i;
            }
        }
        return items[maxDogIndex];
    }
}
