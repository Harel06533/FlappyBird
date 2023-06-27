package util;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public abstract class SoundUtil {
  // atributos 

  // carga y reproduce un archivo de audio
  public static void playSound (String path) {
    try {
      File soundFile = new File(path);
      AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
      Clip clip = AudioSystem.getClip();
      clip.open(audioInput);
      clip.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}