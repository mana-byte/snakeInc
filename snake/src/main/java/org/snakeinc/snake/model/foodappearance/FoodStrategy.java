package org.snakeinc.snake.model.foodappearance;

import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.utils.Tuple2;

interface FoodStrategy {
  Tuple2<Integer, Integer> applyStrategy(Cell snakeHead);
}
