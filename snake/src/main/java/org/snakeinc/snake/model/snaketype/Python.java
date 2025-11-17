package org.snakeinc.snake.model.snaketype;

import java.awt.Color;

import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Grid;
import org.snakeinc.snake.model.foodtype.Brocoli;
import org.snakeinc.snake.model.foodtype.Food;
import org.snakeinc.snake.model.foodtype.FoodEatenListener;

public final class Python extends Snake {
  public Python(FoodEatenListener listener, Grid grid) {
    super(listener, grid);
    this.color = Color.GREEN;
  }

  @Override
  public void eat(Food food, Cell cell) {
    if (food instanceof Brocoli) {
      this.body.getLast().removeSnake();
      this.body.removeLast();
      this.body.getLast().removeSnake();
      this.body.removeLast();
      this.body.getLast().removeSnake();
      this.body.removeLast();

    }
    onFoodEatenListener.onFoodEaten(food, cell);
  }
}
