package ponk.game.app;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ponk.game.app.user.score.ScoreStatus;
import ponk.game.ejec.Player;
import ponk.game.pojo.PonkBall;
import ponk.game.pojo.PonkBar;

/**
 *
 * @author pdgomezl
 */
public class PlayGame extends JPanel {

    public static ScoreStatus getScoreStatus() {
        return scoreStatus;
    }

    public static void setScoreStatus(ScoreStatus aScoreStatus) {
        scoreStatus = aScoreStatus;
    }

    private final PonkBall ponkBall = new PonkBall(this);
    public final PonkBar bar = new PonkBar(this);
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEITH = 400;
    private static final int VELOCIDAD_TIMER_EFECT = 4;
    private static final String ICO_PATH = "resources/images/ico.png";
    private static int numeroImagen;
    private static ScoreStatus scoreStatus;
    //private static Graphics graphics;

    public PlayGame() throws InterruptedException {
        Player.playSound(String.valueOf(randomValueInt(5)));
        numeroImagen = randomValueInt(3);
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
        ponkBall.move();
        bar.move();
    }

    @Override
    public void paint(Graphics grafic) {
        URL proof = ClassLoader.getSystemResource("resources/images/random/" + numeroImagen + ".jpg");
        ImageIcon im = new ImageIcon(proof);
        super.paint(grafic);
        Graphics2D painter2dEsfe = (Graphics2D) grafic;
        painter2dEsfe.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        grafic.drawImage(im.getImage(), 0, 0, super.getWidth(), super.getHeight(), null);
        ponkBall.paint(painter2dEsfe);
        bar.paint(painter2dEsfe);
        //super.paint(grafic);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame principalFrame = new JFrame("PONK!");
        Dimension dime = new Dimension(FRAME_WIDTH, FRAME_HEITH);
        principalFrame.setLocation(screenDimension((int) SCREEN_SIZE.getWidth(), FRAME_WIDTH), screenDimension((int) SCREEN_SIZE.getHeight(), FRAME_HEITH));
        //--------
        URL proof = ClassLoader.getSystemResource(ICO_PATH);
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
        //-------------------
        scoreStatus = new ScoreStatus();
        //scoreStatus.setBackground(Color.red);
        //scoreStatus.setOpaque(true);
        scoreStatus.setVisible(true);

        javax.swing.GroupLayout scoreStatusLayout = new javax.swing.GroupLayout(scoreStatus);
        scoreStatus.setLayout(scoreStatusLayout);
        scoreStatusLayout.setHorizontalGroup(scoreStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 53, Short.MAX_VALUE));
        scoreStatusLayout.setVerticalGroup(scoreStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 20, Short.MAX_VALUE));

        principalFrame.add(scoreStatus);
        //-----------------

        javax.swing.GroupLayout gameLayout = new javax.swing.GroupLayout(game);
        game.setLayout(gameLayout);
        gameLayout.setHorizontalGroup(gameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gameLayout.createSequentialGroup()
                        .addContainerGap(337, Short.MAX_VALUE)
                        .addComponent(scoreStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
        );
        gameLayout.setVerticalGroup(
                gameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scoreStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(269, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(principalFrame.getContentPane());
        principalFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(game, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(game, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        //--------------------
        principalFrame.add(game);
        principalFrame.setSize(dime);
        //frame.setSize(300, 400);
        principalFrame.setVisible(true);
        principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(VELOCIDAD_TIMER_EFECT);
            //puntaje
            scoreStatus.setText("HI " + String.valueOf(ScoreStatus.getPlayerActualScore()));
            //scoreStatus.update(graphics);
        }

    }

    public void gameOver() throws InterruptedException {

        if (JOptionPane.showConfirmDialog(this, "Desea Continuar ?", "Game Over", 0) == 0) {
            ponkBall.setEjeX(0);
            ponkBall.setEjeY(0);
            PonkBall.setAumentoVelocidad(1);
            ponkBall.move();
            ScoreStatus.setPlayerActualScore(0);
            //----->
            bar.setEjeX(screenDimension(super.getWidth(), PonkBar.getWith()));
        } else {
            System.exit(ABORT);
        }
        //JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);

        //System.exit(ABORT);
    }

    public static int randomValueInt(int queto) {
        Random rnd = new Random();
        int numeroImage = (int) (rnd.nextDouble() * queto + 1);
        return numeroImage;
    }

    private static int screenDimension(int major, int frameWidth) {
        int widthTemporalCenter = (int) major / 2 - frameWidth / 2;
        if (widthTemporalCenter < 0) {
            widthTemporalCenter *= -1;
        }
        return widthTemporalCenter;
        //System.out.println("WIMOVDTH : " +screenSize.width + " HEIGTH : " + screenSize.height);
    }

    /* private static int screenDimensionHeith(int frameHeith) {
        int heithTemporalCenter = (int) SCREEN_SIZE.getHeight() / 2 - frameHeith / 2;
        if (heithTemporalCenter < 0) {
            heithTemporalCenter *= -1;
        }
        return heithTemporalCenter;
        //System.out.println("WIDTH : " +screenSize.width + " HEIGTH : " + screenSize.height);
    }

    private static int internScreenWith(int objectWith, int frame) {
        int withTemp = (int) frame / 2 - objectWith / 2;
        if (withTemp < 0) {
            withTemp *= -1;
        }
        return withTemp;
    }*/

}
