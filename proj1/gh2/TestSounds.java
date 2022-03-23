package gh2;

import edu.princeton.cs.introcs.StdAudio;
import org.junit.Test;

public class TestSounds {
  @Test
  public void TestHarp() {
    Harp harpString = new Harp(GuitarHeroLite.CONCERT_A);
    harpString.pluck();
    for (int i = 0; i < 50000; i += 1) {
      StdAudio.play(harpString.sample());
      harpString.tic();
    }
  }

  @Test
  public void TestDrum() {
    Drum drum = new Drum(GuitarHeroLite.CONCERT_C);
    drum.pluck();
    for (int i = 0; i < 50000; i += 1) {
      StdAudio.play(drum.sample());
      drum.tic();
    }
  }
}
