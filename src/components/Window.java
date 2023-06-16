package components;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Hereda de la clase JFrame para crear una ventana específica del juego
 */
public class Window extends JFrame {
  public Window (int width, int height, String title) {
    // Setters
    setSize(width, height);
    setTitle(title);
    setResizable(false);
    setVisible(true);

    // Al cerrar la ventana finaliza el programa
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
