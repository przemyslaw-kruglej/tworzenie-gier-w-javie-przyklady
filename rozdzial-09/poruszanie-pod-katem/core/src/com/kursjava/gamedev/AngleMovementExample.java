package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class AngleMovementExample extends ApplicationAdapter {
  private SpriteBatch batch;
  private Texture img;

  private int imgX = 225, imgY = 175;

  private Vector2 goLeftVector, goRightVector;
  private Vector2 goUpVector, goDownVector;
  private Vector2 go30DegreesAngleVector;
  private Vector2 go135DegreesAngleVector;

  private float timeElapsed;

  @Override
  public void create () {
    batch = new SpriteBatch();
    img = new Texture("cat.png");

    goLeftVector = new Vector2(50, 0).setAngle(180);
    goRightVector = new Vector2(50, 0).setAngle(0);
    goUpVector = new Vector2(25, 0).setAngle(90);
    goDownVector = new Vector2(25, 0).setAngle(270);
    go30DegreesAngleVector = new Vector2(75, 0).setAngle(30);
    go135DegreesAngleVector = new Vector2(75, 0).setAngle(135);
  }

  @Override
  public void render () {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    timeElapsed += Gdx.graphics.getDeltaTime();

    batch.begin();

    // statyczny obraz
    batch.draw(img, imgX, imgY);

    // obraz poruszajacy sie w lewo
    batch.draw(
        img,
        imgX + goLeftVector.x * timeElapsed,
        imgY + goLeftVector.y * timeElapsed
    );

    // obraz poruszajacy sie w prawo
    batch.draw(
        img,
        imgX + goRightVector.x * timeElapsed,
        imgY + goRightVector.y * timeElapsed
    );

    // obraz poruszajacy sie w gore
    batch.draw(
        img,
        imgX + goUpVector.x * timeElapsed,
        imgY + goUpVector.y * timeElapsed
    );

    // obraz poruszajacy sie w dol
    batch.draw(
        img,
        imgX + goDownVector.x * timeElapsed,
        imgY + goDownVector.y * timeElapsed
    );

    // obraz poruszajacy sie pod katem 30 stopni
    batch.draw(
        img,
        imgX + go30DegreesAngleVector.x * timeElapsed,
        imgY + go30DegreesAngleVector.y * timeElapsed
    );

    // obraz poruszajacy sie pod katem 120 stopni
    batch.draw(
        img,
        imgX + go135DegreesAngleVector.x * timeElapsed,
        imgY + go135DegreesAngleVector.y * timeElapsed
    );

    batch.end();
  }

  @Override
  public void dispose () {
    batch.dispose();
    img.dispose();
  }
}
