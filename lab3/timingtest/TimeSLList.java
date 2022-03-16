package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int M = 1000;
        AList<Integer> Ns = new AList<>();
        Ns.addLast(1000);
        Ns.addLast(2000);
        Ns.addLast(4000);
        Ns.addLast(8000);
        Ns.addLast(16000);
        Ns.addLast(32000);
        Ns.addLast(64000);
        Ns.addLast(128000);
//        Ns.addLast(256000);
//        Ns.addLast(1000000);
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        for (int i = 0; i < Ns.size(); i++) {
            SLList<Object> testSList = new SLList<>();
            int N = Ns.get(i);
            int k = 0;
            while (k < N) {
                testSList.addLast(1);
                k += 1;
            }
            Stopwatch sw = new Stopwatch(); // timer starts
            for (int m = 0; m < M; m++) {
                testSList.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
            //TODO: opCount is not correct.
            opCounts.addLast(M);

        }
        printTimingTable(Ns,times,opCounts);
    }

}
