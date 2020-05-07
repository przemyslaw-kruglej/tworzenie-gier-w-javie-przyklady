package com.kursjava.gamedev;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

class PuzzlePiece {
  private TextureRegion pieceImg;
  private GridPoint2 positionOnScreen;

  PuzzlePiece(
      TextureRegion pieceImg,
      GridPoint2 positionOnScreen) {

    this.pieceImg = pieceImg;
    this.positionOnScreen = new GridPoint2(positionOnScreen);
  }

  void draw(SpriteBatch batch) {
    batch.draw(pieceImg, positionOnScreen.x, positionOnScreen.y);
  }

  boolean isMouseIn(GridPoint2 mousePos) {
    return
        mousePos.x >= positionOnScreen.x &&
        mousePos.y >= positionOnScreen.y &&
        mousePos.x < positionOnScreen.x + pieceImg.getRegionWidth() &&
        mousePos.y < positionOnScreen.y + pieceImg.getRegionHeight();
  }

  void moveBy(int x, int y) {
    positionOnScreen.x += x;
    positionOnScreen.y += y;
  }
}