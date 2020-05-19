package com.kursjava.gamedev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MovingActor extends Actor {
  private final Texture texture;
  private final int SPEED = 200;

  public MovingActor(Texture texture, float x, float y) {
    this.texture = texture;
    this.setPosition(x, y);
  }

  @Override
  public void act(float delta) {
    super.act(delta);

    float deltaX = 0, deltaY = 0;

    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      deltaX = SPEED * delta;
    } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      deltaX = -SPEED * delta;
    }

    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      deltaY = SPEED * delta;
    } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      deltaY = -SPEED * delta;
    }

    moveBy(deltaX, deltaY);
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    batch.draw(texture, getX(), getY());
  }
}
