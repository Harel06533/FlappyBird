package components.tools;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class BirdKeyListener implements KeyListener {
  // atributos
  public boolean canJump = true;
  public boolean jumping = false;

  @Override
  public void keyPressed (KeyEvent e) {
    int keyCode = e.getKeyCode();
    if (keyCode == KeyEvent.VK_SPACE) {
    }
  }
  
  @Override
  public void keyReleased (KeyEvent e) {
    jumping = false;
    canJump = true;
  }

  @Override
  public void keyTyped (KeyEvent e) {
  }
  
}
