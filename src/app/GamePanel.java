package app;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import components.Bird;
import components.PipeHandler;
import components.Ground;
import components.Logos;
import components.Background;

import static util.Constant.FRAMERATE;

/** Implementa un componente para trabajar gráficos encima */
public class GamePanel extends JPanel implements Runnable {
  // clase keyListener para que ocurra dentro del juego
  public class BirdKeyListener implements KeyListener {
    @Override
    public void keyPressed (KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_SPACE && bird.isKeyReleased()) {    //--> Solo ocurre cuando se presionó el espacio y no estaba presionando ninguna tecla
        switch (gameState) {

          // caso 1, el juego está listo para empezar
          case GAME_READY:
            gameState = GAME_STARTED;

          // caso 2, el juego está en desarrollo 
          case GAME_STARTED:
            bird.fly();            //--> Brinca
          break;

          // caso 3, el juego termino
          case GAME_STOPPED:
            restart();
          break;
        }
        bird.pressKey();        //--> Variable flag que determina si está o no presionada la tecla
      }
    }
    
    // qué ocurre cuándo se deja de presionar la tecla
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
  
  private int width;
  private int height;
  
  private int gameState;
  
  private Thread thread;
  
  private Bird bird;
  private PipeHandler pipeHandler;
  private Background background;
  private Ground ground;
  private Logos logo;

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
    pipeHandler = new PipeHandler();
    ground = new Ground();
    bird = Bird.getBird();
    //pipe = new Pipe();
    logo = new Logos();
    gameState = GAME_READY;
    
  }

  // sobreescribe el componente para pintar los gráficos
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    background.draw(g);
    bird.draw(g);
    
    if (gameState == GAME_READY) // si el juego esta listo para empezar
      logo.draw(g); // dibuja el titulo y el space bar

    // si el juego comenzó, dibja las tuberías
    if (gameState == GAME_STARTED) {
      pipeHandler.draw(g);
      bird.drawScore(g);
    }
    
    if(gameState == GAME_STOPPED) {// si el juego se para
      pipeHandler.draw(g); // dibuja las tuberías (internamente no poseen movimiento)
      logo.draw_lost(g); // dibuja el game over
    }
    
    ground.draw(g);
    g.dispose();
  }


  // inicializa el hilo
  public void start () {
    thread = new Thread(this);
    thread.start();
  }

  // reinicia
  public void restart () {
    gameState = GAME_READY;
    bird.restart();
    pipeHandler.restart();
  }

  // actualiza la información del juego para ser calculado en pantalla
  public void update () {
    bird.update();
    // si el juego empezó, comienza a crear y mostrar tuberías
    if (gameState == GAME_STARTED) {
      pipeHandler.update();
    }
    // si el pájaro está muerto, entonces el juego está en STOP
    if (bird.getState() == Bird.BIRD_DEAD)
      gameState = GAME_STOPPED;
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