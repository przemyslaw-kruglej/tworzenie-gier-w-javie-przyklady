package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleJigsawPuzzleGame extends ApplicationAdapter {
  public static final int WINDOW_WIDTH = 800;
  public static final int WINDOW_HEIGHT = 450;

  private Texture puzzleImg;
  private SpriteBatch batch;
  private int puzzleOriginX;
  private int puzzleOriginY;

  @Override
  public void create () {
    puzzleImg = new Texture("kitty.png");
    puzzleOriginX = WINDOW_WIDTH / 2 - puzzleImg.getWidth() / 2;
    puzzleOriginY = WINDOW_HEIGHT / 2 - puzzleImg.getHeight() / 2;

    batch = new SpriteBatch();
  }

  @Override
  public void render () {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    batch.draw(puzzleImg, puzzleOriginX, puzzleOriginY);
    batch.end();
  }

  @Override
  public void dispose () {
    batch.dispose();
    puzzleImg.dispose();
  }
}
