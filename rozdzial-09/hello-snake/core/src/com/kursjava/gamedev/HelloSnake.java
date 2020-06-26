package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;

import java.util.List;

public class HelloSnake extends ApplicationAdapter {
  private SpriteBatch batch;
  private Texture snakeImg;
  private Texture cherryImg;

  private Snake snake;
  private Cherry cherry;
  private PositionRandomizer positionRandomizer;
  private boolean gameOver;

  @Override
  public void create () {
    batch = new SpriteBatch();

    snakeImg = new Texture("snake.png");
    cherryImg = new Texture("cherry.png");

    snake = new Snake(snakeImg);
    cherry = new Cherry(cherryImg);
    positionRandomizer = new PositionRandomizer();

    initializeNewGame();
  }

  @Override
  public void render () {
    updateGame();

    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();

    cherry.draw(batch);
    snake.draw(batch);

    batch.end();
  }

  @Override
  public void dispose () {
    batch.dispose();
    snakeImg.dispose();
    cherryImg.dispose();
  }

  private void initializeNewGame() {
    snake.initialize();
    randomizeCherryPosition();
    gameOver = false;
  }

  private void randomizeCherryPosition() {
    List<GridPoint2> occupiedPositions =
        snake.getSnakeSegmentPositions();

    try {
      cherry.setPosition(
          positionRandomizer.getRandomAvailablePosition(
              occupiedPositions
          )
      );
    // w rzadkim przypadku, w którym gracz będzie grał
    // tak długo, że uda mu się zająć wężem cały ekran,
    // nie będzie już miejsca dla jedzenia - będzie to
    // równoważne z końcem gry
    } catch (NoMorePositionsAvailable e) {
      gameOver = true;
    }
  }

  private void updateGame() {
    if (!gameOver) {
      snake.act(Gdx.graphics.getDeltaTime());

      if (snake.isCherryFound(cherry.getPosition())) {
        snake.extendSnake();
        randomizeCherryPosition();
      }

      if (snake.hasHitHimself()) {
        gameOver = true;
      }
    } else {
      if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
        initializeNewGame();
      }
    }
  }
}
