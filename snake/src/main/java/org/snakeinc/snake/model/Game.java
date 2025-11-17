package org.snakeinc.snake.model;

import lombok.Getter;

import java.util.Random;

import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.SizeIsZeroException;
import org.snakeinc.snake.model.snaketype.Anaconda;
import org.snakeinc.snake.model.snaketype.Python;
import org.snakeinc.snake.model.snaketype.Snake;
import org.snakeinc.snake.model.snaketype.BoaConstrictor;

@Getter
public class Game {

  private final Grid grid;
  private final Basket basket;
  private final Snake snake;

  public Game() {
    grid = new Grid();
    basket = new Basket(grid);
    this.snake = generateSnakeObject();
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
        return new Python((apple, cell) -> basket.removeAppleInCell(apple, cell), grid);
      case 1:
        return new Anaconda((apple, cell) -> basket.removeAppleInCell(apple, cell), grid);
      default:
        return new BoaConstrictor((apple, cell) -> basket.removeAppleInCell(apple, cell), grid);
    }
  }
}
