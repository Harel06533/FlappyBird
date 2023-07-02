package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import static util.Constant.WIDTH;

public class Score {

  // atributos
  private int highscore;
  private int currentScore;

  // constructor
  public Score () {
    currentScore = 0;
  }

  // suma 1 al score 
  public void addToScore () {
    currentScore += 1;
  }

  // setea un nuevo highscore 
  public void setNewHighscore (int highscore) {
    this.highscore = highscore;
  }

  // getScore 
  public int getScore () {
    return (currentScore);
  }

  // getHighscore
  public int getHighscore () {
    return (highscore);
  }

  // reset
  public void restart () {
    currentScore = 0;
  }

  // dibuja
  public void draw (Graphics g) {
    g.setFont(new Font("Sans Serif", Font.BOLD, 50));
    g.setColor(Color.white);
    String stringScore = Integer.toString(currentScore);
    int textWidth = g.getFontMetrics().stringWidth(stringScore);
    g.drawString(stringScore, (WIDTH / 2 + textWidth / 2) - 30, 70);
  }
}
