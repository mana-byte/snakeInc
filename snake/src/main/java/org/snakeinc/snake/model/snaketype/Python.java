package org.snakeinc.snake.model.snaketype;

import org.snakeinc.snake.model.AppleEatenListener;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import java.awt.Color;

import org.snakeinc.snake.model.Apple;

public final class Python extends Snake {
  public Python(AppleEatenListener listener, Grid grid) {
    super(listener, grid);
    this.color = Color.GREEN;
  }

  @Override
  public void eat(Apple apple, Cell cell) {
    onAppleEatenListener.onAppleEaten(apple, cell);
  }
}
