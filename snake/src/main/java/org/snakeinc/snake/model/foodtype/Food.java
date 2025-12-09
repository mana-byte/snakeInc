package org.snakeinc.snake.model.foodtype;

import java.awt.Color;

import org.snakeinc.snake.model.Cell;

import lombok.Getter;
import lombok.Setter;

@Getter
public sealed class Food permits Apple, Brocoli {

  protected Color color;

  @Setter
  @Getter
  protected Cell cell;

  private boolean movedOnce = false;
  public Food() {
    this.color = Color.WHITE;
  }

  public static Food createFoodInCell(Cell cell) {
    Food food;
    boolean isSpecial = Math.random() < 0.5 ? true : false;
    if (Math.random() < 0.5) {
      food = new Apple(isSpecial);
    } else {
      food = new Brocoli(isSpecial);
    }
    cell.addFood(food);
    food.setCell(cell);
    return food;

  }

  public Color getColor() {
    return color;
  }

  public void moveFoodToCell(Cell newCell) {
    this.cell.removeFood();
    this.setCell(newCell);
    newCell.addFood(this);
    this.movedOnce = true;
  }

  public boolean GetMovedOnce() {
    return this.movedOnce;
  }
}
