package app;

import javax.swing.JFrame;

/* Clase de la ventana principal */
public class Window extends JFrame {
  // atributos
  GamePanel panelComponent;

  /** Clase Window, implementa la ventana principal del juego */
  public Window (int width, int height, String title) {
    // configuración básica
    setTitle(title);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Setea el panel para su uso
    panelComponent = new GamePanel(width, height);
    add(panelComponent);
    
    // configuración de la visibilidad
    pack();                         //--> Pack permite dimensionar el frame en base a sus componentes hijos
    setLocationRelativeTo(null);  
    setVisible(true);
  } 

  public void gameStart () {
    panelComponent.start();
  }
}   