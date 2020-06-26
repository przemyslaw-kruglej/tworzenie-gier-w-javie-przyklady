package com.kursjava.gamedev;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

public class Cherry {
  private final GridPoint2 position;
  private final Texture texture;

  public Cherry(Texture texture) {
    this.texture = texture;
    this.position = new GridPoint2();
  }

  public void draw(Batch batch) {
    batch.draw(texture, position.x, position.y);
  }

  public void setPosition(GridPoint2 position) {
    this.position.set(position);
  }

  public GridPoint2 getPosition() {
    return this.position;
  }
}
