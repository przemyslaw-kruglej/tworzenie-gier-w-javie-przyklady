package com.kursjava.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

import java.util.LinkedList;
import java.util.List;

public class SimpleJigsawPuzzleGame extends ApplicationAdapter {
  public static final int WINDOW_WIDTH = 800;
  public static final int WINDOW_HEIGHT = 450;

  private static final int PUZZLE_PIECE_WIDTH = 100;
  private static final int PUZZLE_PIECE_HEIGHT = 100;

  private Texture puzzleImg;
  private Texture puzzleOutlineImg;
  private SpriteBatch batch;
  private int puzzleOriginX;
  private int puzzleOriginY;

  private List<PuzzlePiece> puzzlePiecesLeft;

  @Override
  public void create () {
    puzzleImg = new Texture("kitty.png");
    puzzleOriginX = WINDOW_WIDTH / 2 - puzzleImg.getWidth() / 2;
    puzzleOriginY = WINDOW_HEIGHT / 2 - puzzleImg.getHeight() / 2;

    puzzleOutlineImg = new Texture("puzzle_outline.png");
    batch = new SpriteBatch();

    puzzlePiecesLeft = new LinkedList<>();

    preparePuzzlePieces();
  }

  @Override
  public void render () {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();

    batch.draw(puzzleOutlineImg, puzzleOriginX, puzzleOriginY);

    puzzlePiecesLeft.forEach(piece -> piece.draw(batch));

    batch.end();
  }

  @Override
  public void dispose () {
    batch.dispose();
    puzzleImg.dispose();
    puzzleOutlineImg.dispose();
  }

  private void preparePuzzlePieces() {
    int numberOfPuzzleRows =
        puzzleImg.getHeight() / PUZZLE_PIECE_HEIGHT;
    int numberOfPuzzleColumns =
        puzzleImg.getWidth() / PUZZLE_PIECE_WIDTH;

    for (int row = 0; row < numberOfPuzzleRows; row++) {
      for (int col = 0; col < numberOfPuzzleColumns; col++) {

        TextureRegion puzzlePieceImg = new TextureRegion();

        puzzlePieceImg.setTexture(puzzleImg);
        puzzlePieceImg.setRegion(
            col * PUZZLE_PIECE_WIDTH,
            row * PUZZLE_PIECE_HEIGHT,
            PUZZLE_PIECE_WIDTH,
            PUZZLE_PIECE_HEIGHT
        );

        GridPoint2 positionOnScreen =
            randomizePuzzlePiecePosition();

        PuzzlePiece piece = new PuzzlePiece(
            puzzlePieceImg,
            positionOnScreen
        );

        puzzlePiecesLeft.add(piece);
      }
    }
  }

  private GridPoint2 randomizePuzzlePiecePosition() {
    return new GridPoint2(
        randomIntMax(WINDOW_WIDTH - PUZZLE_PIECE_WIDTH),
        randomIntMax(WINDOW_HEIGHT - PUZZLE_PIECE_HEIGHT)
    );
  }

  private int randomIntMax(int maxValue) {
    return (int) (Math.random() * (maxValue + 1));
  }
}
