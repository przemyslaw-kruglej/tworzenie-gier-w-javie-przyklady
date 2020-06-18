package com.kursjava.gamedev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayerActor extends Actor {
  private final static int ROTATION_SPEED = 180;

  private final TextureRegion textureRegion;
  private final Polygon collisionPolygon;
  private final Vector2 movementVector;

  public PlayerActor(
      Texture texture,
      float x,
      float y,
      float[] collisionVertices) {

    textureRegion = new TextureRegion(texture);
    collisionPolygon = new Polygon(collisionVertices);
    movementVector = new Vector2(100, 0).setAngle(90);

    this.setRotation(90); // wstępna rotacja postaci gracza
    this.setBounds(x, y, texture.getWidth(), texture.getHeight());
    // punkt na obszarze tekstury gracza, wokół którego
    // gracz powinien być obracany
    this.setOrigin(getWidth() / 2, getHeight() / 2);
  }

  @Override
  public void act(float delta) {
    super.act(delta);

    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      rotateBy(-ROTATION_SPEED * delta);
      movementVector.setAngle(getRotation());
    } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      rotateBy(ROTATION_SPEED * delta);
      movementVector.setAngle(getRotation());
    }

    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      moveBy(movementVector.x * delta, movementVector.y * delta);
    } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      moveBy(-movementVector.x * delta, -movementVector.y * delta);
    }
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    batch.draw(
        textureRegion,  // tekstura skojarzona z aktorem
        getX(),         // polozenie X na ekranie
        getY(),         // polozenie Y na ekranie
        getOriginX(),   // wspolrzedna X srodka obrotu
        getOriginY(),   // wspolrzedna Y srodka obrotu
        getWidth(),     // szerokosc tekstury
        getHeight(),    // wysokosc tekstury
        getScaleX(),    // skala szerokosci
        getScaleY(),    // skala wysokosci
        getRotation()   // rotacja w stopniach
    );
  }

  public void checkCollision(StationaryActor obj) {
    Polygon otherPolygon = obj.getCollisionPolygon();
    Polygon thisPolygon = collisionPolygon;
    thisPolygon.setPosition(getX(), getY());
    thisPolygon.setOrigin(this.getOriginX(), this.getOriginY());
    thisPolygon.setRotation(this.getRotation());

    if (!thisPolygon.getBoundingRectangle().overlaps(
        otherPolygon.getBoundingRectangle())
    ) {
      return;
    }

    Intersector.MinimumTranslationVector mtv =
        new Intersector.MinimumTranslationVector();

    if (Intersector.overlapConvexPolygons(
        collisionPolygon,
        obj.getCollisionPolygon(),
        mtv)
    ) {
      if (obj.canBeCollected()) {
        obj.setVisible(false);
      } else {
        this.moveBy(
            mtv.normal.x * mtv.depth,
            mtv.normal.y * mtv.depth
        );
      }
    }
  }
}
