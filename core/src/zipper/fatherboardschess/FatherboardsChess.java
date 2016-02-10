package zipper.fatherboardschess;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class FatherboardsChess extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;
	Space[][] chessBoard = new Space[8][8];
	Space lastSpace;
	int turns = 0;
	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1600, 960);
		batch = new SpriteBatch();
		createBoard();
	}
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if (Gdx.input.isTouched()) {
			Rectangle touchedRect = new Rectangle(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 1, 1);
			for (Space[] spaceRow : chessBoard) {
				for (Space space : spaceRow) {
					if (touchedRect.overlaps(space.spaceRect)) {
						if (lastSpace != null && lastSpace.piece != null) {
							if (turns % 2 == 0 && Space.instanceOfWhite(lastSpace.piece)) {
								lastSpace.piece.move(space);
								turns++;
								if (lastSpace.piece != null) {
									turns--;
								}
							} else if (turns % 2 == 1 && Space.instanceOfBlack(lastSpace.piece)) {
								lastSpace.piece.move(space);
								turns++;
								if (lastSpace.piece != null) {
									turns--;
								}
							}
						}
						lastSpace = space;
					}
					space.drawSpace();
				}
			}
		}
		else {
			for (Space[] spaceRow : chessBoard) {
				for (Space space : spaceRow) {
					space.drawSpace();
				}
			}
		}
		batch.end();
	}
	private void createBoard() {
		int x = 40;
		int y = 350;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 == 0) {
					if (i == 0) {
						if (j == 0)
							chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new WhiteRook(batch, chessBoard, i, j));
						else if (j == 1)
							chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new WhiteBishop(batch, chessBoard, i, j));
						else if (j == 2)
							chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new WhiteKnight(batch, chessBoard, i, j));
						else if (j == 3)
							chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new WhiteQueen(batch, chessBoard, i, j));
						else if (j == 4)
							chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new WhiteKing(batch, chessBoard, i, j));
						else if (j == 5)
							chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new WhiteKnight(batch, chessBoard, i, j));
						else if (j == 6)
							chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new WhiteBishop(batch, chessBoard, i, j));
						else if (j == 7)
							chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new WhiteRook(batch, chessBoard, i, j));
					} else if (i == 6) {
						if (j % 2 == 0)
							chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new BlackPawn(batch, chessBoard, i, j));
						else
							chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new BlackPawn(batch, chessBoard, i, j));
					} else if (j % 2 == 0)
						chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j);

					else chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j);
				} else {
					if (i == 7) {
						if (j == 0)
							chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new BlackRook(batch, chessBoard, i, j));
						else if (j == 1)
							chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new BlackBishop(batch, chessBoard, i, j));
						else if (j == 2)
							chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new BlackKnight(batch, chessBoard, i, j));
						else if (j == 3)
							chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new BlackQueen(batch, chessBoard, i, j));
						else if (j == 4)
							chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new BlackKing(batch, chessBoard, i, j));
						else if (j == 5)
							chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new BlackKnight(batch, chessBoard, i, j));
						else if (j == 6)
							chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new BlackBishop(batch, chessBoard, i, j));
						else if (j == 7)
							chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new BlackRook(batch, chessBoard, i, j));
					} else if (j % 2 == 1) {
						if (i / 2 == 0)
							chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new WhitePawn(batch, chessBoard, i, j));

						else
							chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j);
					} else {
						if (i / 2 == 0)
							chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new WhitePawn(batch, chessBoard, i, j));

						else
							chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j);
					}
				}
				x += 125;
			}
			x = 40;
			y += 125;
		}
	}
}