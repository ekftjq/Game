package TextGame.GUI;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Moon on 4/22/2016.
 * Plays and stops the music from the GUI
 */
public class Bgm {
    File soundfile;
    Clip clip;

    public Bgm(File file) {
        soundfile = file;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playMusic() {
        try {
            clip.open(AudioSystem.getAudioInputStream(soundfile));
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        }
        clip.start();
            //Thread.sleep(clip.getMicrosecondLength());
    }

    public void stopMusic() {
        clip.stop();
    }
}
