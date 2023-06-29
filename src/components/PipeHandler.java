package components;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static util.Constant.WIDTH;
import static util.Constant.HEIGHT;

public class PipeHandler {
    private List<Pipe> pipes;  
    private Random random; // Generador de números aleatorios
    private boolean firstGame; // Bandera que indica si es el primer juego

    /**
     * Constructor de la clase PipeHandler.
     * Inicializa la lista de tuberías, el generador de números aleatorios y la bandera de primer juego.
     */
    public PipeHandler() {
        pipes = new ArrayList<>();
        random = new Random();
        firstGame = true;
    }

    /**
     * Actualiza la posición de las tuberías y realiza las operaciones necesarias.
     */
    public void update() {
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.update();

            // Si la tubería sale de la pantalla, se elimina de la lista
            if (pipe.getX() + pipe.getWidth() < 0) {
                pipes.remove(i);
                i--;
            }
        }

        // Añadir una nueva tubería si es necesario
        if (pipes.isEmpty() || pipes.get(pipes.size() - 1).getX() <= WIDTH - Pipe.PIPE_WIDTH - Pipe.GAP) {
            addPipe();
        }
    }

    /**
     * Dibuja todas las tuberías en el objeto Graphics proporcionado.
     * @param g Objeto Graphics para dibujar las tuberías.
     */
    public void draw(Graphics g) {
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }
    }

    /**
     * Reinicia el estado de las tuberías y la bandera de primer juego.
     */
    public void reset() {
        pipes.clear();
        firstGame = true;
    }

    /**
     * Agrega una nueva tubería a la lista de tuberías.
     * Genera una posición vertical aleatoria para la nueva tubería y calcula la posición X
     * en función de la posición X de la última tubería.
     */
    private void addPipe() {
        int randomY = random.nextInt(HEIGHT - 2 * Pipe.PIPE_HEIGHT - Pipe.GAP) + Pipe.PIPE_HEIGHT;

        int newX;
        if (firstGame) {
            newX = WIDTH; // Posición inicial en el lado derecho de la pantalla
            firstGame = false;
        } else {
            int lastX = pipes.get(pipes.size() - 1).getX();
            newX = lastX + Pipe.PIPE_WIDTH + Pipe.GAP;
        }

        // Crea una nueva tubería en la posición adecuada y la añade a la lista
        Pipe newPipe = new Pipe(newX, randomY);
        pipes.add(newPipe);
    }
}
