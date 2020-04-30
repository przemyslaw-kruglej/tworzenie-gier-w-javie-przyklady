package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HandlingKeyboardExample extends ApplicationAdapter {
  private SpriteBatch batch;
  private Texture cat;

  private static final int CAT_SPEED = 200;
  private float x, y;

  @Override
  public void create () {
    batch = new SpriteBatch();
    cat = new Texture("cat.png");
  }

  @Override
  public void render () {
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      x += CAT_SPEED * Gdx.graphics.getDeltaTime();
    } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      x -= CAT_SPEED * Gdx.graphics.getDeltaTime();
    }

    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      y += CAT_SPEED * Gdx.graphics.getDeltaTime();
    } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      y -= CAT_SPEED * Gdx.graphics.getDeltaTime();
    }

    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    batch.draw(cat, x, y);
    batch.end();
  }

  @Override
  public void dispose () {
    batch.dispose();
    cat.dispose();
  }
}
