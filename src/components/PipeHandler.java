package components;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import static util.Constant.WIDTH;
import static util.Constant.HEIGHT;

import static util.BaseUtil.getRandomNumber;

/** Esta clase se encarga de manejar la lógica de spawn y colocación de las tuberías */
public class PipeHandler { 
  // atributos
  private List<Pipe> pipeList;
  private boolean isFirstSpawn = true;
  private Bird bird;

  // constructor
  public PipeHandler () {
    pipeList = new ArrayList<>();
    bird = Bird.getBird();
  }

  // actualiza la información de cada tubería
  public void update () {
    // itera en la lista
    for (int i = 0; i < pipeList.size(); i++) {
      Pipe thisPipe = pipeList.get(i);
      thisPipe.update();

      // si la tubería salió de rango, la remueve
      if (thisPipe.getX() + thisPipe.getWidth() < 0) {
        pipeList.remove(i);
        i--;
      }

      // guarda las zonas de colisión de la tubería y el pájaro
      Rectangle bottomBounds = thisPipe.getBottomBounds();
      Rectangle topBounds = thisPipe.getTopBounds();
      Rectangle scoreBounds = thisPipe.getScoreBounds();
      Rectangle birdBounds = bird.getBirdBounds();
      
      // si el pájaro pasa por la tubería de arriba o la de abajo, muere
      if (topBounds.intersects(birdBounds) || bottomBounds.intersects(birdBounds)) 
        bird.setState(Bird.BIRD_DEAD);

      // si el pájaro pasa por la zona de score, aumenta 1 al score
      if (scoreBounds.contains(birdBounds))
        System.out.println("bird in scorezone");
      
    }
    // añade una nueva tubería si ya no hay en la lista, o si la anterior salió del rango
    if (pipeList.isEmpty() || pipeList.get(pipeList.size() - 1).getX() <= WIDTH - Pipe.PIPE_WIDTH - Pipe.GAP)
      addPipe();
  }

  // dibuja las tuberías
  public void draw (Graphics g) {
    for (Pipe pipe : pipeList) {
      pipe.draw(g);
    }
  }

  // reestablece todos los datos
  public void restart () {
    pipeList.clear();
    isFirstSpawn = true;
  }

  // añade una tubería a la lista
  public void addPipe () {
    int min = Pipe.GAP;                             //--> Valor mínimo de altura
    int max = (HEIGHT - Pipe.GAP * 2) - 155;        //--> Valor máximo de altura
    int randomPosy = getRandomNumber(min, max);     //--> Obtiene un número aleatorio entre el mínimo y el máximo
    int currx = WIDTH * 2;                          //--> Primer spawn en x de una tubería
    if (isFirstSpawn) {
      isFirstSpawn = false;
    } else {
      int lastX = pipeList.get(pipeList.size() - 1).getX();
      currx = lastX + Pipe.PIPE_WIDTH + 35 + Pipe.GAP;      //--> A partir del primer spawn, la posición x de las siguientes tuberías serán mas cercanas
    }
    
    Pipe newPipe = new Pipe(currx, randomPosy);
    pipeList.add(newPipe);
  }

  public List<Pipe> getPipes () {
    return pipeList;
  }
}
