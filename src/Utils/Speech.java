package Utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Speech {
    static volatile boolean stop = false;

    public void Play() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
//        AudioFormat audioFormat =
//                new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 16000F, 16, 2, 4,
//                        16000F, true);
        File file = new File("/Users/talnex/Downloads/16k.wav");
        AudioFormat audioFormat = AudioSystem.getAudioFileFormat(file).getFormat();
        System.out.println(audioFormat.getEncoding());

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
        TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
        targetDataLine.open(audioFormat);

        final SourceDataLine sourceDataLine;
        info = new DataLine.Info(SourceDataLine.class, audioFormat);
        sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
        sourceDataLine.open(audioFormat);

        targetDataLine.start();
        sourceDataLine.start();

        AudioInputStream audioInputStream = new AudioInputStream(targetDataLine);

        FloatControl fc = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
        double value = 2;
        float dB = (float)
                (Math.log(value == 0.0 ? 0.0001 : value) / Math.log(10.0) * 20.0);
        fc.setValue(dB);

        int a = 200;
        int nByte = 0;
        final int bufSize = 4 * 100;
        byte[] buffer = new byte[bufSize];
        while ((nByte = targetDataLine.read(buffer, 0, bufSize))!=-1) {
            System.out.print(String.valueOf(nByte));
//            sourceDataLine.write(buffer, 0, nByte);
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE,file);
        }
        audioInputStream.close();
        sourceDataLine.stop();

    }
}

