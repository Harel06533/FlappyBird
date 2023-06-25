package components.tools;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class BirdKeyListener implements KeyListener {
  // atributos

  @Override
  public void keyPressed (KeyEvent e) {
    boolean wasSpaceBar = (e.getKeyCode() == KeyEvent.VK_SPACE);
    if (wasSpaceBar) {

    }
  }
  
  @Override
  public void keyReleased (KeyEvent e) {
  }

  @Override
  public void keyTyped (KeyEvent e) {

  }
  
}