package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import app.GamePanel;
import components.tools.BirdKeyListener;

import static util.Constant.WIDTH; 
import static util.Constant.HEIGHT;
import static util.Constant.BIRD_IMGS_PATH;
import static util.Constant.FALL_IMGS_PATH;
import static util.Constant.FLY_SOUND_PATH;
import static util.Constant.HIT_SOUND_PATH;
import static util.Constant.SCORE_SOUND_PATH;

import util.BaseUtil;

public class Bird {
  // constantes
  public static final int BIRD_IDLE = 0;
  public static final int BIRD_PLAYING = 1;
  public static final int BIRD_DEAD = 2;
  private static final double MAX_VEL_Y = 12;
  private static final double ACC_Y = 0.6;
  private boolean initMove;

  //atributos
  private static Bird instance = null;

  private static final int initialX = WIDTH >> 1;
  private static final int initialY = HEIGHT >> 1;

  private int animationTick;
  private int animationIndex;
  private int animationSpeed;
  private int state;
  private int posx;
  private int posy;
  private double xVelocity;
  private double yVelocity;


  private GamePanel parentPanel;
  private BirdKeyListener mainKeyListener;

  private BufferedImage[] birdImages;
  private BufferedImage currentImage;

  private boolean fallingAnimation; //para la animacion de caida, aun no cae del todo bien el wey
  private int fallAnimationIndex;
  private BufferedImage[] fallImages; // mismo formato que birdimages

  // constructor privado (inicializa todas las variables necesarias)
  private Bird (GamePanel parentPanel, BirdKeyListener mainKeyListener) {
    animationSpeed = 8;
    animationTick = 0;
    animationIndex = 0;
    fallingAnimation = false;
    fallAnimationIndex = 0;
    fallImages = new BufferedImage[FALL_IMGS_PATH.length];
    for (int i = 0; i < fallImages.length; i++) {
      fallImages[i] = BaseUtil.loadBufferedImage(FALL_IMGS_PATH[i]);
    }
    posx = initialX;
    posy = initialY;
    xVelocity = 0;
    yVelocity = 7;
    initMove = false;
    
    birdImages = new BufferedImage[BIRD_IMGS_PATH.length];
    for (int i = 0; i < birdImages.length; i++) {
      birdImages[i] = BaseUtil.loadBufferedImage (BIRD_IMGS_PATH[i]);
    }
    this.parentPanel = parentPanel;
    this.mainKeyListener = mainKeyListener;
  }

  // obtención de la instancia
  public static Bird getBird (GamePanel parentPanel, BirdKeyListener mainkKeyListener) {
    if (instance == null) 
      instance = new Bird(parentPanel, mainkKeyListener);
    return instance;
  }

  // Getters necesarios

  public static String getFlySoundPath() {
  return FLY_SOUND_PATH;
}

  public static String getHitSoundPath() {
    return HIT_SOUND_PATH;
  }

  public static String getScoreSoundPath() {
    return SCORE_SOUND_PATH;
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
  if (!initMove) {
    if (mainKeyListener.jumping) {
      initMove = true;
      yVelocity = -10;  // Velocidad inicial hacia arriba al presionar la tecla de espacio
    }
  } else {
    if (mainKeyListener.jumping) {
      yVelocity = -10;  // Mantener la velocidad hacia arriba al presionar la tecla de espacio
      fallingAnimation = false;
    } else {
      yVelocity += ACC_Y;  // Aplicar gravedad
    }

    posy += yVelocity;
    animate();
  }
}



  // dibuja el elemento en pantalla
  public void draw (Graphics g) {
    if(fallingAnimation)
      g.drawImage(fallImages[fallAnimationIndex], posx, posy, null);// dibujar las animaciones de caida, falta por implementar
    else
      g.drawImage(birdImages[animationIndex], posx, posy, null);
  }
}
