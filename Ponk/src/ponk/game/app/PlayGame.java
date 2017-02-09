package ponk.game.app;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ponk.game.pojo.PonkBall;
import ponk.game.pojo.PonkBar;

/**
 *
 * @author pdgomezl
 */
public class PlayGame extends JPanel {

    private final PonkBall ball = new PonkBall(this);
    public final PonkBar bar = new PonkBar(this);
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEITH = 400;
    private static final int NIVEL_VELOCIDAD_BALL = 4;

    public PlayGame() {
        setFocusable(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                bar.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                bar.keyPressed(e);
            }
        });
    }

    private void move() throws InterruptedException {
        ball.move();
        bar.move();
    }

    @Override
    public void paint(Graphics grafic) {
        /*URL proof = ClassLoader.getSystemResource("resources/images/random/" + randomImage() + ".jpg");
        ImageIcon im = new ImageIcon(proof);
        grafic.drawImage(im.getImage(), screenDimensionWidth(FRAME_WIDTH), screenDimensionHeith(FRAME_HEITH), null);*/
        super.paint(grafic);
        Graphics2D painter2dEsfe = (Graphics2D) grafic;
        painter2dEsfe.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(painter2dEsfe);
        bar.paint(painter2dEsfe);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame principalFrame = new JFrame("PONK!");
        Dimension dime = new Dimension(FRAME_WIDTH, FRAME_HEITH);
        principalFrame.setLocation(screenDimensionWidth(FRAME_WIDTH), screenDimensionHeith(FRAME_HEITH));
        //--------
        URL proof = ClassLoader.getSystemResource("resources/images/ico.png");
        ImageIcon im = new ImageIcon(proof);
        principalFrame.setIconImage(im.getImage());
        //----->
        /* al mostarr la imagen de fondo se ejecuta el game over
        proof = ClassLoader.getSystemResource("resources/images/random/" + randomImage() + ".jpg");
        principalFrame.setContentPane(new JLabel(new ImageIcon(proof)));
         */
        //----------
        //System.exit(frame.ABORT);
        PlayGame game = new PlayGame();
        principalFrame.add(game);
        principalFrame.setSize(dime);
        //frame.setSize(300, 400);
        principalFrame.setVisible(true);
        principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(NIVEL_VELOCIDAD_BALL);
        }

    }

    public void gameOver() throws InterruptedException {

        if (JOptionPane.showConfirmDialog(this, "Desea Continuar ?", "Game Over", 0) == 0) {
            ball.setEjeX(0);
            ball.setEjeY(0);
            ball.move();
            //----->
            bar.setEjeX(getWidth() / 2 - 15);
        } else {
            System.exit(ABORT);
        }
        //JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);

        //System.exit(ABORT);
    }

    public static int randomImage() {
        Random rnd = new Random();
        int numeroImage = (int) (rnd.nextDouble() * 4 + 1);
        return numeroImage;
    }

    private static int screenDimensionWidth(int frameWidth) {
        int widthTemporalCenter = (int) SCREEN_SIZE.getWidth() / 2 - frameWidth / 2;
        if (widthTemporalCenter < 0) {
            widthTemporalCenter *= -1;
        }
        return widthTemporalCenter;
        //System.out.println("WIMOVDTH : " +screenSize.width + " HEIGTH : " + screenSize.height);
    }

    private static int screenDimensionHeith(int frameHeith) {
        int heithTemporalCenter = (int) SCREEN_SIZE.getHeight() / 2 - frameHeith / 2;
        if (heithTemporalCenter < 0) {
            heithTemporalCenter *= -1;
        }
        return heithTemporalCenter;
        //System.out.println("WIDTH : " +screenSize.width + " HEIGTH : " + screenSize.height);
    }

}
