package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureRegionExample extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture multiTexture;
	private TextureRegion star, lightning, diamond, triangle;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		multiTexture = new Texture("kilka_obrazow.png");

		star = new TextureRegion(multiTexture, 0, 0, 50, 50);
		lightning = new TextureRegion(multiTexture, 50, 0, 50, 50);
		diamond = new TextureRegion(multiTexture, 0, 50, 50, 50);
		triangle = new TextureRegion(multiTexture, 50, 50, 50, 50);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		// cala tekstura
		batch.draw(multiTexture, 0, 0);

		// fragment tekstury
		batch.draw(star, 225, 200);
		batch.draw(lightning, 225, 150);
		batch.draw(diamond, 225, 100);
		batch.draw(triangle, 225, 50);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		multiTexture.dispose();
	}
}
