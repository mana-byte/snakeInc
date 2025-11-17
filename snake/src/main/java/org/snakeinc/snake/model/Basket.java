package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Data;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.Snake;

@Data
public class Basket {

  private Grid grid;
  private List<Apple> apples;

  public Basket(Grid grid) {
    apples = new ArrayList<>();
    this.grid = grid;
  }

  public void addApple(Cell cell) {
    // Generates a random cell if null and makes sure it is not on the snake 
    Random random = new Random();
    if (cell == null) {
      cell = grid.getTile(random.nextInt(0, GameParams.TILES_X), random.nextInt(0, GameParams.TILES_Y));
      if (cell.containsASnake()) {
        addApple(null);
        return;
      }
    }
    Apple apple = AppleFactory.createAppleInCell(cell);
    apples.add(apple);
  }

  public void removeAppleInCell(Apple apple, Cell cell) {
    cell.removeApple();
    apples.remove(apple);
  }

  public boolean isEmpty() {
    return apples.isEmpty();
  }

  private void refill(int nApples) {
    for (int i = 0; i < nApples; i++) {
      addApple(null);
    }
  }

  public void refillIfNeeded(int nApples) {
    int missingApple = nApples - apples.size();
    if (missingApple > 0) {
      refill(missingApple);
    }
  }

}
