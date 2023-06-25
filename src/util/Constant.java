package util;

/** Constantes que se utilizarán a lo largo de toda la aplicación */
public abstract class Constant {
  // version actuál del juego  
  private static final String version = "ALPHA v1.0";

  // pantalla
  public static final int WIDTH = 432;
  public static final int HEIGHT = 768;
  public static final double FRAMERATE = 60.0;
  public static final String TITLE = "Flappy Bird " + version;

  // path (es probable que se tengan que actualizar los datos)
  public static final String[] BIRD_IMGS_PATH = {"./FlappyBird/assets/img/bird0.png", "./FlappyBird/assets/img/bird1.png", "./FlappyBird/assets/img/bird2.png"};
  public static final String GROUND_IMG_PATH = "./FlappyBird/assets/img/ground.png";
  public static final String PIPE_IMG_PATH = "./FlappyBird/assets/img/pipe.png";
  public static final String BACKGROUND_IMG_PATH = "./FlappyBird/assets/img/background.png";
  public static final String[] FALL_IMGS_PATH = {"./FlappyBird/assets/img/down0.png","./FlappyBird/assets/img/down1.png",
                                                  "./FlappyBird/assets/img/down2.png","./FlappyBird/assets/img/down3.png",
                                                "./FlappyBird/assets/img/down4.png","./FlappyBird/assets/img/down5.png",
                                              "./FlappyBird/assets/img/down6.png","./FlappyBird/assets/img/down7.png"};

  public static final String FLY_SOUND_PATH = "./FlappyBird/assets/audio/fly.wav";
  public static final String HIT_SOUND_PATH = "./FlappyBird/assets/audio/hit.wav";
  public static final String SCORE_SOUND_PATH = "./FlappyBird/assets/audio/score.wav";
}
