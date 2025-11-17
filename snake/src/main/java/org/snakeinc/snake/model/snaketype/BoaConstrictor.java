package org.snakeinc.snake.model.snaketype;

import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.foodtype.Brocoli;
import org.snakeinc.snake.model.foodtype.Food;
import org.snakeinc.snake.model.foodtype.FoodEatenListener;

import java.awt.Color;

public final class BoaConstrictor extends Snake {
  public BoaConstrictor(FoodEatenListener listener, Grid grid) {
    super(listener, grid);
    this.color = Color.BLUE;
  }

  @Override
  public void eat(Food food, Cell cell) {
    if (!(food instanceof Brocoli)) {
      this.body.getLast().removeSnake();
      this.body.removeLast();
    }
    onFoodEatenListener.onFoodEaten(food, cell);
  }
}
