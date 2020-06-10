package com.kursjava.gamedev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

enum MovementDirection { LEFT, UP, RIGHT, DOWN }

public class Snake {
  private final Texture texture;
  private final List<GridPoint2> snakeSegments;
  private MovementDirection direction;
  private float timeElapsedSinceLastMove;

  public Snake(Texture texture) {
    this.texture = texture;

    direction = MovementDirection.RIGHT;
    snakeSegments = new ArrayList<>();

    snakeSegments.add(new GridPoint2(90, 30));
    snakeSegments.add(new GridPoint2(75, 30));
    snakeSegments.add(new GridPoint2(60, 30));
    snakeSegments.add(new GridPoint2(45, 30));
    snakeSegments.add(new GridPoint2(30, 30));
  }

  public void act(float deltaTime) {
    handleDirectionChange();

    timeElapsedSinceLastMove += deltaTime;

    if (timeElapsedSinceLastMove >= 0.1) {
      timeElapsedSinceLastMove = 0;
      move();
    }
  }

  public void draw(Batch batch) {
    for (GridPoint2 pos : snakeSegments) {
      batch.draw(texture, pos.x, pos.y);
    }
  }

  private void handleDirectionChange() {
    if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) &&
        direction != MovementDirection.RIGHT) {
      direction = MovementDirection.LEFT;
    }

    if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) &&
        direction != MovementDirection.LEFT) {
      direction = MovementDirection.RIGHT;
    }

    if (Gdx.input.isKeyJustPressed(Input.Keys.UP) &&
        direction != MovementDirection.DOWN) {
      direction = MovementDirection.UP;
    }

    if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) &&
        direction != MovementDirection.UP) {
      direction = MovementDirection.DOWN;
    }
  }

  private void move() {
    for (int i = snakeSegments.size() - 1; i > 0; i--) {
      snakeSegments.get(i).set(snakeSegments.get(i - 1));
    }

    GridPoint2 head = snakeSegments.get(0);
    switch (direction) {
      case LEFT:
        head.x -= texture.getWidth();
        break;
      case UP:
        head.y += texture.getHeight();
        break;
      case RIGHT:
        head.x += texture.getWidth();
        break;
      case DOWN:
        head.y -= texture.getHeight();
        break;
    }
  }
}
