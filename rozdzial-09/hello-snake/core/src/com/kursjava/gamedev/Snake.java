package com.kursjava.gamedev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

enum MovementDirection { LEFT, UP, RIGHT, DOWN }

public class Snake {
  private static final int SEGMENT_WIDTH = 15;
  private static final int SEGMENT_HEIGHT = 15;

  private static final int TEXTURE_HEAD_START_INDEX = 0;
  private static final int TEXTURE_TAIL_START_INDEX = 4;
  private static final int TEXTURE_BODY_INDEX = 8;

  private static final int LAST_POSSIBLE_X_POSITION
      = Gdx.graphics.getWidth() - SEGMENT_WIDTH;
  private static final int LAST_POSSIBLE_Y_POSITION
      = Gdx.graphics.getHeight() - SEGMENT_HEIGHT;

  private final TextureRegion[] headTexture;
  private final TextureRegion bodyTexture;
  private final TextureRegion[] tailTexture;

  private final List<GridPoint2> snakeSegments;

  private MovementDirection direction;
  private MovementDirection tailDirection;
  private float timeElapsedSinceLastMove;
  private boolean canChangeDirection;

  public Snake(Texture texture) {
    headTexture = new TextureRegion[] {
        getTexturePart(texture, TEXTURE_HEAD_START_INDEX),
        getTexturePart(texture, TEXTURE_HEAD_START_INDEX + 1),
        getTexturePart(texture, TEXTURE_HEAD_START_INDEX + 2),
        getTexturePart(texture, TEXTURE_HEAD_START_INDEX + 3)
    };
    bodyTexture = getTexturePart(texture, TEXTURE_BODY_INDEX);
    tailTexture = new TextureRegion[] {
        getTexturePart(texture, TEXTURE_TAIL_START_INDEX),
        getTexturePart(texture, TEXTURE_TAIL_START_INDEX + 1),
        getTexturePart(texture, TEXTURE_TAIL_START_INDEX + 2),
        getTexturePart(texture, TEXTURE_TAIL_START_INDEX + 3)
    };

    snakeSegments = new ArrayList<>();
  }

  public void initialize() {
    timeElapsedSinceLastMove = 0;
    direction = MovementDirection.RIGHT;
    tailDirection = MovementDirection.RIGHT;

    snakeSegments.clear();
    snakeSegments.add(new GridPoint2(90, 30));
    snakeSegments.add(new GridPoint2(75, 30));
    snakeSegments.add(new GridPoint2(60, 30));
    snakeSegments.add(new GridPoint2(45, 30));
    snakeSegments.add(new GridPoint2(30, 30));
  }

  public List<GridPoint2> getSnakeSegmentPositions() {
    return snakeSegments;
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

    determineTailDirection();
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
    // narysuje ciało węża (bez głowy i ogona)
    for (int i = 1; i < snakeSegments.size() - 1; i++) {
      GridPoint2 body = snakeSegments.get(i);
      batch.draw(bodyTexture, body.x, body.y);
    }

    // narysuj ogon węża
    GridPoint2 tail = snakeSegments.get(tailIndex());
    batch.draw(
        tailTexture[tailDirection.ordinal()],
        tail.x,
        tail.y
    );

    // narysuj głowę węża
    batch.draw(
        headTexture[direction.ordinal()],
        head().x,
        head().y
    );
  }

  private TextureRegion getTexturePart(Texture texture, int index) {
    // obraz zawiera tekstury o tym samym rozmiarze,
    // w jednym rzędzie - argument index wyznacza,
    // którą teksturę z kolei chcemy pobrać
    return new TextureRegion(
        texture,
        index * SEGMENT_WIDTH,
        0,
        SEGMENT_WIDTH,
        SEGMENT_HEIGHT
    );
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

    GridPoint2 head = head();

    switch (direction) {
      case LEFT:
        head.x = (head.x == 0) ?
            LAST_POSSIBLE_X_POSITION : head.x - SEGMENT_WIDTH;
        break;
      case UP:
        head.y = (head.y == LAST_POSSIBLE_Y_POSITION)
            ? 0 : head.y + SEGMENT_HEIGHT;
        break;
      case RIGHT:
        head.x = (head.x == LAST_POSSIBLE_X_POSITION) ?
            0 : head.x + SEGMENT_WIDTH;
        break;
      case DOWN:
        head.y = (head.y == 0) ?
            LAST_POSSIBLE_Y_POSITION : head.y - SEGMENT_HEIGHT;
        break;
    }
  }

  private void determineTailDirection() {
    GridPoint2 segmentBeforeTail = snakeSegments.get(tailIndex() - 1);
    GridPoint2 tail = snakeSegments.get(tailIndex());

    // wyznaczając kierunek, w który ogon węża ma być skierowany,
    // musimy wziąc pod uwagę cztery szczególne przypadki -
    // po jednym na każdą krawędź okna, z której wąż może "wyjść"
    if (tail.x == 0 &&
        segmentBeforeTail.x == LAST_POSSIBLE_X_POSITION) {

      tailDirection = MovementDirection.LEFT;

    } else if (tail.x == LAST_POSSIBLE_X_POSITION &&
        segmentBeforeTail.x == 0) {

      tailDirection = MovementDirection.RIGHT;

    } else if (tail.y == 0 &&
        segmentBeforeTail.y == LAST_POSSIBLE_Y_POSITION) {

      tailDirection = MovementDirection.DOWN;

    } else if (tail.y == LAST_POSSIBLE_Y_POSITION &&
        segmentBeforeTail.y == 0) {

      tailDirection = MovementDirection.UP;

    }
    // "zwykłe" przypadki wyznaczania kierunku ogona węża
    // polegają na sprawdzeniu, z której strony ogona,
    // czyli ostatniego segmentu węża, jest przedostatni segment
    else if (segmentBeforeTail.x > tail.x) {
      tailDirection = MovementDirection.RIGHT;
    } else if (segmentBeforeTail.x < tail.x) {
      tailDirection = MovementDirection.LEFT;
    } else if (segmentBeforeTail.y > tail.y) {
      tailDirection = MovementDirection.UP;
    } else if (segmentBeforeTail.y < tail.y) {
      tailDirection = MovementDirection.DOWN;
    }
  }

  private GridPoint2 head() {
    return snakeSegments.get(0);
  }

  private int tailIndex() {
    return snakeSegments.size() - 1;
  }
}
