package org.snakeinc.snake.model.snaketype;

import java.util.ArrayList;
import org.snakeinc.snake.GameParams;
import org.snakeinc.snake.exception.OutOfPlayException;
import org.snakeinc.snake.exception.SelfCollisionException;
import org.snakeinc.snake.exception.SizeIsZeroException;
import org.snakeinc.snake.model.foodtype.Apple;
import org.snakeinc.snake.model.foodtype.Brocoli;
import org.snakeinc.snake.model.foodtype.Food;
import org.snakeinc.snake.model.foodtype.FoodEatenListener;
import org.snakeinc.snake.model.snakestate.Poisoned;
import org.snakeinc.snake.model.snakestate.SnakeState;
import org.snakeinc.snake.utils.IntegerWrapper;
import org.snakeinc.snake.utils.Tuple2;
import org.snakeinc.snake.model.Cell;
import org.snakeinc.snake.model.Directions;
import org.snakeinc.snake.model.Grid;

import java.awt.Color;

import lombok.Getter;

public sealed class Snake permits BoaConstrictor, Anaconda, Python {

  protected final ArrayList<Cell> body;
  protected final FoodEatenListener onFoodEatenListener;
  protected IntegerWrapper score;
  private final Grid grid;
  protected @Getter Color color;
  private int initSize;

  protected SnakeState state = SnakeState.GOOD_HEALTH;

  public Snake(FoodEatenListener listener, Grid grid) {
    this.body = new ArrayList<>();
    this.onFoodEatenListener = listener;
    this.grid = grid;
    this.color = Color.GREEN;
    this.initSize = GameParams.SNAKE_DEFAULT_SIZE;
    Cell head = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y);
    head.addSnake(this);
    body.add(head);
  }

  public Snake(FoodEatenListener listener, Grid grid, Integer initSize) {
    this.body = new ArrayList<>();
    this.onFoodEatenListener = listener;
    this.grid = grid;
    this.color = Color.GREEN;
    this.initSize = initSize;
    Cell head = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y);
    head.addSnake(this);
    body.add(head);
  }

  public void setScoreWrapper(IntegerWrapper score) {
    this.score = score;
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

  public void eat(Food food, Cell cell) {
    this.body.addFirst(cell);
    cell.addSnake(this);
    onFoodEatenListener.onFoodEaten(food, cell);
  }

  public void move(Directions direction) throws OutOfPlayException, SelfCollisionException, SizeIsZeroException {

    Tuple2<Integer, Integer> newHeadPos = state.movementModifier(direction);
    int x = getHead().getX() + newHeadPos.getFirst();
    int y = getHead().getY() + newHeadPos.getSecond();


    Cell newHead = grid.getTile(x, y);
    if (newHead == null) {
      throw new OutOfPlayException();
    }
    if (newHead.containsASnake()) {
      throw new SelfCollisionException();
    }

    if (this.getSize() == 0) {
      throw new SizeIsZeroException();
    }

    // Eat apple :
    if (newHead.containsFood()) {
      calculateScoreAndChangeState(newHead.getFood());
      this.eat(newHead.getFood(), newHead);
      return;
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

  private void calculateScoreAndChangeState(Food food) {
    if (food == null) {
      System.out.println("Food is null, cannot calculate score.");
      return;
    }
    if (this.score != null) {

      if (food instanceof Apple) {
        Apple apple = (Apple) food;
        if (apple.isPoisonous()) {
          this.score.value = this.score.value + GameParams.SCORE_SPECIAL_FOOD;
          if(state instanceof Poisoned) {
            this.state = SnakeState.PERMANENTLY_DAMAGED;
          } else {
            this.state = SnakeState.POISONED;
          }
          return;
        }
        this.score.value = this.score.value + GameParams.SCORE_APPLE;
      }

      if (food instanceof Brocoli) {
        Brocoli brocoli = (Brocoli) food;
        if (brocoli.isSteamed()) {
          this.score.value = this.score.value + GameParams.SCORE_SPECIAL_FOOD;
          return;
        }
        this.score.value = this.score.value + GameParams.SCORE_BROCOLI;
        if(this.state instanceof Poisoned) {
          this.state = SnakeState.GOOD_HEALTH;
        }
      }

    } else {
      System.out.println("Score wrapper is not set for this snake.");
    }
  }

}
