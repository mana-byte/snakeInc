package org.snakeinc.snake.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import lombok.AllArgsConstructor;
import org.snakeinc.snake.model.Cell;

@AllArgsConstructor
public class CellUI {

  private Cell cell;
  private int upperPixelX;
  private int upperPixelY;

  public void drawRectangle(Graphics g, Color color) {
    g.fillRect(upperPixelX, upperPixelY, GamePanel.TILE_PIXEL_SIZE, GamePanel.TILE_PIXEL_SIZE);
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(color.darker());
    g2.setStroke(new BasicStroke(2));
    g2.drawRect(upperPixelX, upperPixelY, GamePanel.TILE_PIXEL_SIZE, GamePanel.TILE_PIXEL_SIZE);
  }

  public void drawOval(Graphics g) {
    g.fillOval(upperPixelX, upperPixelY, GamePanel.TILE_PIXEL_SIZE, GamePanel.TILE_PIXEL_SIZE);
  }

  public void draw(Graphics g) {

    if (cell.containsAnApple()) {
      g.setColor(Color.RED);
      drawOval(g);
    }
    if (cell.containsASnake()) {
      Color color = cell.getSnake().getColor().brighter();
      g.setColor(color);
      drawRectangle(g, color);
    }

  }

}
