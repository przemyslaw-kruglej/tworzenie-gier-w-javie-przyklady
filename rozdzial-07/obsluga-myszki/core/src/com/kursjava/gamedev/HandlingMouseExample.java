package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HandlingMouseExample extends ApplicationAdapter {
  private SpriteBatch batch;
  private Texture cat;

  private int catX, catY;
  private boolean movingCat;
  private float lastMouseX, lastMouseY;

  @Override
  public void create () {
    batch = new SpriteBatch();
    cat = new Texture("cat.png");
  }

  @Override
  public void render () {
    if (movingCat) {
      catX += Gdx.input.getX() - lastMouseX;
      catY += (300 - Gdx.input.getY()) - lastMouseY;
      lastMouseX = Gdx.input.getX();
      lastMouseY = 300 - Gdx.input.getY();
    }

    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
      lastMouseX = Gdx.input.getX();
      lastMouseY = 300 - Gdx.input.getY();

      if (lastMouseX >= catX && lastMouseX <= catX + cat.getWidth()
      && lastMouseY >= catY && lastMouseY <= catY + cat.getHeight()) {
        movingCat = true;
      }
    } else if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
      movingCat = false;
    }

    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    batch.draw(cat, catX, catY);
    batch.end();
  }

  @Override
  public void dispose () {
    batch.dispose();
    cat.dispose();
  }
}
