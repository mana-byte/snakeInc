package org.snakeinc.snake.model;

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
  Apple apple;

  public Cell(int x, int y) {
    setX(x);
    setY(y);
  }

  public void addApple(Apple apple) {
    this.apple = apple;
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

  public void removeApple() {
    this.apple = null;
  }

  public boolean containsASnake() {
    return this.snake != null;
  }

  public boolean containsAnApple() {
    return this.apple != null;
  }

}
