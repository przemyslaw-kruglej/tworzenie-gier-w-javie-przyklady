package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HandlingMouseExample extends ApplicationAdapter {
  private SpriteBatch batch;
  private Texture cat;

  private static final int SCREEN_HEIGHT = 300;
  private int catX, catY;
  private boolean isCatMoving;
  private float lastMouseX, lastMouseY;

  @Override
  public void create () {
    batch = new SpriteBatch();
    cat = new Texture("cat.png");
  }

  @Override
  public void render () {
    int currentMouseX = Gdx.input.getX();
    // zmapuj wspolrzedna Y na wspolrzedne obiektow swiata gry
    int currentMappedMouseY = SCREEN_HEIGHT - 1 - Gdx.input.getY();

    if (isCatMoving) {
      // przesun wspolrzedne tekstury o roznice
      // w aktualnej i poprzedniem pozycji kursora myszki
      catX += currentMouseX - lastMouseX;
      catY += currentMappedMouseY - lastMouseY;

      // zapamietaj aktualne polozenie kursora, aby
      // w kolejnym wykonaniu metody render() sprawdzic,
      // o ile zmienila sie pozycja kursora
      lastMouseX = currentMouseX;
      lastMouseY = currentMappedMouseY;
    }

    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
      // sprawdz, czy kliknieto w obszar tekstury
      if (currentMouseX >= catX &&
          currentMouseX < catX + cat.getWidth() &&
          currentMappedMouseY >= catY &&
          currentMappedMouseY < catY + cat.getHeight()) {
        lastMouseX = currentMouseX;
        lastMouseY = currentMappedMouseY;
        isCatMoving = true;
      }
    } else if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
      isCatMoving = false;
    }

    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    batch.draw(cat, catX, catY);
    batch.end();
  }

  @Override
  public void dispose () {
    batch.dispose();
    cat.dispose();
  }
}
