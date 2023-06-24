package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import app.GamePanel;

import components.tools.BirdKeyListener;

import static util.Constant.WIDTH; 
import static util.Constant.HEIGHT;
import static util.Constant.BIRD_IMGS_PATH;

import util.BaseUtil;

public class Bird {
  // constantes
  public static final int BIRD_IDLE = 0;
  public static final int BIRD_PLAYING = 1;
  public static final int BIRD_DEAD = 2;

  //atributos
  private static Bird instance = null;

  private static final int initialX = WIDTH >> 1;
  private static final int initialY = HEIGHT >> 1;

  private int width;
  private int height;
  private int posx;
  private int posy;
  private double xVelocity;
  private double yVelocity;

  private GamePanel parentPanel;
  private BirdKeyListener mainKeyListener;

  private BufferedImage[] birdImages;
  private BufferedImage image;

  // constructor privado (inicializa todas las variables necesarias)
  private Bird (GamePanel parentPanel, BirdKeyListener mainKeyListener) {
    posx = initialX;
    posy = initialY;
    xVelocity = 0;
    yVelocity = 7;
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

  // actualiza la inforamción del jugador basado en su estado
  public void update () {
    if (mainKeyListener.jumping == true) {
      posy -= yVelocity;
    } else {
      posy += yVelocity - 3;
    }
  }

  // dibuja el elemento en pantalla
  public void draw (Graphics g) {
    image = birdImages[1];
    g.drawImage(image, posx, posy, null);
  }
}
