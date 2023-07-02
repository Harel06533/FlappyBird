package components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import util.BaseUtil;

import static util.Constant.PIPE_TOP_IMG_PATH;
import static util.Constant.PIPE_BOTTOM_IMG_PATH;
import static util.Constant.GAME_VELOCITY;

/** Clase individual de una tubería */
public class Pipe {
  // constantes
  protected static final int GAP = 115;
  protected static final int PIPE_WIDTH = 138 / 2;
  protected static final int PIPE_HEIGHT = 793 / 2;

  // atributos
  private int posx;
  private int posy;

  private int xVelocity;

  private BufferedImage topPipe;
  private BufferedImage bottomPipe;

  private Bird bird;

  // constructor
  public Pipe(int posx, int posy) {
    this.posx = posx;
    this.posy = posy;
    xVelocity = GAME_VELOCITY;
    bird = Bird.getBird();
    topPipe = BaseUtil.loadBufferedImage(PIPE_TOP_IMG_PATH);
    bottomPipe = BaseUtil.loadBufferedImage(PIPE_BOTTOM_IMG_PATH);
  }

  // actualiza la posición de la tubería
  public void update() {
    if (bird.getState() == Bird.BIRD_DEAD)
      xVelocity = 0;
    posx -= xVelocity;
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

  public int getY(){
    return posy;
  }

/**
   * Los límites de colisión se definen como un rectángulo que rodea completamente la parte superior de la tubería.
   * 
   * @return El rectángulo de los límites de colisión de la parte superior de la tubería.
   */
  public Rectangle getTopBounds() {
    return new Rectangle(posx, posy - PIPE_HEIGHT, PIPE_WIDTH, PIPE_HEIGHT);
  }

  /**
   * Los límites de colisión se definen como un rectángulo que rodea completamente la parte inferior de la tubería.
   * 
   * @return El rectángulo de los límites de colisión de la parte inferior de la tubería.
   */
  public Rectangle getBottomBounds() {
    return new Rectangle(posx, posy + GAP, PIPE_WIDTH, PIPE_HEIGHT);
  }

  // obtiene el ancho de la tubería
  public int getWidth() {
    return PIPE_WIDTH;
  }
}
