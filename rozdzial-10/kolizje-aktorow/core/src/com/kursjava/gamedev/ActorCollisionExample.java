package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ActorCollisionExample extends ApplicationAdapter {
  private SpriteBatch batch;
  private Texture cat;
  private Texture box;
  private Texture diamond;
  private Texture food;

  private PlayerActor player;
  private StationaryActor[] gameObjects;

  @Override
  public void create () {
    batch = new SpriteBatch();

    cat = new Texture("cat.png");
    box = new Texture("wall.png");
    diamond = new Texture("diamond.png");
    food = new Texture("food.png");

    player = new PlayerActor(
        cat,
        10,
        10,
        getRectangularVertices(cat.getWidth(), cat.getHeight())
    );

    gameObjects = new StationaryActor[15];
    gameObjects[0] = createBox(10, 80);
    gameObjects[1] = createBox(120, 80);
    gameObjects[2] = createBox(230, 80);
    gameObjects[3] = createBox(230, 150);
    gameObjects[4] = createBox(10, 200);

    gameObjects[5] = createFood(95, 90);
    gameObjects[6] = createFood(225, 125);
    gameObjects[7] = createFood(285, 125);
    gameObjects[8] = createFood(160, 250);
    gameObjects[9] = createFood(205, 250);

    gameObjects[10] = createFood(205, 90);
    gameObjects[11] = createDiamond(135, 140);
    gameObjects[12] = createDiamond(30, 140);
    gameObjects[13] = createDiamond(30, 245);
    gameObjects[14] = createDiamond(300, 205);
  }

  @Override
  public void render () {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    player.act(Gdx.graphics.getDeltaTime());

    batch.begin();

    for (StationaryActor gameObject : gameObjects) {
      gameObject.draw(batch, 1);
      player.checkCollision(gameObject);
    }

    player.draw(batch, 1);

    batch.end();
  }

  @Override
  public void dispose () {
    batch.dispose();
    cat.dispose();
    box.dispose();
    diamond.dispose();
    food.dispose();
  }

  private StationaryActor createBox(float x, float y) {
    return new StationaryActor(
        box,
        false,
        x,
        y,
        getRectangularVertices(
            box.getWidth(),
            box.getHeight()
        )
    );
  }

  private StationaryActor createDiamond(float x, float y) {
    return new StationaryActor(
        diamond,
        true,
        x,
        y,
        getDiamondVertices(
            diamond.getWidth(),
            diamond.getHeight()
        )
    );
  }

  private StationaryActor createFood(float x, float y) {
    return new StationaryActor(
        food,
        true,
        x,
        y,
        getRectangularVertices(
            food.getWidth(),
            food.getHeight()
        )
    );
  }

  private float[] getRectangularVertices(int width, int height) {
    return new float[] {
        0, 0,           // lewy dolny wierzcholek
        width, 0,       // prawy dolny wierzcholek
        width, height,  // prawy gorny wierzcholek
        0, height       // lewy gorny wierzcholek
    };
  }

  private float[] getDiamondVertices(int width, int height) {
    return new float[] {
        width / 2.0f, 0,      // srodek dolnej krawedzi
        width, height / 2.0f, // srodek prawej krawedzi
        width / 2.0f, height, // srodek gornej krawedzi
        0, height / 2.0f      // srodek lewej krawedzi
    };
  }
}
