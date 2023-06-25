package app;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import components.Bird;
import components.Ground;
import components.Background;
import components.tools.BirdKeyListener;

import static util.Constant.FRAMERATE;

import static util.Constant.FLY_SOUND_PATH;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.io.File;




/** Implementa un componente para trabajar gráficos encima */
public class GamePanel extends JPanel implements Runnable {
  // atributos
  private int width;
  private int height;

  private Thread thread;

  private BirdKeyListener birdKeyListener;
  private Bird bird;
  private Background background;
  private Ground ground;
  private String soundFilePath = FLY_SOUND_PATH;

  // constructor
  public GamePanel (int width, int height) {
    birdKeyListener = new BirdKeyListener();
    background = new Background();
    ground = new Ground();
    bird = Bird.getBird(this, birdKeyListener);

    this.width = width;
    this.height = height;

    setPreferredSize(new Dimension(this.width, this.height));
    setBackground(Color.black);

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
    playSound(Bird.getFlySoundPath());
    bird.update();
  }

private void playSound(String soundFilePath) {
  try {
    File soundFile = new File(soundFilePath);
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    volumeControl.setValue(-10.0f); // Ajusta el volumen aquí (en dB)
    clip.start();
  } catch (Exception e) {
    e.printStackTrace();
  }
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
            bird.update();
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
