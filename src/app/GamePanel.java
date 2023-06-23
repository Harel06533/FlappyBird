package app;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import components.tools.BirdKeyListener;

import static util.Constant.FRAMERATE;

/** Implementa un componente para trabajar gráficos encima */
public class GamePanel extends JPanel implements Runnable {
  // atributos
  private Thread gameThread;
  private int width;
  private int height;
  private int posx;
  private int posy;
  private BirdKeyListener birdKeyListener;

  // constructor, inicializa el panel
  public GamePanel (int width, int height) {
    // crea un keylistener
    birdKeyListener = new BirdKeyListener();

    this.width = width;
    this.height = height;

    // mitad de la pantalla
    posx = this.width >> 1;
    posy = this.height >> 1;

    // setea visibilidad
    this.setPreferredSize(new Dimension(this.width, this.height));
    this.setBackground(Color.black);

    // setea el keylistener
    this.addKeyListener(birdKeyListener);
    this.setFocusable(true);
    
    // doble buffer de renderizado para mayor rendimiento
    this.setDoubleBuffered(true);
  }

  // comienza el hilo
  public void start () {
    gameThread = new Thread(this);
    gameThread.start();
  }

  // sobreescribe el método paintComponent de JPanel
  @Override
  protected void paintComponent (Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.cyan);
    g.fillRect(posx, posy, 48, 48);
    g.dispose();
  }

  // implementa run de la interfaz Runnable
  @Override
  public void run () {
    double timePerFrame = 1000000000 / FRAMERATE;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    int fps = 0;
    while (true) {
      currentTime = System.nanoTime();
      delta += (currentTime - lastTime) / timePerFrame;
      timer += (currentTime - lastTime);
      lastTime = currentTime;
      if (delta > 0) {
        update();
        repaint();
        delta--;
        fps++;
      }
      if (timer >= 1000000000) {
        System.out.println("FPS: " + fps);
        fps = 0;
        timer = 0;
      }
    }
  }
  
  // UPDATE: actualiza la información al renderizar
  public void update () {
    if (birdKeyListener.jumping == true) {
      posy -= 10;
    } else {
      posy += 4;
    }
  }
}
