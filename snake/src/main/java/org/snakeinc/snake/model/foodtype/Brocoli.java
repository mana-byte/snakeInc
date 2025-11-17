package org.snakeinc.snake.model.foodtype;

import java.awt.Color;

public final class Brocoli extends Food {

  private final boolean isSteamed;
  private final Color specialColor;

  public Brocoli() {
    super();
    this.isSteamed = false;
    this.specialColor = Color.PINK;
    this.color = Color.GREEN;
  }

  public Brocoli(boolean isSteamed) {
    super();
    this.isSteamed = isSteamed;
    this.specialColor = Color.PINK;
    this.color = Color.GREEN;
  }

  public boolean isSteamed() {
    return isSteamed;
  }

  @Override
  public Color getColor() {
    return isSteamed ? specialColor : this.color;
  }

}
