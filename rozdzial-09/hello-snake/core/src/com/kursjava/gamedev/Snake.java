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
  private boolean canChangeDirection;

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
    if (canChangeDirection) {
      handleDirectionChange();
    }

    timeElapsedSinceLastMove += deltaTime;

    if (timeElapsedSinceLastMove >= 0.1) {
      timeElapsedSinceLastMove = 0;
      canChangeDirection = true;
      move();
    }
  }

  public boolean isCherryFound(GridPoint2 cherryPosition) {
    return head().equals(cherryPosition);
  }

  public void extendSnake() {
    snakeSegments.add(
        new GridPoint2(snakeSegments.get(snakeSegments.size() - 1))
    );
  }

  public boolean hasHitHimself() {
    for (int i = 1; i < snakeSegments.size(); i++) {
      if (snakeSegments.get(i).equals(head())) {
        return true;
      }
    }
    return false;
  }

  public void draw(Batch batch) {
    for (GridPoint2 pos : snakeSegments) {
      batch.draw(texture, pos.x, pos.y);
    }
  }

  private void handleDirectionChange() {
    MovementDirection newDirection = direction;

    if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) &&
        direction != MovementDirection.RIGHT) {
      newDirection = MovementDirection.LEFT;
    }

    if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) &&
        direction != MovementDirection.LEFT) {
      newDirection = MovementDirection.RIGHT;
    }

    if (Gdx.input.isKeyJustPressed(Input.Keys.UP) &&
        direction != MovementDirection.DOWN) {
      newDirection = MovementDirection.UP;
    }

    if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) &&
        direction != MovementDirection.UP) {
      newDirection = MovementDirection.DOWN;
    }

    if (direction != newDirection) {
      direction = newDirection;
      canChangeDirection = false;
    }
  }

  private void move() {
    // przesuń wszystkie segmenty poza głową
    for (int i = snakeSegments.size() - 1; i > 0; i--) {
      snakeSegments.get(i).set(snakeSegments.get(i - 1));
    }

    // przesuń głowę
    int segmentWidth = texture.getWidth();
    int segmentHeight = texture.getWidth();

    // pozycje X, Y ostatniego segmentu przed górną i prawą krawędzią okna
    int lastWindowSegmentX = Gdx.graphics.getWidth() - segmentWidth;
    int lastWindowSegmentY = Gdx.graphics.getHeight() - segmentHeight;

    GridPoint2 head = head();

    switch (direction) {
      case LEFT:
        head.x = (head.x == 0) ? lastWindowSegmentX : head.x - segmentWidth;
        break;
      case UP:
        head.y = (head.y == lastWindowSegmentY) ? 0 : head.y + segmentHeight;
        break;
      case RIGHT:
        head.x = (head.x == lastWindowSegmentX) ? 0 : head.x + segmentWidth;
        break;
      case DOWN:
        head.y = (head.y == 0) ? lastWindowSegmentY : head.y - segmentHeight;
        break;
    }
  }

  private GridPoint2 head() {
    return snakeSegments.get(0);
  }
}
