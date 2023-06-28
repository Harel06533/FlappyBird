package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import util.BaseUtil;
import static util.Constant.PIPE_IMG_PATH;

public class Pipe {

  private static final int PIPE_WIDTH = 52; // ANCHO DE LA TUBERIA
  private static final int PIPE_HEIGHT = 320; // LARGO DE LA TUBERIA
  private static final int PIPE_GAP = 100; // ESPACIO QUE HAY ENTRE LAS TUBERIAS

  private BufferedImage pipeImage;
  private int posx;
  private int topPipeHeight; // punto alto de la tuberia
  private int bottomPipeHeight; // punto bajo de la tuberia
  
  // Constructor
  public Pipe() {
    pipeImage = BaseUtil.loadBufferedImage(PIPE_IMG_PATH);
    assert pipeImage != null;
    //posx = initialX;
    randomPipe();
  }

  //Metodo para randomizar la posición de las tuberias
  public void randomPipe() {
    int maxTop = (Bird.getBird().getPosY() - PIPE_GAP); //  Obtiene la posicion y y le resta el espacio que hay entre las tuberias
    int minTop = maxTop - PIPE_HEIGHT; // Se obtiene restando la altura de la tuberia a la altura max  de la parte superior
    topPipeHeight = BaseUtil.getRandomNumber(minTop, maxTop); // Se genera una altura aleatoria para la parte superior
    bottomPipeHeight = topPipeHeight + PIPE_HEIGHT +PIPE_GAP; // Se calcula sumando la altura de la parte superior, la altura de la tuberia y el espacio entre tuberias
  }

  // Metodo para actualizar la tuberia en cada frame
  public void update() {
    posx += 2; // mueve la tuberia a la izquierda en cada update
    if(posx + PIPE_WIDTH < 0) { // si la tuberia se sale de la pantall
      posx = 800; // regresa la tuberia del lado derecho
      randomPipe(); // vuelve a generar otras tuberias
    }
  }

  // Metodo para dibujar las tuberias
  public void draw(Graphics g) {
    g.drawImage(pipeImage, posx, topPipeHeight - PIPE_HEIGHT, null); // dibuja la tuberia de arriba
    g.drawImage(pipeImage, posx, bottomPipeHeight, null); // dibuja la tuberia de abajo
  }


}
