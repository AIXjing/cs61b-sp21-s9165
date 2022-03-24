/*
    For more details, please check out the lecture via the link:
    https://www.youtube.com/watch?v=dbdbcbhe3Jk&list=PL8FaHk7qbOD56r1sGUGifsfC0KRDAsuZ3&index=5
 */

package ourComparableExecise;

public interface OurComparable {

    /* Specification
     * return negative if origin object less than input object
     * return 0 if equal
     * return positive if origin object greater than input object
     * */

    public int compareTo(Object o);
}
