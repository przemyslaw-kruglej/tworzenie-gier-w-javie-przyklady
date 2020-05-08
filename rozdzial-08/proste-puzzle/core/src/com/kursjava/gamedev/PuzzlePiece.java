package com.kursjava.gamedev;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

class PuzzlePiece {
  private TextureRegion pieceImg;
  private GridPoint2 positionOnScreen;
  private GridPoint2 positionInPuzzle;
  private int rotation;

  PuzzlePiece(
      TextureRegion pieceImg,
      GridPoint2 positionOnScreen,
      GridPoint2 positionInPuzzle,
      int rotation) {

    this.pieceImg = pieceImg;
    this.positionOnScreen = new GridPoint2(positionOnScreen);
    this.positionInPuzzle = new GridPoint2(positionInPuzzle);
    this.rotation = rotation;
  }

  void draw(SpriteBatch batch) {
    batch.draw(
        pieceImg,
        positionOnScreen.x,
        positionOnScreen.y,
        pieceImg.getRegionWidth() / 2,
        pieceImg.getRegionHeight() / 2,
        pieceImg.getRegionWidth(),
        pieceImg.getRegionHeight(),
        1,
        1,
        rotation
    );
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

  boolean isPositionRight(GridPoint2 dropPosition) {
    return rotation == 0 &&
        dropPosition.x >= positionInPuzzle.x &&
        dropPosition.y >= positionInPuzzle.y &&
        dropPosition.x < positionInPuzzle.x + pieceImg.getRegionWidth() &&
        dropPosition.y < positionInPuzzle.y + pieceImg.getRegionHeight();
  }

  void snapToGrid() {
    positionOnScreen.set(positionInPuzzle);
  }

  void rotate() {
    rotation = rotation == 270 ? 0 : rotation + 90;
  }
}