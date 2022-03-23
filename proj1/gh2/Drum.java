package gh2;

import deque.Deque;
import deque.LinkedListDeque;

public class Drum {
    private static final int SR = 44100; // Sampling Rate
    private static final double DECAY = 1.000; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public Drum(double frequency) {
        this.buffer = new LinkedListDeque<>();
        int capacity = (int) Math.round(SR / frequency * 2);
        // initialize ListDeque with 0.0
        for (int i = 0; i < capacity; i++) {
            buffer.addFirst(0.0);
        }
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        Deque<Double> tmpDeque = new LinkedListDeque<>();
        int size = this.buffer.size();
        for (int i = 0; i < size; i++) {
            double r = Math.random() - 0.5;
            tmpDeque.addLast(r);
        }
        this.buffer = tmpDeque;
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // create a new sample and flip the sign before add to the last with a prob 0.5
        double rand = Math.random();
        double flip = 0.0;
        if (rand < 0.5) flip = 1.0;
        else flip = -1.0;
        Double newSample = flip * (buffer.get(1) + buffer.get(2)) / 2.0 * DECAY;
        buffer.removeFirst();
        buffer.addLast(newSample);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(1);
    }
}
