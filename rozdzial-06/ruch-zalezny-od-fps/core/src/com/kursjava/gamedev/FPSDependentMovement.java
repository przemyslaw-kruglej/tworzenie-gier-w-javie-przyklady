package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FPSDependentMovement extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;

	private int x = 0;
	
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
		batch.draw(img, x, 50);
		batch.end();

		// Zmiana pozycji zapisana w ten sposob powoduje,
		// ze obraz bedzie poruszal sie z rozna szybkoscia
		// w zaleznosci od liczby FPS, z jaka dziala gra.
		x += 2;

		// Zapetlanie animacji gdy obraz wyjdzie poza ekran.
		// Ustawienie x na ujemna wartosc szerokosci tekstury
		// spowoduje efekt "wyjazdu" obrazu z lewej krawedzi okna.
		if (x >= 500) {
			x = -img.getWidth();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
