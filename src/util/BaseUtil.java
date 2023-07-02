package util;

import java.awt.image.BufferedImage;

import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/** Implementa funciones que serán de utilidad para la aplicación */
public abstract class BaseUtil { 

  // genera un número aleatorio
  public static int getRandomNumber (int min, int max) {
    return ((int)(Math.random() * (max - min)) + min);
  }

  // carga un archivo binario y lo lee


  // carga las imágenes
  public static BufferedImage loadBufferedImage (String path) {
    try {
      return (ImageIO.read(new FileInputStream(path)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}

