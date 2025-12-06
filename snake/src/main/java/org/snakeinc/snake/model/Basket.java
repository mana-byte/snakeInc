package org.snakeinc.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.model.foodappearance.EasyStrategy;
import org.snakeinc.snake.model.foodappearance.FoodContext;
import org.snakeinc.snake.model.foodappearance.HardStrategy;
import org.snakeinc.snake.model.foodappearance.RandomStrategy;
import org.snakeinc.snake.model.foodtype.Food;
import org.snakeinc.snake.utils.Tuple2;

import lombok.Data;

@Data
public class Basket {

  private Grid grid;
  private List<Food> foods;
  private final FoodContext foodContext = new FoodContext(new EasyStrategy());

  public Basket(Grid grid) {
    foods = new ArrayList<>();
    this.grid = grid;
  }

  public void addFood(Cell cell, Cell snakeHead) {
    // Generates a random cell if null and makes sure it is not on the snake
    Random random = new Random();
    if (cell == null) {
      Tuple2<Integer, Integer> coords = foodContext.executeStrategy(snakeHead);
      cell = grid.getTile(coords.getFirst(), coords.getSecond());
      if (cell.containsASnake()) {
        addFood(null, snakeHead);
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

  private void refill(int nFoods, Cell snakeHead) {
    for (int i = 0; i < nFoods; i++) {
      addFood(null, snakeHead);
    }
  }

  public void refillIfNeeded(int nFoods, Cell snakeHead) {
    int missingFood = nFoods - foods.size();
    if (missingFood > 0) {
      refill(missingFood, snakeHead);
    }
  }

}
