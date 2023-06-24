package components;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Bird {
    private static Bird instance = null;
    private static ImageIcon bird0;
    private String birdpath = "assets/img/bird0.png";

    private static int birdPosX;
    private static int birdPosY;

    // Constructor privado
    private Bird() {
        bird0 = new ImageIcon(birdpath);
        birdPosX = 0;
        birdPosY = 0;
    }

    // Establece posición del pajaro en X
    public void setBirdPosX(int birdPosX) {
        Bird.birdPosX = birdPosX;
    }

    // Establece posición del pajaro en Y
    public void setBirdPosY(int birdPosY) {
        Bird.birdPosY = birdPosY;
    }

    //Dibuja el pajaro
    public void drawBird(Graphics g) {
        g.drawImage(bird0.getImage(), birdPosX, birdPosY, null);
    }

    // Obtiene 
    public static Bird getBird() {
        if (instance == null) {
            instance = new Bird();
        }
        return instance;
    }
}
