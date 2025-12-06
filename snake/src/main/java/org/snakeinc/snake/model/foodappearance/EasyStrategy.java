package org.snakeinc.snake.model.foodappearance;

import java.util.Random;

import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.utils.Tuple2;

public class EasyStrategy implements FoodStrategy {
  @Override
  public Tuple2<Integer, Integer> applyStrategy(Cell snakeHead) {
    int headX = snakeHead.getX();
    int headY = snakeHead.getY();
    Random random = new Random();

    int foodX, foodY;
    do {
      foodX = headX + random.nextInt(-GameParams.EASY_STRATEGY_SQUARE_SIZE, GameParams.EASY_STRATEGY_SQUARE_SIZE);
      foodY = headY + random.nextInt(-GameParams.EASY_STRATEGY_SQUARE_SIZE, GameParams.EASY_STRATEGY_SQUARE_SIZE);
    } while (foodX < 0 || foodX >= GameParams.TILES_X || foodY < 0 || foodY >= GameParams.TILES_Y);

    Tuple2<Integer, Integer> tuple2 = new Tuple2<>(foodX, foodY);
	return tuple2;
  }
}
