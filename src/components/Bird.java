package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import app.GamePanel;
import components.tools.BirdKeyListener;

import static util.Constant.WIDTH; 
import static util.Constant.HEIGHT;

import static util.Constant.BIRD_IMGS_PATH;

import static util.Constant.FLY_SOUND_PATH;
import static util.Constant.HIT_SOUND_PATH;
import static util.Constant.SCORE_SOUND_PATH;

import util.BaseUtil;

public class Bird {
  // constantes
  public static final int MOVEMENT_STATES = 2;

  public static final int BIRD_IDLE = 0;
  public static final int BIRD_MOVIND = 1;
  public static final int BIRD_FALLING = 2;
  public static final int BIRD_DEAD = 3;

  private static final double MAX_VEL_Y = 12;
  private static final double ACC_Y = 0.6;

  private static final int initialX = WIDTH >> 1;
  private static final int initialY = HEIGHT >> 1;

  //atributos
  private static Bird instance = null;


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

  private BufferedImage[][] birdImages;


  // constructor privado (inicializa todas las variables necesarias)
  private Bird (GamePanel parentPanel, BirdKeyListener mainKeyListener) {
    animationSpeed = 8;
    animationTick = 0;
    animationIndex = 0;

    posx = initialX;
    posy = initialY;

    state = BIRD_IDLE;
    
    birdImages = new BufferedImage[BIRD_IMGS_PATH.length][BIRD_IMGS_PATH[0].length];
    for (int i = 0; i < BIRD_IMGS_PATH.length; i++) {
      for (int j = 0; j < BIRD_IMGS_PATH[i].length; j++) {
        birdImages[i][j] = BaseUtil.loadBufferedImage(BIRD_IMGS_PATH[i][j]);
      }
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
}

// dibuja el elemento en pantalla
  public void draw (Graphics g) {
  }
}