package app;

import javax.swing.JPanel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import components.Bird;
import components.tools.BirdKeyListener;

import static util.Constant.FRAMERATE;
import static util.Constant.BACKGROUND_IMG_PATH;

import components.Background;

import components.Bird;

/** Implementa un componente para trabajar gráficos encima */
public class GamePanel extends JPanel implements Runnable {
  // atributos
  private int width;
  private int height;

  private Thread thread;
  private BirdKeyListener birdKeyListener;
  private Bird bird;



  // constructor
  public GamePanel (int width, int height) {
    birdKeyListener = new BirdKeyListener();
    bird = Bird.getBird(this, birdKeyListener);
    this.width = width;
    this.height = height;

    setPreferredSize(new Dimension(this.width, this.height));
    setBackground(Color.black);
    /* Establece la imagen de fondo */
    setPreferredSize(new Dimension(this.width, this.height));
    setOpaque(false); // Hace que el panel sea transparente



    addKeyListener(birdKeyListener);
    setFocusable(true);

    setDoubleBuffered(true);
  }

  // sobreescribe el componente para pintar los gráficos
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Background fondo = new Background(BACKGROUND_IMG_PATH);
    fondo.drawBackground(g);
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
      timer += (currentTime -lastTime);

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
}