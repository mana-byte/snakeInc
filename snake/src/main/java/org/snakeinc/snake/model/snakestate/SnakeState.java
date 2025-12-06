package org.snakeinc.snake.model.snakestate;

import org.snakeinc.snake.model.Directions;
import org.snakeinc.snake.utils.Tuple2;

public sealed class SnakeState permits GoodHealth, Poisoned, PermanentlyDamaged {

  public static final SnakeState GOOD_HEALTH = new GoodHealth();
  public static final SnakeState POISONED = new Poisoned();
  public static final SnakeState PERMANENTLY_DAMAGED = new PermanentlyDamaged();

  public Tuple2<Integer, Integer> movementModifier(Directions direction) {
    int x = 0;
    int y = 0;
    switch (direction) {
      case Directions.UP:
        y--;
        break;
      case Directions.DOWN:
        y++;
        break;
      case Directions.LEFT:
        x--;
        break;
      case Directions.RIGHT:
        x++;
        break;
    }
    return new Tuple2<>(x, y);
  }

}
