package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import util.BaseUtil;

import static util.Constant.WIDTH;
import static util.Constant.PIPE_TOP_IMG_PATH;
import static util.Constant.PIPE_BOTTOM_IMG_PATH;

/** Clase individual de una tubería */
public class Pipe {
  // constantes
  protected static final int GAP = 100;
  protected static final int PIPE_WIDTH = 65;
  protected static final int PIPE_HEIGHT = 300;
  private static final int X_VELOCITY = 3;

  // atributos
  private int posx;
  private int posy;

  private BufferedImage topPipe;
  private BufferedImage bottomPipe;

  // constructor
  public Pipe(int initialX, int initialY) {
    posx = initialX;
    posy = initialY;
    topPipe = BaseUtil.loadBufferedImage(PIPE_TOP_IMG_PATH);
    bottomPipe = BaseUtil.loadBufferedImage(PIPE_BOTTOM_IMG_PATH);
  }

  // actualiza la posición de la tubería
  public void update() {
    posx -= X_VELOCITY;
  }

  // dibuja la tubería
  public void draw(Graphics g) {
    g.drawImage(topPipe, posx, posy - PIPE_HEIGHT, PIPE_WIDTH, PIPE_HEIGHT, null);
    g.drawImage(bottomPipe, posx, posy + GAP, PIPE_WIDTH, PIPE_HEIGHT, null);
  }

  // obtiene la posición x actual de la tubería
  public int getX() {
    return posx;
  }

  // obtiene el ancho de la tubería
  public int getWidth() {
    return PIPE_WIDTH;
  }
}
