package org.snakeinc.snake.model;

import org.snakeinc.snake.model.foodtype.Food;
import org.snakeinc.snake.model.snaketype.Snake;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@EqualsAndHashCode
public class Cell {

  @Getter
  @Setter
  private int x;

  @Getter
  @Setter
  private int y;

  Snake snake;
  Food food;

  public Cell(int x, int y) {
    setX(x);
    setY(y);
  }

  public Food getFood() {
    return this.food;
  }

  public void addFood(Food food) {
    this.food = food;
  }

  public void addSnake(Snake snake) {
    this.snake = snake;
  }

  public void removeSnake() {
    this.snake = null;
  }

  public Snake getSnake() {
    return this.snake;
  }

  public void removeFood() {
    this.food = null;
  }

  public boolean containsASnake() {
    return this.snake != null;
  }

  public boolean containsFood() {
    return this.food != null;
  }

}
