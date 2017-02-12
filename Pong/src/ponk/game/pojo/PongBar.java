package ponk.game.pojo;

import ponk.game.app.PlayGame;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author pdgomezl
 */
public class PongBar {

    private static int nivelAvanceBar = 2;
    private static int ejeY;
    private static final int WITH = 60;
    private static final int HEIGHT = 10;

    public static int getNivelAvanceBar() {
        return nivelAvanceBar;
    }

    public static void setNivelAvanceBar(int aNivelAvanceBar) {
        nivelAvanceBar = aNivelAvanceBar;
    }
    private int ejeX = 205;
    private int aumentoEjeX;
    private final PlayGame game;

    public PongBar(PlayGame game) {
        this.game = game;
    }

    public void move() {
        if (ejeX + aumentoEjeX > 0 && ejeX + aumentoEjeX < game.getWidth() - WITH) {
            ejeX = ejeX + aumentoEjeX * nivelAvanceBar;
        }
    }

    public void paint(Graphics2D g) {
        ejeY = game.getHeight() - 30;
        g.fillRect(ejeX, ejeY, WITH, HEIGHT);
    }

    public void keyReleased(KeyEvent e) {
        aumentoEjeX = 0;
    }

    public void keyPressed(KeyEvent e) {
        //----------CON LAS FLECHAS
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                aumentoEjeX = -1;
                break;
            //---------CON A O D
            case KeyEvent.VK_A:
                aumentoEjeX = -1;
                break;
            case KeyEvent.VK_RIGHT:
                aumentoEjeX = 1;
                break;
            case KeyEvent.VK_D:
                aumentoEjeX = 1;
                break;
            default:
                break;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(ejeX, ejeY, WITH, HEIGHT);
    }

    public int getTopY() {
        return ejeY - HEIGHT;
    }

    public int getEjeX() {
        return ejeX;
    }

    public void setEjeX(int ejeX) {
        this.ejeX = ejeX;
    }

    public int getAumentoEjeX() {
        return aumentoEjeX;
    }

    public void setAumentoEjeX(int aumentoEjeX) {
        this.aumentoEjeX = aumentoEjeX;
    }

    public static int getWith() {
        return WITH;
    }

}
