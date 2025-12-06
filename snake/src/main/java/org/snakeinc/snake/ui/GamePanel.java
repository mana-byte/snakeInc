package org.snakeinc.snake.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.SizeIsZeroException;
import org.snakeinc.snake.model.Game;
import org.snakeinc.snake.utils.IntegerWrapper;
import org.snakeinc.snake.model.Directions;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

  public static final int TILE_PIXEL_SIZE = 20;
  public static final int GAME_PIXEL_WIDTH = TILE_PIXEL_SIZE * GameParams.TILES_X;
  public static final int GAME_PIXEL_HEIGHT = TILE_PIXEL_SIZE * GameParams.TILES_Y;

  private Timer timer;
  private Game game;
  private boolean running = false;
  private Directions direction = Directions.RIGHT;

  public GamePanel() {
    this.setPreferredSize(new Dimension(GAME_PIXEL_WIDTH, GAME_PIXEL_HEIGHT));
    this.setBackground(Color.BLACK);
    this.setFocusable(true);
    this.addKeyListener(this);
    startGame();
  }

  private void startGame() {
    game = new Game();
    timer = new Timer(100, this);
    timer.start();
    running = true;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (running) {
      UIUtils.draw(g, game);
    } else {
      gameOver(g, game.getScore());
    }
  }

  private void gameOver(Graphics g, IntegerWrapper score) {
    final int fontSize = 20;
    final int offset = 10;

    g.setColor(Color.RED);
    g.setFont(new Font("Arial", Font.BOLD, fontSize));
    FontMetrics metrics = getFontMetrics(g.getFont());
    g.drawString("Game Over" , (GAME_PIXEL_WIDTH - metrics.stringWidth("Game Over")) / 2, GAME_PIXEL_HEIGHT / 2);
    g.drawString("Score: " + score.value, (GAME_PIXEL_WIDTH - metrics.stringWidth("Score: " + score.value)) / 2, GAME_PIXEL_HEIGHT / 2 + offset + fontSize);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (running) {
      try {
        game.iterate(direction);
      } catch (OutOfPlayException | SelfCollisionException | SizeIsZeroException exception) {
        timer.stop();
        running = false;
      }
    }
    repaint();
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_LEFT:
        if (direction != Directions.RIGHT) {
          direction = Directions.LEFT;
        }
        break;
      case KeyEvent.VK_RIGHT:
        if (direction != Directions.LEFT) {
          direction = Directions.RIGHT;
        }
        break;
      case KeyEvent.VK_UP:
        if (direction != Directions.DOWN) {
          direction = Directions.UP;
        }
        break;
      case KeyEvent.VK_DOWN:
        if (direction != Directions.UP) {
          direction = Directions.DOWN;
        }
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }
}
