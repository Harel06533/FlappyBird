package components;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/** Clase del pájaro, utiliza diseño Singleton para facilitar su manipulación a lo largo de la aplicación */
public class Bird {
  //atributos
  private static Bird instance = null;

  // constructor privado
  private Bird () {

  }

  // obtención de la instancia
  public static Bird getBird () {
    if (instance == null) 
      instance = new Bird();
    return instance;
  }
}
