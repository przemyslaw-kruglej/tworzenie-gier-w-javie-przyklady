package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleCollisionsExample extends ApplicationAdapter {
  private static final int PLAYER_SPEED = 50;
  private SpriteBatch batch;
  private Texture catImg;
  private Texture blockImg;

  private CollisionActor player;
  private CollisionActor[] blocks;
  
  @Override
  public void create () {
    batch = new SpriteBatch();
    catImg = new Texture("cat.png");
    blockImg = new Texture("block.png");

    player = new CollisionActor(catImg, 0, 0);

    blocks = new CollisionActor[3];
    blocks[0] = new CollisionActor(blockImg, 40, 60);
    blocks[1] = new CollisionActor(blockImg, 40, 180);
    blocks[2] = new CollisionActor(blockImg, 230, 160);
  }

  @Override
  public void render () {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    handleKeyboard();

    batch.begin();

    for (CollisionActor block : blocks) {
      block.draw(batch, 1);
    }
    player.draw(batch, 1);

    batch.end();
  }

  @Override
  public void dispose () {
    batch.dispose();
    catImg.dispose();
    blockImg.dispose();
  }

  private void handleKeyboard() {
    float deltaTime = Gdx.graphics.getDeltaTime();

    float deltaX = 0, deltaY = 0;

    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      deltaX = PLAYER_SPEED * deltaTime;
    } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      deltaX = -PLAYER_SPEED * deltaTime;
    }

    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      deltaY = PLAYER_SPEED * deltaTime;
    } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      deltaY = -PLAYER_SPEED * deltaTime;
    }

    if (deltaX != 0 || deltaY != 0) {
      movePlayer(deltaX, deltaY);
    }
  }

  private void movePlayer(float deltaX, float deltaY) {
    player.moveBy(deltaX, deltaY);

    for (CollisionActor block : blocks) {
      if (player.checkCollision(block)) {
        player.moveBy(-deltaX, -deltaY);
        break;
      }
    }
  }
}
