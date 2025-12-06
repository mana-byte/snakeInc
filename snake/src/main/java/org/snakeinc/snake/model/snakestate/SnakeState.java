package org.snakeinc.snake.model.snakestate;

import org.snakeinc.snake.model.Directions;
import org.snakeinc.snake.utils.Tuple2;

sealed interface SnakeState permits GoodHealth, Poisoned, PermanentlyDamaged {
  public Tuple2<Integer, Integer> movementModifier(Directions direction);

}
