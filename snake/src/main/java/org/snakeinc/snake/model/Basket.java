package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.foodtype.Food;

import lombok.Data;

@Data
public class Basket {

  private Grid grid;
  private List<Food> foods;

  public Basket(Grid grid) {
    foods = new ArrayList<>();
    this.grid = grid;
  }

  public void addFood(Cell cell) {
    // Generates a random cell if null and makes sure it is not on the snake
    Random random = new Random();
    if (cell == null) {
      cell = grid.getTile(random.nextInt(0, GameParams.TILES_X), random.nextInt(0, GameParams.TILES_Y));
      if (cell.containsASnake()) {
        addFood(null);
        return;
      }
    }
    Food food = Food.createFoodInCell(cell);
    foods.add(food);
  }

  public void removeFoodInCell(Food food, Cell cell) {
    cell.removeFood();
    foods.remove(food);
  }

  public boolean isEmpty() {
    return foods.isEmpty();
  }

  private void refill(int nFoods) {
    for (int i = 0; i < nFoods; i++) {
      addFood(null);
    }
  }

  public void refillIfNeeded(int nFoods) {
    int missingFood = nFoods - foods.size();
    if (missingFood > 0) {
      refill(missingFood);
    }
  }

}
