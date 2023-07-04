package components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import static util.Constant.WIDTH; 
import static util.Constant.HEIGHT;

import static util.Constant.BIRD_IMGS_PATH;

import static util.Constant.FLY_SOUND_PATH;
import static util.Constant.HIT_SOUND_PATH;
import static util.Constant.SCORE_SOUND_PATH;

import util.SoundUtil;
import util.BaseUtil;

/** Clase de pájaro 
 * @author Harel Alejandro Olguín Gaytán
*/
public class Bird {
  // constantes
  public static final int BIRD_IDLE = 0;                            //--> El pájaro está parado
  public static final int BIRD_JUMPING = 1;                         //--> El pájaro está brincando
  public static final int BIRD_DEAD = 2;                            //--> El pájaro está muerto

  private static final double MAX_VEL_Y = 12;                       //--> La velocidad máxima que puede tener el pájaro
  private static final double ACC_Y = 0.6;                          //--> La aceleración de la velocidad
  private static final double JUMP_FORCE = -7;                      //--> La velocidad hacía arriba al brincar

  private static final int INITIAL_X = (WIDTH >> 1) - 25;            //--> Posición inicial en 'x'
  private static final int INITIAL_Y = (HEIGHT >> 1) - 70;           //--> Posición inicial en 'y'

  //atributos
  private static Bird instance = null;                             //--> La instancia del pájaro

  private int animationTick;                                         //--> La cantidad de ticks antes de la animación
  private int animationSpeed;                                        //--> La velocidad de la animación
  private int animationIndex;                                        //--> el índice de la imágen para animar

  private int state;                                                 //--> El estado del pájaro

  private int posx;                                                  //--> Posición 'x' actual
  private int posy;                                                  //--> Posición 'y' actual
  
  private double yVelocity;                                          //--> Velocidad 'y' actual

  private boolean keyFlag;                                           //--> Determina si se está presionando una tecla
  private boolean hitFlag;                                           //--> Determina si el pájaro ya chocó

  private BufferedImage[] birdImages;                                //--> Imágenes del pájaro para animar

  private Score birdScore;

  private Rectangle birdBounds;

  // constructor privado (inicializa todas las variables necesarias)
  private Bird () {
    posx = INITIAL_X;
    posy = INITIAL_Y;
    keyFlag = false;
    hitFlag = false;
    yVelocity = 0;

    animationSpeed = 8;
    animationTick = 0;
    animationIndex = 0;

    state = BIRD_IDLE;

    birdScore = new Score();
    
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
    // si el pájaro murió, no anima nada
    if (state == BIRD_DEAD) {
      animationIndex = 0;
      return;
    }
    // animación
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

    // si la posición en 'y' está fuera de la pantalla, regresa al pájaro a una posición válida
    if (posy <= 1) 
      posy = -5;

    // si el pájaro está IDLE o DEAD, la velocidad en Y es 0
    if (state == BIRD_IDLE || state == BIRD_DEAD) 
      yVelocity = 0;

    // si el pájaro está muerto, checa si ya chocó, y si aun no, suena el sonido al chocar y determina que ahora sí chocó
    if (state == BIRD_DEAD) {
      if (hitFlag == false) {
        hitSound();
        hitFlag = true;
      }
    }

    fall();
  }

  // reinicia a valores default luego de morir
  public void restart () {
    state = BIRD_IDLE;
    posy = INITIAL_Y;
    posx = INITIAL_X;
    hitFlag = false;
    birdScore.restart();
  }

  // la velocidad en y cuando brinca el pájaro
  public void fly () {
    flySound();
    state = BIRD_JUMPING;
    yVelocity = JUMP_FORCE;
  }

  // añade 1 al score del pájaro
  public void addBirdScore () {
    birdScore.addToScore();
    scoreSound();
  }

  // caída libre del pájaro
  public void fall () {
    if (state == BIRD_DEAD || state == BIRD_IDLE)                //---> El pajaro solo caera cuando su estado sea brincando
      return;
    yVelocity += ACC_Y;                                          //---> Aumentara gradualmente la velocidad del pájaro simulando la gravedad
    posy += yVelocity;                                           //---> El pajaro se desplaza en y y se guarda la nueva posición
    if (yVelocity > MAX_VEL_Y)                                   //---> Si el pajaro rebasa la maxima velocidad que es 12
      yVelocity = MAX_VEL_Y;                                     //---> Si es asi, la vuelve a 12 y evita que el pájaro caiga demasiado rápido
  }


  // sonido al volar
  public void flySound () {
    SoundUtil.playSound(FLY_SOUND_PATH);
  }

  // sonido al hacer hit
  public void hitSound () {
    SoundUtil.playSound(HIT_SOUND_PATH);
  }

  // sonido al hacer score
  public void scoreSound () {
    SoundUtil.playSound(SCORE_SOUND_PATH);
  }

  // dibuja el elemento en pantalla
  public void draw (Graphics g) {
    g.drawImage(birdImages[animationIndex], posx, posy, null);
  }

  // dibuja el score del pájaro
  public void drawScore (Graphics g) {
    birdScore.draw(g);
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
  public Rectangle getBirdBounds () {
    birdBounds = new Rectangle(posx, posy, birdImages[animationIndex].getWidth(), birdImages[animationIndex].getHeight());
    return birdBounds;
  }
  public int getState () { return state; }
  public int getPosY () { return posy; }
  public double getyVelocity () { return yVelocity; }
  public boolean isKeyPressed () { return (keyFlag == true); }
  public boolean isKeyReleased () { return (keyFlag == false); }
}