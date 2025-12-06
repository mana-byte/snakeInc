package org.snakeinc.snake.model.foodappearance;

import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.utils.Tuple2;

public class FoodContext {
  private FoodStrategy strategy;

  public FoodContext(FoodStrategy strategy) {
    this.strategy = strategy;
  }

  public void setStrategy(FoodStrategy strategy) {
    this.strategy = strategy;
  }

  public Tuple2<Integer, Integer> executeStrategy(Cell head) {
    return strategy.applyStrategy(head);
  }
}
