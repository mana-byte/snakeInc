package org.snakeinc.snake.model.snaketype;

import org.snakeinc.snake.model.Snake;
import org.snakeinc.snake.model.AppleEatenListener;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import java.awt.Color;

import org.snakeinc.snake.model.Apple;

public class BoaConstrictor extends Snake {
  public BoaConstrictor(AppleEatenListener listener, Grid grid) {
    super(listener, grid);
    this.color = Color.BLUE;
  }

  @Override
  public void eat(Apple apple, Cell cell) {
    this.body.getLast().removeSnake();
    this.body.removeLast();
    onAppleEatenListener.onAppleEaten(apple, cell);
  }
}
