package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawingActorsExample extends ApplicationAdapter {
  private SpriteBatch batch;
  private Texture catImg;

  private MyActor normalActor;
  private MyActor transparentActor;
  private MyActor greenTintedActor;
  private MyActor biggerActor;
  private MyActor smallerActor;
  private MyActor rotatedActorLeftBottomOrigin;
  private MyActor rotatedActorCenter;

  @Override
  public void create () {
    batch = new SpriteBatch();
    catImg = new Texture("cat.png");

    normalActor = new MyActor(catImg, 25, 100);

    transparentActor = new MyActor(catImg, 85, 100);
    transparentActor.setColor(1, 1, 1, 0.5f);

    greenTintedActor = new MyActor(catImg, 145, 100);
    greenTintedActor.setColor(0, 1, 0, 1);

    biggerActor = new MyActor(catImg, 205, 100);
    biggerActor.setScale(2);

    smallerActor = new MyActor(catImg, 315, 100);
    smallerActor.setScale(0.5f);

    rotatedActorLeftBottomOrigin = new MyActor(catImg, 400, 100);
    rotatedActorLeftBottomOrigin.setRotation(180);

    rotatedActorCenter = new MyActor(catImg, 410, 100);
    rotatedActorCenter.setRotation(180);
    rotatedActorCenter.setOrigin(
        rotatedActorCenter.getWidth() / 2,
        rotatedActorCenter.getHeight() / 2
    );
  }

  @Override
  public void render () {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();

    normalActor.draw(batch, 1);
    transparentActor.draw(batch, 1);
    greenTintedActor.draw(batch, 1);
    biggerActor.draw(batch, 1);
    smallerActor.draw(batch, 1);
    rotatedActorLeftBottomOrigin.draw(batch, 1);
    rotatedActorCenter.draw(batch, 1);

    batch.end();
  }

  @Override
  public void dispose () {
    batch.dispose();
    catImg.dispose();
  }
}
