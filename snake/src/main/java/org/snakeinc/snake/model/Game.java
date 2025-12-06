package org.snakeinc.snake.model;

import java.util.Random;

import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.SizeIsZeroException;
import org.snakeinc.snake.model.snaketype.Anaconda;
import org.snakeinc.snake.model.snaketype.BoaConstrictor;
import org.snakeinc.snake.model.snaketype.Python;
import org.snakeinc.snake.model.snaketype.Snake;
import org.snakeinc.snake.utils.IntegerWrapper;

import lombok.Getter;
import lombok.Setter;


@Getter
public class Game {

  private final Grid grid;
  private final Basket basket;
  private final Snake snake;

  @Getter
  private IntegerWrapper score;

  public Game() {
    this.score = new IntegerWrapper(0);
    grid = new Grid();
    basket = new Basket(grid);
    this.snake = generateSnakeObject();
    this.snake.setScoreWrapper(score);
    basket.refillIfNeeded(1);
  }

  public void iterate(Directions direction) throws OutOfPlayException, SelfCollisionException, SizeIsZeroException {
    snake.move(direction);
    basket.refillIfNeeded(1);
  }

  private Snake generateSnakeObject() {
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
