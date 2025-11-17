package org.snakeInc.snake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.model.Game;
import org.snakeinc.snake.model.Directions;

public class SnakeTest {

  Game game = new Game();

  @Test
  public void snakeEatApplesAfterMove_ReturnsCorrectBodySize() throws OutOfPlayException, SelfCollisionException {
    game.getBasket().addApple(game.getGrid().getTile(5, 4));
    game.getSnake().move(Directions.UP);
    Assertions.assertEquals(2, game.getSnake().getSize());
  }

  @Test
  void snakeMovesUp_ReturnCorrectHead() throws OutOfPlayException, SelfCollisionException {
    game.getSnake().move(Directions.UP);
    Assertions.assertEquals(5, game.getSnake().getHead().getX());
    Assertions.assertEquals(4, game.getSnake().getHead().getY());
  }

  @Test
  void snakeMovesOutOfPlay_ThrowsOutOfPlayException() throws OutOfPlayException, SelfCollisionException {
    for (int i = 0; i < 10; i++) {
      try {
        game.getSnake().move(Directions.UP);
      } catch (OutOfPlayException e) {
        Assertions.assertTrue(true);
        return;
      }
    }
  }

  @Test
  void snakeSelfCollide_ThrowsSelfCollisionException() throws OutOfPlayException, SelfCollisionException {
    for (int i = 1; i < 6; i++) {
      game.getBasket().addApple(game.getGrid().getTile(5 + i, 5));
      game.getSnake().move(Directions.RIGHT);
    }
    game.getSnake().move(Directions.DOWN);
    game.getSnake().move(Directions.LEFT);
    try {
      game.getSnake().move(Directions.UP);
    } catch (SelfCollisionException e) {
      Assertions.assertTrue(true);
      return;
    }
  }

}
