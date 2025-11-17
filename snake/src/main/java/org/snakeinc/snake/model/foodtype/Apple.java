package org.snakeinc.snake.model.foodtype;

import java.awt.Color;

public final class Apple extends Food {
  private final boolean isPoisonous;
  private final Color specialColor;

  public Apple() {
    super();
    this.isPoisonous = false;
    this.specialColor = Color.MAGENTA;
    this.color = Color.RED;
  }

  public Apple(boolean isPoisonous) {
    super();
    this.isPoisonous = isPoisonous;
    this.specialColor = Color.MAGENTA;
    this.color = Color.RED;
  }

  public boolean isPoisonous() {
    return isPoisonous;
  }

  @Override
  public Color getColor() {
    return isPoisonous ? specialColor : this.color;
  }

}
