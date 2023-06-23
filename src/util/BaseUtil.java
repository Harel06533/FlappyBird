package util;

/** Implementa funciones que serán de utilidad para la aplicación */
public abstract class BaseUtil { 

  // genera un número aleatorio
  public static int getRandomNumber (int min, int max) {
    return ((int)(Math.random() * (max - min)) + min);
  }
}
