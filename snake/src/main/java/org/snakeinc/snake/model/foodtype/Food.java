package org.snakeinc.snake.model.foodtype;

import java.awt.Color;

import org.snakeinc.snake.model.Cell;

import lombok.Getter;

@Getter
public sealed class Food permits Apple, Brocoli {

  protected Color color;

  public Food() {
    this.color = Color.WHITE;
  }

  public static Food createFoodInCell(Cell cell) {
    Food food;
    boolean isSpecial = Math.random() < 0.5 ? true : false;
    if (Math.random() < 0.5) {
      food = new Apple(isSpecial);
      cell.addFood((Apple) food);
    } else {
      food = new Brocoli(isSpecial);
      cell.addFood((Brocoli) food);
    }
    return food;

  }

  public Color getColor() {
    return color;
  }
}
