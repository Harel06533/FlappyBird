package components;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

import  util.BaseUtil;

import static util.Constant.BACKGROUND_IMG_PATH;

public class Background {
  
  private BufferedImage backgroundImage;
  

  // Constructor
  public Background() {
    backgroundImage = BaseUtil.loadBufferedImage(BACKGROUND_IMG_PATH);
  }

  // Método para dibujar el fondo
  public void draw(Graphics g) {
    g.drawImage(backgroundImage, 0, 0, null);
  }
}
