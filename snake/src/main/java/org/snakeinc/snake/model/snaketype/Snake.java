package org.snakeinc.snake.model.snaketype;

import java.util.ArrayList;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.SizeIsZeroException;
import org.snakeinc.snake.model.Apple;
import org.snakeinc.snake.model.AppleEatenListener;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Directions;
import org.snakeinc.snake.model.Grid;

import java.awt.Color;

import lombok.Getter;

public sealed class Snake permits BoaConstrictor, Anaconda, Python {

  protected final ArrayList<Cell> body;
  protected final AppleEatenListener onAppleEatenListener;
  private final Grid grid;
  protected @Getter Color color;
  private int initSize;

  public Snake(AppleEatenListener listener, Grid grid) {
    this.body = new ArrayList<>();
    this.onAppleEatenListener = listener;
    this.grid = grid;
    this.color = Color.GREEN;
    this.initSize = GameParams.SNAKE_DEFAULT_SIZE;
    Cell head = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y);
    head.addSnake(this);
    body.add(head);
  }

  public Snake(AppleEatenListener listener, Grid grid, Integer initSize) {
    this.body = new ArrayList<>();
    this.onAppleEatenListener = listener;
    this.grid = grid;
    this.color = Color.GREEN;
    this.initSize = initSize;
    Cell head = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y);
    head.addSnake(this);
    body.add(head);
  }

  public int getSize() {
    return body.size();
  }

  public Cell getHead() {
    if (body.isEmpty()) {
      return new Cell(-1, -1);
    }
    return body.getFirst();
  }

  public void eat(Apple apple, Cell cell) {
    this.body.addFirst(cell);
    cell.addSnake(this);
    onAppleEatenListener.onAppleEaten(apple, cell);
  }

  public void move(Directions direction) throws OutOfPlayException, SelfCollisionException, SizeIsZeroException {
    int x = getHead().getX();
    int y = getHead().getY();
    switch (direction) {
      case Directions.UP:
        y--;
        break;
      case Directions.DOWN:
        y++;
        break;
      case Directions.LEFT:
        x--;
        break;
      case Directions.RIGHT:
        x++;
        break;
    }
    Cell newHead = grid.getTile(x, y);
    if (newHead == null) {
      throw new OutOfPlayException();
    }
    if (newHead.containsASnake()) {
      throw new SelfCollisionException();
    }

    // Eat apple :
    if (newHead.containsAnApple()) {
      this.eat(newHead.getApple(), newHead);
      return;
    }

    if (this.getSize() == 0) {
      throw new SizeIsZeroException();
    }

    // The snake did not eat :
    newHead.addSnake(this);
    body.addFirst(newHead);

    if (this.initSize < 2) {
      body.getLast().removeSnake();
      body.removeLast();
    } else
      this.initSize--;

  }

}
