package components;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Bird {
    private static Bird instance = null;
    private static ImageIcon bird0;
    private String birdpath = "assets/img/bird0.png";

    private static int birdPosX;
    private static int birdPosY;

    private Bird() {
        bird0 = new ImageIcon(birdpath);
        birdPosX = 0;
        birdPosY = 0;
    }

    public void setBirdPosX(int birdPosX) {
        Bird.birdPosX = birdPosX;
    }

    public void setBirdPosY(int birdPosY) {
        Bird.birdPosY = birdPosY;
    }

    public void drawBird(Graphics g) {
        g.drawImage(bird0.getImage(), birdPosX, birdPosY, null);
    }

    public static Bird getBird() {
        if (instance == null) {
            instance = new Bird();
        }
        return instance;
    }
}
