package org.snakeinc.snake.model.foodappearance;

import java.util.Random;

import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.utils.Tuple2;

public class RandomStrategy implements FoodStrategy {
  @Override
  public Tuple2<Integer, Integer> applyStrategy(Cell snakeHead) {
    Random random = new Random();
    return new Tuple2<>(random.nextInt(0, GameParams.TILES_X), random.nextInt(0, GameParams.TILES_Y));
  }
}
