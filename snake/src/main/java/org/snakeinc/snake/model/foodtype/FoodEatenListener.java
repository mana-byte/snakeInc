package org.snakeinc.snake.model.foodtype;

import org.snakeinc.snake.model.Cell;

public interface FoodEatenListener {

  void onFoodEaten(Food food, Cell cell);

}
