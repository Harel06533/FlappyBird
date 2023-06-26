package app;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import components.Bird;
import components.Ground;
import components.Background;

import static util.Constant.FRAMERATE;
import static util.Constant.FLY_SOUND_PATH;
import static util.Constant.HIT_SOUND_PATH;
import static util.Constant.SCORE_SOUND_PATH;

import static util.SoundUtil.playSound;

/** Implementa un componente para trabajar gráficos encima */
public class GamePanel extends JPanel implements Runnable {
  // clase keyListener para que ocurra dentro del juego
  public class BirdKeyListener implements KeyListener {
    @Override
    public void keyPressed (KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_SPACE && bird.isKeyReleased()) {
        switch (gameState) {

          // caso 1, el juego está listo para empezar
          case GAME_READY:
            gameState = GAME_STARTED;
            bird.setState(Bird.BIRD_JUMPING);
            playSound(FLY_SOUND_PATH);
          break;

          // caso 2, el juego está en desarrollo
          case GAME_STARTED:
          if (bird.getState() == Bird.BIRD_DEAD) 
            gameState = GAME_STOPPED;
          break;

          // caso 3, el juego termino
          case GAME_STOPPED:
          break;
        }
        bird.setYVelocity(-7);
        bird.pressKey();
      }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        bird.releaseKey();
      }
    }

    /** NO SE DEBE DE USAR ESTA */
    @Override
    public void keyTyped (KeyEvent e) {
    }

  }

  // constantes
  public static final int GAME_READY = 0;
  public static final int GAME_STARTED = 1;
  public static final int GAME_STOPPED = 2;

  // atributos
  private Bird bird;

  private int width;
  private int height;

  private int gameState;

  private Thread thread;

  private Background background;
  private Ground ground;

  // constructor
  public GamePanel (int width, int height) {
    this.width = width;
    this.height = height;
    initPanel();
    initGame();

  }

  // inicializa los datos del Panel
  public void initPanel () {
    setPreferredSize(new Dimension(this.width, this.height));
    setBackground(Color.black);
    setOpaque(false);
    setFocusable(true);
    setDoubleBuffered(true);
    addKeyListener(new BirdKeyListener());
  }

  // inicializa el juego y sus componentes
  public void initGame () {
    background = new Background();
    ground = new Ground();
    bird = Bird.getBird();
    setGameState(GAME_READY);
  }

  // sobreescribe el componente para pintar los gráficos
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    background.draw(g);
    ground.draw(g);// dibuja el piso
    bird.draw(g);
    g.dispose();
  }

  // inicializa el hilo
  public void start () {
    thread = new Thread(this);
    thread.start();
  }

  // actualiza la información del juego para ser calculado en pantalla
  public void update () {
    bird.update();
  }

  // corre el hilo del juego para actualizar y renderizar imágenes (Calcula los tiempos de cada frame para que sea estable)
  @Override
  public void run () {
    long lastTime = System.nanoTime();
    double timePerFrame = 1000000000 / FRAMERATE;
    double delta = 0;
    long timer = 0;
    long fps = 0;

    while (true) {
      long currentTime = System.nanoTime();
      delta += (currentTime - lastTime) / timePerFrame;
      lastTime = currentTime;
      timer += (currentTime - lastTime);
      if (delta > 0) {
        update();
        repaint();
        delta--;
        fps++;
        }
      if (timer >= 1000000000) {
        System.out.println("FPS: " + fps);
        timer = 0;
        fps = 0;
      }
    }
  }

  // setters
  public void setGameState (int gameState) {
    this.gameState = gameState;
  }

  // getters
  public int getGameState () { return gameState; }
}