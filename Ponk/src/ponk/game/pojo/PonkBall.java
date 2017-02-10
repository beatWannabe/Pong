package ponk.game.pojo;

import ponk.game.app.PlayGame;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import ponk.game.app.user.score.ScoreStatus;
import ponk.game.ejec.Player;

/**
 *
 * @author pdgomezl
 */
public class PonkBall {

    private static final int DIAMETER = 25;
    private static final long AUMENTO_PUNTAJE = 100;
    private int ejeX = 1;
    private int ejeY = 2;
    private int aumentoEjeX = 1;
    private int aumentoEjeY = 1;
    private final PlayGame game;

    public PonkBall(PlayGame game) {
        this.game = game;
    }

    public void move() throws InterruptedException {
        if (ejeX + aumentoEjeX < 0) {
            aumentoEjeX = 1;
        }
        if (ejeX + aumentoEjeX > game.getWidth() - DIAMETER) {
            aumentoEjeX = -1;
        }
        if (ejeY + aumentoEjeY < 0) {
            aumentoEjeY = 1;
        }
        if (ejeY + aumentoEjeY > game.getHeight() - DIAMETER) {
            /*ejeX = 0;
            ejeY = 0;*/
            game.gameOver();
        }
        if (collision()) {
            Player.playSound("popup");
            //----------->
            ScoreStatus.setPlayerActualScore(AUMENTO_PUNTAJE);
            //Thread.sleep(15);
            aumentoEjeY = -1;
            ejeY = game.bar.getTopY() - DIAMETER;
        }
        ejeX += aumentoEjeX;
        ejeY += aumentoEjeY;
    }

    private boolean collision() {
        return game.bar.getBounds().intersects(getBounds());
    }

    public void paint(Graphics2D g) {
        g.fillOval(ejeX, ejeY, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(ejeX, ejeY, DIAMETER, DIAMETER);
    }

    public int getEjeX() {
        return ejeX;
    }

    public void setEjeX(int ejeX) {
        this.ejeX = ejeX;
    }

    public int getEjeY() {
        return ejeY;
    }

    public void setEjeY(int ejeY) {
        this.ejeY = ejeY;
    }

    public int getAumentoEjeX() {
        return aumentoEjeX;
    }

    public void setAumentoEjeX(int aumentoEjeX) {
        this.aumentoEjeX = aumentoEjeX;
    }

    public int getAumentoEjeY() {
        return aumentoEjeY;
    }

    public void setAumentoEjeY(int aumentoEjeY) {
        this.aumentoEjeY = aumentoEjeY;
    }

}
