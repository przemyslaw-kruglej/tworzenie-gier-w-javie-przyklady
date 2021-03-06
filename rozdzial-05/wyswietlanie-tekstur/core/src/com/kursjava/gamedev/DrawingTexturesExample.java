package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawingTexturesExample extends ApplicationAdapter {
  public static final int SCREEN_WIDTH = 500;
  public static final int SCREEN_HEIGHT = 400;

  private SpriteBatch batch;
  private Texture catImg;

  @Override
  public void create () {
    batch = new SpriteBatch();
    catImg = new Texture("cat.png");
  }

  @Override
  public void render () {
    Gdx.gl.glClearColor(0.741f, 0.874f, 0.976f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    batch.begin();

    // obraz w lewym, dolnym rogu
    batch.draw(catImg, 0, 0);

    // obraz w prawym, gornym rogu
    batch.draw(
        catImg,
        SCREEN_WIDTH - catImg.getWidth(),
        SCREEN_HEIGHT - catImg.getHeight()
    );

    // obraz w lewym, gornym rogu
    batch.draw(catImg, 0, SCREEN_HEIGHT - catImg.getHeight());

    // obraz w prawym, dolnym rogu
    batch.draw(catImg, SCREEN_WIDTH - catImg.getWidth(), 0);

    // obraz w srodku okna
    batch.draw(
        catImg,
        SCREEN_WIDTH / 2 - catImg.getWidth() / 2,
        SCREEN_HEIGHT / 2 - catImg.getHeight() / 2
    );

    batch.end();
  }

  @Override
  public void dispose () {
    batch.dispose();
    catImg.dispose();
  }
}
