package org.snakeinc.snake.model.snaketype;

import org.snakeinc.snake.model.AppleEatenListener;
import org.snakeinc.snake.model.Grid;
import java.awt.Color;

public final class Anaconda extends Snake {
  public Anaconda(AppleEatenListener listener, Grid grid) {
    super(listener, grid);
    this.color = Color.GRAY;
  }
}
