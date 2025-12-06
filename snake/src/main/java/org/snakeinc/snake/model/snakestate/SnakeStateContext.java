package org.snakeinc.snake.model.snakestate;

import org.snakeinc.snake.model.Directions;
import org.snakeinc.snake.utils.Tuple2;

public class SnakeStateContext {
    private SnakeState state;
    public SnakeStateContext(SnakeState state) {
        this.state = state;
    }
    public void setState(SnakeState state) {
        this.state = state;
    }
    public SnakeState getState() {
      return this.state;
    }
    public Tuple2<Integer, Integer> movementModifier(Directions direction) {
        return state.movementModifier(direction);
    }
}
