package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HelloSnake extends ApplicationAdapter {
  private SpriteBatch batch;
  private Texture snakeImg;
  private Texture cherryImg;

  private Snake snake;
  private Cherry cherry;

  @Override
  public void create () {
    batch = new SpriteBatch();

    snakeImg = new Texture("snake.png");
    cherryImg = new Texture("cherry.png");

    snake = new Snake(snakeImg);
    cherry = new Cherry(cherryImg);
  }

  @Override
  public void render () {
    snake.act(Gdx.graphics.getDeltaTime());

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
}
