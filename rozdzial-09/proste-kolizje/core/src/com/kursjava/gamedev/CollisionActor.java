package com.kursjava.gamedev;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CollisionActor extends Actor {
  private final Texture texture;
  private final Rectangle collisionRectangle;

  public CollisionActor(Texture texture, float x, float y) {
    this.texture = texture;
    this.setBounds(x, y, texture.getWidth(), texture.getHeight());
    this.collisionRectangle = new Rectangle(x, y, getWidth(), getHeight());
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    batch.draw(texture, (int) getX(), (int) getY());
  }

  @Override
  public void moveBy(float x, float y) {
    super.moveBy(x, y);

    collisionRectangle.setPosition((int) getX(), (int) getY());
  }

  public boolean checkCollision(CollisionActor other) {
    return collisionRectangle.overlaps(
        other.collisionRectangle
    );
  }
}
