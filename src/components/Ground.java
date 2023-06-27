package components;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

import  util.BaseUtil;

import static util.Constant.GROUND_IMG_PATH;
import static util.Constant.WIDTH;

/** Clase del piso, crea dos sprites y los mueve para irlos intercalando 
 * @author Jorge Francisco Arriaga Escamilla
*/
public class Ground {
  // atributos
  private Bird bird;
  private BufferedImage GroundImage;

  private int x1, x2;
  private int posy;

  private int velX;

  // Constructor
  public Ground() {
    bird = Bird.getBird();
    x1 = 0;
    x2 = WIDTH;
    velX = 3;
    posy = 600;

    GroundImage = BaseUtil.loadBufferedImage(GROUND_IMG_PATH);
  }

  // movimiento
  public void movement() {
    

    // moverá el suelo únicamente si el pájaro está en estado normal o brincando
    if (bird.getState() == Bird.BIRD_JUMPING || bird.getState() == Bird.BIRD_IDLE) {
      x1 -= velX;
      x2 -= velX;

      if(x1 + WIDTH < 0)
        x1 = WIDTH;
      if(x2 + WIDTH < 0)
        x2 = WIDTH;
    }
  }

  // Método para dibujar el fondo
  public void draw(Graphics g) {
    movement();
    g.drawImage(GroundImage, x1, posy, null);
    g.drawImage(GroundImage, x2, posy, null);
  }

  // retorna la posición en y
  public int getPosY () { return posy; }
}