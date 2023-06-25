package components;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

import  util.BaseUtil;

import static util.Constant.GROUND_IMG_PATH;
import static util.Constant.WIDTH;

public class Ground {
  
  private BufferedImage GroundImage;
  private int x1, x2;

  private float velX;
  

  // Constructor
  public Ground() {
    x1 = 0;
    x2 = WIDTH;
    velX = 3;

    GroundImage = BaseUtil.loadBufferedImage(GROUND_IMG_PATH);
  }

  public void movement() {

    x1 -= velX;
    x2 -= velX;

    if(x1 + WIDTH < 0)
      x1 =WIDTH;
      
    if(x2 + WIDTH < 0)
      x2 = WIDTH;
}


  // Método para dibujar el fondo
  public void draw(Graphics g) {
    g.drawImage(GroundImage, x1, 600, null);
    g.drawImage(GroundImage, x2, 600, null);
  }
}