package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FPSIndependentMovement extends ApplicationAdapter {
  private SpriteBatch batch;
  private Texture img;

  private static final int SPEED = 100;
  private float xFPSDependent;
  private float xFPSIndependent;

  @Override
  public void create () {
    batch = new SpriteBatch();
    img = new Texture("arrow.png");
  }

  @Override
  public void render () {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    batch.draw(img, xFPSDependent, 80);
    batch.draw(img, xFPSIndependent, 0);
    batch.end();

    // Ruch nie zalezny od FPS, lecz od czasu, jaki uplynal
    // od poprzedniego wywolania metody render().
    xFPSIndependent += SPEED * Gdx.graphics.getDeltaTime();
    // Ruch zalezny od FPS.
    xFPSDependent += 2;

    // Zapetlanie animacji gdy obrazy wyjda poza ekran.
    // Ustawienie x na ujemna wartosc szerokosci tekstury
    // spowoduje efekt "wyjazdu" obrazu z lewej krawedzi okna.
    if (xFPSIndependent >= 520) {
      xFPSIndependent = -img.getWidth();
    }
    if (xFPSDependent>= 520) {
      xFPSDependent = -img.getWidth();
    }
  }

  @Override
  public void dispose () {
    batch.dispose();
    img.dispose();
  }
}
