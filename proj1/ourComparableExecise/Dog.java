package ourComparableExecise;

public class Dog implements OurComparable {
    private String name;
    private int size;

    public Dog(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void bark() {
        System.out.println(name + " say: bark!");
    }

    public int compareTo(Object o) {
        Dog uddaDog = (Dog) o;
        return this.size - uddaDog.size;
//        if (this.size < uddaDog.size) {
//            return -1;
//        } else if (this.size > uddaDog.size) {
//            return 1;
//        }
//        return 0;
    }
}
