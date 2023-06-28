package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static util.Constant.WIDTH; 
import static util.Constant.HEIGHT;

import static util.Constant.BIRD_IMGS_PATH;

import util.BaseUtil;

/** Clase de pájaro 
 * @author Harel Alejandro Olguín Gaytán
*/
public class Bird {
  // constantes
  public static final int BIRD_IDLE = 0;
  public static final int BIRD_JUMPING = 1;
  public static final int BIRD_DEAD = 2;

  private static final double MAX_VEL_Y = 12;
  private static final double ACC_Y = 0.6;

  private static final int initialX = (WIDTH / 2) - 25;
  private static final int initialY = (HEIGHT / 2) - 70;

  //atributos
  private static Bird instance = null;

  private int animationTick;
  private int animationIndex;
  private int animationSpeed;

  private int state;

  private int posx;
  private int posy;
  
  private double yVelocity;

  private boolean keyFlag;
  private boolean hitFlag;

  private BufferedImage[] birdImages;

  // constructor privado (inicializa todas las variables necesarias)
  private Bird () {
    posx = initialX;
    posy = initialY;
    keyFlag = false;
    hitFlag = false;
    yVelocity = 0;

    animationSpeed = 8;
    animationTick = 0;
    animationIndex = 0;

    state = BIRD_IDLE;

    birdImages = new BufferedImage[BIRD_IMGS_PATH.length];
    for (int i = 0; i < BIRD_IMGS_PATH.length; i++) {
      birdImages[i] = BaseUtil.loadBufferedImage(BIRD_IMGS_PATH[i]);
    }
  }

  // obtención de la instancia
  public static Bird getBird () {
    if (instance == null) 
      instance = new Bird();
    return instance;
  }

  // suma in índice para iterar en los sprites del pájaro y animarlos en base a los fps
  public void animate () {
    animationTick++;
    if (animationTick >= animationSpeed) {
      animationTick = 0;
      animationIndex++;
      if (animationIndex >= birdImages.length)
        animationIndex = 0;
    }
  }

  // actualiza la información del jugador basado en su estado
  public void update() {
    animate();
    if (state == BIRD_IDLE || state == BIRD_DEAD) {
      if (state == BIRD_DEAD) {
        hitFlag = true;             //--> El pájaro al estar muerto, significa que chocó
        animationIndex = 0;
      }
      yVelocity = 0;
    }

    fall();
  }

  // reinicia a valores default luego de morir
  public void restart () {
    state = BIRD_IDLE;
    posy = initialY;
    posx = initialX;
    hitFlag = false;
  }

  // dibuja el elemento en pantalla
  public void draw (Graphics g) {
    g.drawImage(birdImages[animationIndex], posx, posy, null);
  }

  // la velocidad en y cuando brinca el pájaro
  public void fly () {
    yVelocity = -7;
  }

  // caída libre del pájaro
  public void fall () {
    yVelocity += ACC_Y;
    posy += yVelocity;
    if (yVelocity > MAX_VEL_Y)
      yVelocity = MAX_VEL_Y;
  }

  // setea el estado del pájaro (Normal, Jugando o Muerto)
  public void setState (int state) {
    this.state = state;
  }

  // setea la posición en y del pájaro
  public void setPosY (int posy) {
    this.posy = posy;
  }

  // keyFlag se hace true (Esto es para determinar cuándo se presionó una tecla)
  public void pressKey () {
    keyFlag = true;
  }

  // keyFlag se hace false (Esto determina cuándo se liberó la tecla presionada)
  public void releaseKey () {
    keyFlag = false;
  }

  // getters
  public int getState () { return state; }
  public int getPosY () { return posy; }
  public double getyVelocity () { return yVelocity; }
  public boolean isKeyPressed () { return (keyFlag == true); }
  public boolean isKeyReleased () { return (keyFlag == false); }
  public boolean birdHasHit () { return hitFlag; }

}