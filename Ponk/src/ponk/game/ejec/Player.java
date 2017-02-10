package ponk.game.ejec;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author pdgomezl
 */
public class Player {

    //private static final int MICRO_SEGUNDO_SEGUNDO = 1000000;
    private static final String SOUNDS_PACK = "resources/sounds/";
    private static final String FORMAT = ".wav";

    public static void playSound(String nombre) throws InterruptedException {
        try {
            URL proof = ClassLoader.getSystemResource(SOUNDS_PACK + nombre + FORMAT);
            InputStream in = proof.openStream();
            Clip clip = AudioSystem.getClip();
            //clip.setFramePosition(0);
            clip.open(AudioSystem.getAudioInputStream(in));
            if (!nombre.equals("popup")) {
                clip.loop(clip.LOOP_CONTINUOUSLY);
            }
            clip.start();
            // Thread.sleep((int) clip.getMicrosecondLength() / MICRO_SEGUNDO_SEGUNDO);
            /*System.out.println("segundos : " + tim((int) clip.getMicrosecondLength() / MICRO_SEGUNDO_SEGUNDO));
            int segu = (int) clip.getMicrosecondLength() / MICRO_SEGUNDO_SEGUNDO;
            do {
                System.out.println(tim((segu--)));
                Thread.sleep(1000);
            } while (clip.isRunning());
             */
 /*clip.stop();
            System.out.println(clip.isRunning());
            System.out.println("segundos : " + (int) clip.getMicrosecondLength() / MICRO_SEGUNDO_SEGUNDO);
             */
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
