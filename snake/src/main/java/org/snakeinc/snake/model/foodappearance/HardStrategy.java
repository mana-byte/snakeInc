package org.snakeinc.snake.model.foodappearance;

import java.util.Random;

import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.utils.Tuple2;

public class HardStrategy implements FoodStrategy {
  @Override
  public Tuple2<Integer, Integer> applyStrategy(Cell snakeHead) {
    Random random = new Random();

    int border = random.nextInt(0, 4);
    switch (border) {
      case 0: // Top border
        return new Tuple2<>(random.nextInt(0, GameParams.TILES_X), 0);
      case 1: // Bottom border
        return new Tuple2<>(random.nextInt(0, GameParams.TILES_X), GameParams.TILES_Y - 1);
      case 2: // Left border
        return new Tuple2<>(0, random.nextInt(0, GameParams.TILES_Y));
      case 3: // Right border
        return new Tuple2<>(GameParams.TILES_X - 1, random.nextInt(0, GameParams.TILES_Y));
    }
    return new Tuple2<>(0, 0); // Placeholder return value
  }
}
