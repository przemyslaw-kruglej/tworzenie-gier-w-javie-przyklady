package com.kursjava.gamedev;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class StationaryActor extends Actor {
  private final Texture texture;
  private final boolean canBeCollected;
  private final Polygon collisionPolygon;

  public StationaryActor(
      Texture texture,
      boolean canBeCollected,
      float x,
      float y,
      float[] collisionVertices) {

    this.texture = texture;
    this.canBeCollected = canBeCollected;
    this.setBounds(x, y, texture.getWidth(), getHeight());

    this.collisionPolygon = new Polygon(collisionVertices);
    this.collisionPolygon.setPosition(x, y);
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    if (isVisible()) {
      batch.draw(texture, getX(), getY());
    }
  }

  public Polygon getCollisionPolygon() {
    return collisionPolygon;
  }

  public boolean canBeCollected() {
    return canBeCollected;
  }
}
