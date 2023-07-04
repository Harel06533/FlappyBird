package util;

/** Constantes que se utilizarán a lo largo de toda la aplicación */
public abstract class Constant {
  // version actuál del juego  
  private static final String version = "(IN DEVELOPMENT) v4.0";

  // pantalla
  public static final int WIDTH = 432;
  public static final int HEIGHT = 768;
  public static final int GROUND_HEIGHT = 600;
  public static final int GAME_VELOCITY = 3;
  public static final double FRAMERATE = 60.0;
  public static final String TITLE = "Flappy Bird " + version;

  // path (es probable que se tengan que actualizar los datos)
  public static final String[] BIRD_IMGS_PATH = {
    "./assets/img/bird/bird0.png", 
    "./assets/img/bird/bird1.png", 
    "./assets/img/bird/bird2.png", 
    "./assets/img/bird/bird3.png", 
    "./assets/img/bird/bird4.png", 
    "./assets/img/bird/bird5.png"
  };
  public static final String GROUND_IMG_PATH = "./assets/img/ground.png";
  public static final String BACKGROUND_IMG_PATH = "./assets/img/background.png";
  public static final String LOGO_IMG_PATH = "./assets/img/logo.png";
  public static final String START_IMG_PATH = "./assets/img/start.png";
  public static final String LOST_IMG_PATH = "./assets/img/gameover.png";
  
  public static final String FLY_SOUND_PATH = "./assets/audio/fly.wav";
  public static final String HIT_SOUND_PATH = "./assets/audio/hit.wav";
  public static final String SCORE_SOUND_PATH = "./assets/audio/score.wav";

  public static final String PIPE_TOP_IMG_PATH = "./assets/img/pipe2_top.png";
  public static final String PIPE_BOTTOM_IMG_PATH = "./assets/img/pipe2_bottom.png";
}