package gh2;

import com.sun.xml.internal.xsom.impl.scd.Iterators;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

import javax.swing.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GuitarHero {
    private static final double[] CONCERTs = new double[37];
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {

        for(int i = 0; i < 37; i++) {
            CONCERTs[i] = 440 * Math.pow(2.0, (i - 24.0) / 12.0);
        }

        /* create a guitar string array, for concerts */
        GuitarString[] strings = new GuitarString[37];
        for (int i = 0; i < 37; i++) {
            strings[i] = new GuitarString(CONCERTs[i]);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);
                if (index != -1) {
                    strings[index].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = Arrays.stream(strings)
                    .map(GuitarString::sample)
                    .reduce(0.0, Double::sum);
            System.out.println(strings[0].sample());
            System.out.println(strings[1].sample());
            System.out.println(sample);

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < 37; i++) {
                strings[i].tic();
            }
        }
    }

}
