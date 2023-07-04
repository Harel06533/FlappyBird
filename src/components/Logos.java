package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import app.GamePanel;
import util.BaseUtil;
import static util.Constant.LOGO_IMG_PATH;
import static util.Constant.START_IMG_PATH;
import static util.Constant.LOST_IMG_PATH;
import static util.Constant.RESTART_IMG_PATH;
import static util.Constant.HIGHSCORE_IMG_PATH;

/**
 * Clase que representa un logotipo en el juego.
 */
public class Logos {
    private BufferedImage logoImage; // Imagen del logotipo
    private BufferedImage startImage; // Imagen de start
    private BufferedImage lostImage; // Imagen de perdedor
    private BufferedImage restartImage; // Imagen de restart
    private BufferedImage highscoreImage; // Imagen de highscore
    private int lostx; // Posición x del game over
    private int losty; // Posición y del game over
    private int startx; // Posicion x del logo start
    private int starty; // Posicion y del logo start
    private int x; // Posición x del logotipo
    private int y; // Posición y del logotipo
    private int restx; // Posicion x de restart
    private int resty; // Posicion y de restart
    private int highx; // Posición x del highscore
    private int highy; // Posición y del highscore

    /**
     * Constructor de la clase Logo.
     * Carga la imagen del logotipo y establece su posición en el centro de la pantalla.
     */
    public Logos() {
        setLogoImage();
        setStartImage();
        setLostImage();
        setRestartImage();
        setHighscore();

        if (logoImage != null) {
            int logoWidth = logoImage.getWidth();
            int logoHeight = logoImage.getHeight();
            int startImageWidth = startImage.getWidth();
            int startImageHeight = startImage.getHeight();
            int lostWidth = lostImage.getWidth();
            int lostHeight = lostImage.getHeight();
            int restartHeight = restartImage.getHeight();
            int restartWidth = restartImage.getWidth();
            int highscoreHeight = highscoreImage.getHeight();
            int highscoreWidth = highscoreImage.getWidth();

            this.startx = (GamePanel.WIDTH - startImageWidth) / 2+220; // Ajusta la posición horizontal del logotipo de start
            this.starty = (GamePanel.HEIGHT - startImageHeight) / 2+450; // Ajusta la posición vertical del logotipo de start

            this.x = (GamePanel.WIDTH - logoWidth) / 2 + 215; // Ajusta la posición horizontal del logotipo
            this.y = (GamePanel.HEIGHT - logoHeight) / 2 + 200; // Ajusta la posición vertical del logotipo

            this.lostx = (GamePanel.WIDTH - lostWidth) / 2 + 220; // Ajusta la posición horizontal del logotipo de perdedor
            this.losty = (GamePanel.HEIGHT - lostHeight) / 2 + 150; // Ajusta la posición vertical del logotipo de perdedor

            this.restx = (GamePanel.WIDTH - restartWidth) / 2 + 220;
            this.resty = (GamePanel.HEIGHT - restartHeight) / 2 + 500;

            this.highx = (GamePanel.HEIGHT - highscoreWidth) / 2 + 220;
            this.highy = (GamePanel.HEIGHT - highscoreHeight) / 2 + 320;

        } else {
            System.err.println("No se pudo cargar la imagen del logotipo: " + LOGO_IMG_PATH);
            System.err.println("No se puedo cargar la imagen de start: " + START_IMG_PATH);
            System.err.println("No se puedo cargar la imagen de perdedor: " + LOST_IMG_PATH);
            System.err.println("No se puedo cargar la imagen de restart: " + RESTART_IMG_PATH);
            System.err.println("No se puedo cargar la imagen de highscore: " + HIGHSCORE_IMG_PATH);
            this.x = 0;
            this.y = 0;
            this.startx = 0;
            this.starty = 0;
            this.lostx = 0;
            this.losty = 0;
            this.restx = 0;
            this.resty = 0;
            this.highx = 0;
            this.highy = 0;
            
        }
    }

    public void setLogoImage(){
        this.logoImage = BaseUtil.loadBufferedImage(LOGO_IMG_PATH);
    }

    public void setStartImage(){
        this.startImage = BaseUtil.loadBufferedImage(START_IMG_PATH);
    }

    public void setLostImage(){
        this.lostImage = BaseUtil.loadBufferedImage(LOST_IMG_PATH);
    }

    public void setRestartImage(){
        this.restartImage = BaseUtil.loadBufferedImage(RESTART_IMG_PATH);
    }

    public void setHighscore(){
        this.highscoreImage = BaseUtil.loadBufferedImage(HIGHSCORE_IMG_PATH);
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
        g.drawImage(restartImage, restx, resty, null);
        g.drawImage(highscoreImage, highx, highy, null);
    }

}
