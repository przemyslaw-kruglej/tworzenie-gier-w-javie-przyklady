package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ActorExample extends ApplicationAdapter {
  private SpriteBatch batch;
  private Texture catImg;
  private MovingActor cat;

  @Override
  public void create () {
    batch = new SpriteBatch();
    catImg = new Texture("cat.png");

    cat = new MovingActor(catImg, 10, 10);
  }

  @Override
  public void render () {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    cat.act(Gdx.graphics.getDeltaTime());

    batch.begin();
    cat.draw(batch, 1);
    batch.end();
  }

  @Override
  public void dispose () {
    batch.dispose();
    catImg.dispose();
  }
}
