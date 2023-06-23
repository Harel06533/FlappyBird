package util;

/** Constantes que se utilizarán a lo largo de toda la aplicación */
public abstract class Constant {
  // version actuál del juego  
  private static final String version = "ALPHA v1.0";

  // pantalla
  public static final int WIDTH = 432;
  public static final int HEIGHT = 768;
  public static final double FRAMERATE = 120.0;
  public static final String TITLE = "Flappy Bird " + version;

  // path (es probable que se tengan que actualizar los datos)
  public static final String[] birdImgs = {"../assets/img/bird0.png", "../assets/img/bird1.png", "../assets/img/bird2.png"};
  public static final String groundImg = "../assets/img/ground.png";
  public static final String pipeImg = "../assets/img/pipe.png";

  public static final String flySoundPath = "../assets/audio/fly.wav";
  public static final String hitSoundPath = "../assets/audio/hit.wav";
  public static final String scoreSoundPath = "../assets/audio/score.wav";
}
