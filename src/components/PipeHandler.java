package components;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static util.Constant.WIDTH;
import static util.Constant.HEIGHT;

import static util.BaseUtil.getRandomNumber;

public class PipeHandler { 
  // constantes

 
  private List<Pipe> pipeList;
  private boolean isFirstSpawn = true;

  // constructor
  public PipeHandler () {
    pipeList = new ArrayList<>();
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
      currx = lastX + Pipe.PIPE_WIDTH + 30 + Pipe.GAP;      //--> A partir del primer spawn, la posición x de las siguientes tuberías serán mas cercanas
    }
    
    Pipe newPipe = new Pipe(currx, randomPosy);
    pipeList.add(newPipe);
  }
}
