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
}