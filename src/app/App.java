package app;

import static util.Constant.WIDTH;
import static util.Constant.HEIGHT;
import static util.Constant.TITLE;

/**
 * Implementación de un juego tipo Flappy Bird (Es un proyecto universitario) 
 * Esta implementación está basada enormemente en el código de <a href="https://github.com/kingyuluk/FlappyBird/tree/3df72aeaf881c90be38e716e3193df1e9323371e"><strong>kingyuluk</strong></a> en GitHub
 * @author Harel Alejandro Olguín Gaytán
 * @author Jorge Francisco Arriaga Escamilla
 * @see <a href="https://github.com/kingyuluk/FlappyBird/tree/3df72aeaf881c90be38e716e3193df1e9323371e">kingyuluk/GitHub</a>
 * @see <a href = "https://github.com/TheCherno/Flappy/tree/master">The Cherno</a>
 */
public class App {
  public static void main (String[] args) {
    new Window(WIDTH, HEIGHT, TITLE).gameStart();
  }
} 