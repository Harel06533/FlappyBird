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

  private Rectangle topBounds;                  //--> Hitbox de la tubería de arriba
  private Rectangle bottomBounds;               //--> Hitbox de la tubería de abajo
  private Rectangle birdBounds;                 //--> Hitbox del pájaro

  private Bird bird;                            //--> Instancia del pájaro

  // constructor
  public Pipe(int posx, int posy) {
    this.posx = posx;
    this.posy = posy;

    xVelocity = GAME_VELOCITY;

    bird = Bird.getBird();

    birdBounds = bird.getBirdBounds();
    topBounds = new Rectangle();
    bottomBounds = new Rectangle();

    topPipe = BaseUtil.loadBufferedImage(PIPE_TOP_IMG_PATH);
    bottomPipe = BaseUtil.loadBufferedImage(PIPE_BOTTOM_IMG_PATH);
  }

  // actualiza la posición de la tubería
  public void update() {
    // actualiza la información de los bounds / hitbox de cada tubería
    topBounds.setBounds(posx, posy - PIPE_HEIGHT, PIPE_WIDTH, PIPE_HEIGHT);
    bottomBounds.setBounds(posx, posy + GAP, PIPE_WIDTH, PIPE_HEIGHT);;

    // si detecta que el pájaro cruzo los bounds, lo setea como muerto
    if (birdBounds.intersects(topBounds) || birdBounds.intersects(bottomBounds))
      bird.setState(Bird.BIRD_DEAD);

    // si el pájaro murió, la velocidad en 'x' es 0
    if (bird.getState() == Bird.BIRD_DEAD)
      xVelocity = 0;
    posx -= xVelocity;
  }

  // dibuja la tubería
  public void draw(Graphics g) {
    g.drawImage(topPipe, posx, posy - PIPE_HEIGHT, PIPE_WIDTH, PIPE_HEIGHT, null);
    g.drawImage(bottomPipe, posx, posy + GAP, PIPE_WIDTH, PIPE_HEIGHT, null);

    // dibuja los hitbox de momento para debugear
    g.drawRect((int)topBounds.x, (int)topBounds.y, PIPE_WIDTH, PIPE_HEIGHT+1);
    g.drawRect((int)bottomBounds.x, (int)bottomBounds.y, PIPE_WIDTH+2, PIPE_HEIGHT);
  }

  // obtiene la posición x actual de la tubería
  public int getX() {
    return posx;
  }

  public int getY(){
    return posy;
  }
  // obtiene el ancho de la tubería
  public int getWidth() {
    return PIPE_WIDTH;
  }
}
