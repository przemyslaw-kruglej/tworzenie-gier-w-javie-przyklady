package com.kursjava.gamedev;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MyActor extends Actor {
  private final TextureRegion textureRegion;

  public MyActor(Texture texture, float x, float y) {
    this.textureRegion = new TextureRegion(texture);
    this.setPosition(x, y);
    this.setSize(texture.getWidth(), texture.getHeight());
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    batch.setColor(getColor());
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
}
