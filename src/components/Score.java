package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import static util.Constant.WIDTH;
import static util.Constant.HIGHSCORE_BIN_PATH;

import util.BaseUtil;

public class Score {

  // atributos
  private int highscore;
  private int currentScore;

  // constructor
  public Score () {
    currentScore = 0;
    highscore = getHighscore();
  }

  // suma 1 al score 
  public void addToScore () {
    currentScore += 1;
  }

  // setea un nuevo highscore 
  public void setNewHighscore (int highscore) {
    this.highscore = highscore;
    BaseUtil.writeIntegerBin(HIGHSCORE_BIN_PATH, this.highscore);
  }

  // getScore 
  public int getScore () {
    return (currentScore);
  }

  // getHighscore desde un archivo binario
  public int getHighscore () {
    return (BaseUtil.readIntegerBin(HIGHSCORE_BIN_PATH));
  }

  // reset
  public void restart () {
    currentScore = 0;
  }

  // dibuja los datos de score al perder
  public void drawEndScore (Graphics g) {
    g.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
    g.setColor(Color.black);

    String scoreString = Integer.toString(currentScore);
    String highscoreString = Integer.toString(highscore);

    int sWidth = g.getFontMetrics().stringWidth(scoreString);
    int hsWidth = g.getFontMetrics().stringWidth(highscoreString);

    g.drawString(scoreString, (WIDTH / 2 - sWidth / 2) + 5, 315);
    g.drawString(highscoreString, (WIDTH / 2 - hsWidth / 2) + 5, 372);
  }

  // dibuja el score normal al jugar
  public void drawPlaying (Graphics g) {
    g.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
    g.setColor(Color.white);

    String stringScore = Integer.toString(currentScore);

    int textWidth = g.getFontMetrics().stringWidth(stringScore);

    g.drawString(stringScore, (WIDTH / 2 - textWidth / 2), 70);
  }
}
