package app;

// Importar Utilidades
import util.Constant;

// Importar Componentes
import components.Window;

/**
 * Clase Game, implementa la clase Runnable (Para utilizar hilos)
 */
public class Game implements Runnable {
  //Constantes

  // Atributos de pantalla 
  protected int width = Constant.GAME_WIDTH;
  protected int height = Constant.GAME_HEIGHT;
  protected String title = Constant.TITLE;

  // Hilo 
  public Thread thread;

  // Inicializa un nuevo hilo para esta clase (Instancia válida de Runnable) que se encargará del juego principal
  public void start() {
    thread = new Thread(this, "Game"); // this es Runnable y buscará el método run() para su ejecución
    thread.start();
  }

  // El método run implementa la interfáz Runnable (Al referenciar un objeto Runnable, este método corre)
  public void run () {
    new Window(width, height, title);    
  }
}
