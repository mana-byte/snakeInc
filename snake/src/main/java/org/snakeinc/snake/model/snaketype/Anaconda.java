package org.snakeinc.snake.model.snaketype;

import org.snakeinc.snake.model.Snake;
import org.snakeinc.snake.model.AppleEatenListener;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import java.awt.Color;

import org.snakeinc.snake.model.Apple;

public class Anaconda extends Snake {
  public Anaconda(AppleEatenListener listener, Grid grid) {
    super(listener, grid);
    this.color = Color.GRAY;
  }
}
