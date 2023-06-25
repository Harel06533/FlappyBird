package components;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

import  util.BaseUtil;

import static util.Constant.GROUND_IMG_PATH;

public class Ground {
  
  private BufferedImage GroundImage;
  

  // Constructor
  public Ground() {
    GroundImage = BaseUtil.loadBufferedImage(GROUND_IMG_PATH);
  }

  // Método para dibujar el fondo
  public void draw(Graphics g) {
    g.drawImage(GroundImage, -50, 600, null);
  }
}