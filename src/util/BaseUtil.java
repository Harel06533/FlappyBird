package util;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/** Implementa funciones que serán de utilidad para la aplicación */
public abstract class BaseUtil { 

  // genera un número aleatorio
  public static int getRandomNumber (int min, int max) {
    return ((int)(Math.random() * (max - min)) + min);
  }

  // carga un archivo binario de enteros, lo lee y retorna sus datos
  public static int readIntegerBin (String path) {
    int value = 0; 
    try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(path))) {
        value = dataInputStream.readInt();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return value;
  }

  // escribe en un archivo binario de enteros
  public static void writeIntegerBin (String path, int value) {
    try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(path))) {
      dataOutputStream.writeInt(value);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


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

