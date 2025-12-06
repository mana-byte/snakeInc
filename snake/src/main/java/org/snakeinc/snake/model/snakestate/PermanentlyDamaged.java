package org.snakeinc.snake.model.snakestate;

import org.snakeinc.snake.model.Directions;
import org.snakeinc.snake.utils.Tuple2;

public final class PermanentlyDamaged implements SnakeState {
  public PermanentlyDamaged() {
    super();
  }

  @Override
  public Tuple2<Integer, Integer> movementModifier(Directions direction) {
    int x = 0;
    int y = 0;
    switch (direction) {
      case Directions.DOWN:
        y--;
        break;
      case Directions.UP:
        y++;
        break;
      case Directions.RIGHT:
        x--;
        break;
      case Directions.LEFT:
        x++;
        break;
    }
    return new Tuple2<>(x, y);
  }
}
