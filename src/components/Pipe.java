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
  protected static final int GAP = 115;                             //--> Distancia en 'y' entre cada tubería
  protected static final int PIPE_WIDTH = 138 / 2;                  //--> Ancho de cada tubería
  protected static final int PIPE_HEIGHT = 793 / 2;                 //--> Altura de cada tubería

  // atributos
  private int posx;                             //--> Posición 'x' actuál de cada tubería
  private int posy;                             //--> Posición 'y' actuál de cada tubería

  private int xVelocity;                        //--> Velocidad 'x' de cada tubería

  private BufferedImage topPipe;                //--> Imágen de la tubería de arriba
  private BufferedImage bottomPipe;             //--> Imágen de la tubería de abajo

  private Rectangle topPipeBounds;
  private Rectangle bottomPipeBounds;
  private Rectangle scoreBounds;

  private Bird bird;                            //--> Instancia del pájaro

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
    // si el pájaro murió, la velocidad en 'x' es 0
    if (bird.getState() == Bird.BIRD_DEAD)
      xVelocity = 0;
    posx -= xVelocity;
  }

  // dibuja la tubería
  public void draw(Graphics g) {
    g.drawImage(topPipe, posx, posy - PIPE_HEIGHT, PIPE_WIDTH, PIPE_HEIGHT, null);
    g.drawImage(bottomPipe, posx, posy + GAP, PIPE_WIDTH, PIPE_HEIGHT, null);
    g.drawRect((int)scoreBounds.x, (int)scoreBounds.y, (int)scoreBounds.getWidth(), (int)scoreBounds.getHeight());
  }

  // obtiene la posición x actual de la tubería
  public int getX () {
    return posx;
  }

  public int getY (){
    return posy;
  }

  public Rectangle getTopBounds () {
    topPipeBounds = new Rectangle(posx, posy - PIPE_HEIGHT, PIPE_WIDTH, PIPE_HEIGHT);
    return topPipeBounds;
  }

  public Rectangle getBottomBounds () {
    bottomPipeBounds = new Rectangle(posx, posy + GAP, PIPE_WIDTH, PIPE_HEIGHT);
    return bottomPipeBounds;
  }

  public Rectangle getScoreBounds () {
    int newPosy = (int)(topPipeBounds.getY() + topPipeBounds.getHeight());
    int height = (int)(bottomPipeBounds.getY() - (topPipeBounds.getY() + topPipeBounds.getHeight()));
    scoreBounds = new Rectangle(posx, newPosy, PIPE_WIDTH, height);
    return scoreBounds;
  }

  // obtiene el ancho de la tubería
  public int getWidth() {
    return PIPE_WIDTH;
  }
}
