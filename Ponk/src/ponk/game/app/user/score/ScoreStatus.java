package ponk.game.app.user.score;

import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author pdgomezl
 */
public class ScoreStatus extends JTextArea {

    private static long playerActualScore = 0;

    public static long getPlayerActualScore() {
        return playerActualScore;
    }

    public static void setPlayerActualScore(long aPlayerActualScore) {
        if (aPlayerActualScore == 0) {
            playerActualScore = 0;
        } else {
            playerActualScore += aPlayerActualScore;
        }
    }

}
