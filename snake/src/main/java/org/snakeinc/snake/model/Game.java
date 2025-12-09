package org.snakeinc.snake.model;

import java.util.Random;

import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.SizeIsZeroException;
import org.snakeinc.snake.model.snaketype.Anaconda;
import org.snakeinc.snake.model.snaketype.BoaConstrictor;
import org.snakeinc.snake.model.snaketype.Python;
import org.snakeinc.snake.model.snaketype.Snake;
import org.snakeinc.snake.utils.IntegerWrapper;

import lombok.Getter;

@Getter
public class Game {

  private final Grid grid;
  private final Basket basket;
  private final Snake snake;
  private IntegerWrapper score;

  public Game() {
    Random random = new Random();
    this.score = new IntegerWrapper(0);
    grid = new Grid();
    basket = new CrazyBasket(grid);
    this.snake = generateSnakeObjectAtRandom();
    this.snake.setScoreWrapper(this.score);

    this.basket.setStrategyDifficulty(random.nextInt(0, 2));

    basket.refillIfNeeded(GameParams.NUMBER_OF_FOODS, snake.getHead());
  }

  public void iterate(Directions direction) throws OutOfPlayException, SelfCollisionException, SizeIsZeroException {
    snake.move(direction);
    basket.refillIfNeeded(GameParams.NUMBER_OF_FOODS, snake.getHead());
    if (basket instanceof CrazyBasket) {
      ((CrazyBasket) basket).moveFoodWhenSnakeAround(snake.getHead());
    }
  }

  private Snake generateSnakeObjectAtRandom() {
    Random random = new Random();
    int randint = random.nextInt(3);
    switch (randint) {
      case 0:
        return new Python((food, cell) -> basket.removeFoodInCell(food, cell), grid);
      case 1:
        return new Anaconda((food, cell) -> basket.removeFoodInCell(food, cell), grid);
      default:
        return new BoaConstrictor((food, cell) -> basket.removeFoodInCell(food, cell), grid);
    }
  }
}
