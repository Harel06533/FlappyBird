package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import app.GamePanel;
import util.BaseUtil;
import static util.Constant.LOGO_IMG_PATH;
import static util.Constant.START_IMG_PATH;
import static util.Constant.LOST_IMG_PATH;

/**
 * Clase que representa un logotipo en el juego.
 */
public class Logos {
    private BufferedImage logoImage; // Imagen del logotipo
    private BufferedImage startImage; // Imagen de start
    private BufferedImage lostImage; // Imagen de perdedor
    private int lostx; // Posición x del game over
    private int losty; // Posición y del game over
    private int startx; // Posicion x del logo start
    private int starty; // Posicion y del logo start
    private int x; // Posición x del logotipo
    private int y; // Posición y del logotipo

    /**
     * Constructor de la clase Logo.
     * Carga la imagen del logotipo y establece su posición en el centro de la pantalla.
     */
    public Logos() {
        this.logoImage = BaseUtil.loadBufferedImage(LOGO_IMG_PATH);
        this.startImage = BaseUtil.loadBufferedImage(START_IMG_PATH);
        this.lostImage = BaseUtil.loadBufferedImage(LOST_IMG_PATH);

        if (logoImage != null) {
            int logoWidth = logoImage.getWidth();
            int logoHeight = logoImage.getHeight();
            int startImageWidth = logoImage.getWidth();
            int startImageHeight = startImage.getHeight();
            int lostWidth = lostImage.getWidth();
            int lostHeight = lostImage.getHeight();

            this.startx = (GamePanel.WIDTH - startImageWidth) / 2+250; // Ajusta la posición horizontal del logotipo de start
            this.starty = (GamePanel.HEIGHT - startImageHeight) / 2+450; // Ajusta la posición vertical del logotipo de start

            this.x = (GamePanel.WIDTH - logoWidth) / 2 + 215; // Ajusta la posición horizontal del logotipo
            this.y = (GamePanel.HEIGHT - logoHeight) / 2 + 200; // Ajusta la posición vertical del logotipo

            this.lostx = (GamePanel.WIDTH - lostWidth) / 2 + 220; // Ajusta la posición horizontal del logotipo de perdedor
            this.losty = (GamePanel.HEIGHT - lostHeight) / 2 + 300; // Ajusta la posición vertical del logotipo de perdedor

        } else {
            System.err.println("No se pudo cargar la imagen del logotipo: " + LOGO_IMG_PATH);
            System.err.println("No se puedo cargar la imagen de start: " + START_IMG_PATH);
            System.err.println("No se puedo cargar la imagen de perdedor: " + LOST_IMG_PATH);
            this.x = 0;
            this.y = 0;
            this.startx = 0;
            this.starty = 0;
            this.lostx = 0;
            this.losty = 0;
        }
    }

    /**
     * Dibuja el logotipo en el componente gráfico proporcionado.
     *
     * @param g Componente gráfico en el que se dibujará el logotipo.
     */
    public void draw(Graphics g) {
        g.drawImage(logoImage, x, y, null);
        g.drawImage(startImage, startx, starty, null);
    }

    public void draw_lost(Graphics g){
        g.drawImage(lostImage, lostx, losty, null);
    }
}
