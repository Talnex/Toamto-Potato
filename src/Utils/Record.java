package Utils;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Record{
    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        Speech speech = new Speech();
        speech.Play();
    }

}

