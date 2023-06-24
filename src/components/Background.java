package components;

import java.awt.Graphics;
import javax.swing.ImageIcon;


//import java.awt.image.BufferedImage;

public class Background {
  
  private ImageIcon backgroundImage;

  // Constructor
  public Background(String backgroundImagePath) {
    backgroundImage = new ImageIcon(backgroundImagePath);
  }

  // Método para dibujar el fondo
  public void drawBackground(Graphics g) {
    g.drawImage(backgroundImage.getImage(), 0, 0, null);
  }
}
