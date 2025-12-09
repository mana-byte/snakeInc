package org.snakeinc.snake.model;

import java.awt.List;
import java.util.ArrayList;

import org.snakeinc.snake.model.foodtype.Food;
import org.snakeinc.snake.utils.Tuple2;

public class CrazyBasket extends Basket {

  public CrazyBasket(Grid grid) {
    super(grid);
  }

  public void moveFoodWhenSnakeAround(Cell snakeHead) {
    for (Food food : foods) {
      if ((Math.abs(food.getCell().getX() - snakeHead.getX()) <= 2) &&
          (Math.abs(food.getCell().getY() - snakeHead.getY()) <= 2)) {

        if (food.GetMovedOnce()) continue;
        Tuple2<Integer, Integer> coords = foodContext.executeStrategy(snakeHead);
        Cell newCell = grid.getTile(coords.getFirst(), coords.getSecond());
        if (!newCell.containsASnake() && newCell != food.getCell()) {
          food.moveFoodToCell(newCell);
          break;
        }
      }
    }
  }
}
