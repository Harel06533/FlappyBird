package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import util.BaseUtil;

import static util.Constant.WIDTH;
import static util.Constant.HEIGHT;

import static util.Constant.PIPE_TOP_IMG_PATH;
import static util.Constant.PIPE_BOTTOM_IMG_PATH;

/** Clase individual de una tubería */
public class Pipe {
  //constantes
  private static final int GAP = 32;
  private static final int PIPE_WIDTH = 69;
  private static final int PIPE_HEIGHT = 240;
  private static final int X_VELOCITY = 3;

  // atributos
  private int posx;
  private int posy;

  private BufferedImage topPipe;
  private BufferedImage bottomPipe;

  // constructor
  public Pipe () {
    posy = 100;
    posx = WIDTH - 100;
    topPipe = BaseUtil.loadBufferedImage(PIPE_TOP_IMG_PATH);
  }

  // calcula la posición en y para la instancia

  // dibuja 
  public void draw (Graphics g) {
    g.drawImage(topPipe, posx, posy, PIPE_WIDTH, PIPE_HEIGHT, null);
  }
}
