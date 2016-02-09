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
	chessPiece lastPiece;
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1600, 960);
		batch = new SpriteBatch();
		createBoard();

	}
	private void createBoard() {
		int x = 40;
		int y = 350;

		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(i%2==0) {
					if(i==0) {
						if(j==0) chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new WhiteRook(batch, chessBoard,i,j));
						else if(j==1) chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new WhiteBishop(batch, chessBoard, i, j));
						else if(j==2) chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new WhiteKnight(batch, chessBoard,i,j));
						else if(j==3) chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new WhiteQueen(batch, chessBoard, i, j));
						else if(j==4) chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new WhiteKing(batch, chessBoard, i, j));
						else if(j==5) chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new WhiteKnight(batch, chessBoard,i,j));
						else if(j==6) chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new WhiteBishop(batch, chessBoard, i, j));
						else if(j==7) chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new WhiteRook(batch, chessBoard, i, j));
					}

					else if(i==6) {
						if(j%2==0) chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x,y,i,j,new BlackPawn(batch, chessBoard,i,j));
						else chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x,y,i,j,new BlackPawn(batch, chessBoard,i,j));
					}

					else if(j%2==0) chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j);

					else chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j);
				}
				else {
					if(i==7) {
						if(j==0) chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new BlackRook(batch, chessBoard,i,j));
						else if(j==1) chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new BlackBishop(batch, chessBoard, i, j));
						else if(j==2) chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new BlackKnight(batch, chessBoard,i,j));
						else if(j==3) chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new BlackQueen(batch, chessBoard, i, j));
						else if(j==4) chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new BlackKing(batch, chessBoard, i, j));
						else if(j==5) chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new BlackKnight(batch, chessBoard,i,j));
						else if(j==6) chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j, new BlackBishop(batch, chessBoard, i, j));
						else if(j==7) chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j, new BlackRook(batch, chessBoard, i, j));
					}

					else if(j%2==1) {
						if(i/2 == 0) chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x,y,i,j,new WhitePawn(batch, chessBoard,i,j));

						else chessBoard[i][j] = new Space(batch, new Texture("orange.png"), x, y, i, j);
					}

					else {
						if(i/2 == 0) chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x,y,i,j,new WhitePawn(batch, chessBoard,i,j));

						else chessBoard[i][j] = new Space(batch, new Texture("purple.jpg"), x, y, i, j);
					}
				}
				x+=125;
			}
			x=40;
			y+=125;
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		if(Gdx.input.isTouched()) {
				Rectangle touchedRect = new Rectangle(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 1, 1);
				for(Space[] spaceRow : chessBoard) {
					for(Space space : spaceRow) {
						if(touchedRect.overlaps(space.spaceRect)) {
							if(lastPiece!=null) lastPiece.showMoves();
							if(space.piece!=null) space.piece.showMoves();
							else lastPiece.move(space);
							lastPiece = space.piece;
						}
						space.drawSpace();
					}
				}

		}
		else {
			for(Space[] spaceRow : chessBoard) {
				for(Space space : spaceRow) {
					space.drawSpace();
				}
			}
		}

		batch.end();
	}
}

class Space {
	SpriteBatch batch;
	Texture pic;
	Texture highlightTex = new Texture("green.png");
	Rectangle spaceRect;
	chessPiece piece;
	int x;
	int y;
	int verticalPos;
	int horizontalPos;
	final int width = 125;
	final int height = 125;
	boolean highlighted = false;
	public Space(SpriteBatch batch, Texture tex, int x, int y, int verticalPos, int horizontalPos) {
		this.batch = batch;
		this.pic = tex;
		this.x = x;
		this.y = y;
		this.verticalPos = verticalPos;
		this.horizontalPos = horizontalPos;
		spaceRect = new Rectangle(this.x,this.y,this.width,this.height);
		this.piece = null;
	}
	public Space(SpriteBatch batch, Texture tex, int x, int y, int verticalPos, int horizontalPos, chessPiece piece) {
		this.batch = batch;
		this.pic = tex;
		this.x = x;
		this.y = y;
		this.verticalPos = verticalPos;
		this.horizontalPos = horizontalPos;
		spaceRect = new Rectangle(this.x,this.y,this.width,this.height);
		this.piece = piece;
	}
	public void drawSpace() {
		batch.draw(pic, x, y, width, height);
		if(piece != null) {
			piece.draw();
		}
	}
	public void highlight() {
		Texture temp = pic;
		pic = highlightTex;
		highlightTex = temp;
		highlighted = !highlighted;
	}
}

interface chessPiece {
	void showMoves();
	void move(Space space);
	void draw();
}

class WhitePawn implements chessPiece {
	final Texture pic = new Texture("gabriel.PNG");
	SpriteBatch batch;
	Space[][] board;
	int verticalPos;
	int horizontalPos;
	public WhitePawn(SpriteBatch batch, Space[][] board, int verticalPos, int horizontalPos) {
		this.batch = batch;
		this.board = board;
		this.verticalPos = verticalPos;
		this.horizontalPos = horizontalPos;
	}

	@Override
	public void showMoves() {
		try {
			if (verticalPos == 1 && board[3][horizontalPos].piece == null)
				board[3][horizontalPos].highlight();
		} catch(Exception e) {}
		try {
			if (board[verticalPos + 1][horizontalPos].piece == null)
				board[verticalPos + 1][horizontalPos].highlight();
		} catch (Exception e) {}
		try {
			if (board[verticalPos + 1][horizontalPos + 1].piece != null)
				board[verticalPos + 1][horizontalPos + 1].highlight();
		} catch(Exception e) {}
		try {
			if (board[verticalPos + 1][horizontalPos - 1].piece != null)
				board[verticalPos + 1][horizontalPos - 1].highlight();
		} catch(Exception e) {}
	}

	@Override
	public void move(Space space) {
		try {
			if (verticalPos == 1) {
				if (space == board[verticalPos + 2][horizontalPos] && board[verticalPos + 2][horizontalPos].piece == null) {
					board[verticalPos + 2][horizontalPos].piece = this;
					board[verticalPos][horizontalPos].piece = null;
					verticalPos+=2;
				}
			}
		} catch(Exception e) {}
		try {
			if (space == board[verticalPos + 1][horizontalPos] && board[verticalPos + 1][horizontalPos].piece == null) {
				board[verticalPos + 1][horizontalPos].piece = this;
				board[verticalPos][horizontalPos].piece = null;
				verticalPos+=1;
			}
		} catch(Exception e) {}
		try {
			if (space == board[verticalPos + 1][horizontalPos + 1] && board[verticalPos + 1][horizontalPos + 1].piece != null) {
				board[verticalPos + 1][horizontalPos + 1].piece = this;
				board[verticalPos][horizontalPos].piece = null;
				verticalPos+=1;
				horizontalPos+=1;
			}
		} catch(Exception e) {}
		try {
			if (space == board[verticalPos + 1][horizontalPos - 1] && board[verticalPos + 1][horizontalPos - 1].piece != null) {
				board[verticalPos + 1][horizontalPos - 1].piece = this;
				board[verticalPos][horizontalPos].piece = null;
				verticalPos+=1;
				horizontalPos-=1;
			}
		} catch(Exception e) {}
	}

	public void draw() {
		batch.draw(pic, board[verticalPos][horizontalPos].x+20, board[verticalPos][horizontalPos].y+20, board[verticalPos][horizontalPos].width-40,board[verticalPos][horizontalPos].height-40);
	}
}

class WhiteRook implements chessPiece {
	final Texture pic = new Texture("brody.PNG");
	SpriteBatch batch;
	Space[][] board;
	int verticalPos;
	int horizontalPos;
	public WhiteRook(SpriteBatch batch, Space[][] board, int verticalPos, int horizontalPos) {
		this.batch = batch;
		this.board = board;
		this.verticalPos = verticalPos;
		this.horizontalPos = horizontalPos;
	}

	@Override
	public void showMoves() {
		try {
			int i=1;
			while(board[verticalPos+i][horizontalPos].piece==null) { board[verticalPos+i][horizontalPos].highlight(); i++; }
			if(board[verticalPos+i][horizontalPos].piece instanceof BlackPawn || board[verticalPos+i][horizontalPos].piece instanceof BlackRook || board[verticalPos+i][horizontalPos].piece instanceof BlackBishop || board[verticalPos+i][horizontalPos].piece instanceof BlackKnight || board[verticalPos+i][horizontalPos].piece instanceof BlackQueen || board[verticalPos+i][horizontalPos].piece instanceof BlackKing) board[verticalPos+i][horizontalPos].highlight();
		} catch(Exception e) {}
		try {
			int i=1;
			while(board[verticalPos][horizontalPos+i].piece==null) { board[verticalPos][horizontalPos+i].highlight(); i++; }
			if(board[verticalPos][horizontalPos+i].piece instanceof BlackPawn || board[verticalPos][horizontalPos+i].piece instanceof BlackRook || board[verticalPos][horizontalPos+i].piece instanceof BlackBishop || board[verticalPos][horizontalPos+i].piece instanceof BlackKnight || board[verticalPos][horizontalPos+i].piece instanceof BlackQueen || board[verticalPos][horizontalPos+i].piece instanceof BlackKing) board[verticalPos][horizontalPos+i].highlight();
		} catch(Exception e) {}
		try {
			int i=1;
			while(board[verticalPos-i][horizontalPos].piece==null) { board[verticalPos-i][horizontalPos].highlight(); i++; }
			if(board[verticalPos-i][horizontalPos].piece instanceof BlackPawn || board[verticalPos-i][horizontalPos].piece instanceof BlackRook || board[verticalPos-i][horizontalPos].piece instanceof BlackBishop || board[verticalPos-i][horizontalPos].piece instanceof BlackKnight || board[verticalPos-i][horizontalPos].piece instanceof BlackQueen || board[verticalPos-i][horizontalPos].piece instanceof BlackKing) board[verticalPos-i][horizontalPos].highlight();
		} catch(Exception e) {}
		try {
			int i=1;
			while(board[verticalPos][horizontalPos-i].piece==null) { board[verticalPos][horizontalPos-i].highlight(); i++; }
			if(board[verticalPos][horizontalPos-i].piece instanceof BlackPawn || board[verticalPos][horizontalPos-i].piece instanceof BlackRook || board[verticalPos][horizontalPos-i].piece instanceof BlackBishop || board[verticalPos][horizontalPos-i].piece instanceof BlackKnight || board[verticalPos][horizontalPos-i].piece instanceof BlackQueen || board[verticalPos][horizontalPos-i].piece instanceof BlackKing) board[verticalPos][horizontalPos-i].highlight();
		} catch(Exception e) {}
	}

	@Override
	public void move(Space space) {

	}

	public void draw() {
		batch.draw(pic, board[verticalPos][horizontalPos].x+20, board[verticalPos][horizontalPos].y+20, board[verticalPos][horizontalPos].width-40,board[verticalPos][horizontalPos].height-40);
	}
}

class WhiteBishop implements chessPiece {
	final Texture pic = new Texture("andre.PNG");
	SpriteBatch batch;
	Space[][] board;
	int verticalPos;
	int horizontalPos;
	public WhiteBishop(SpriteBatch batch, Space[][] board, int verticalPos, int horizontalPos) {
		this.batch = batch;
		this.board = board;
		this.verticalPos = verticalPos;
		this.horizontalPos = horizontalPos;
	}

	@Override
	public void showMoves() {

	}

	@Override
	public void move(Space space) {

	}


	public void draw() {
		batch.draw(pic, board[verticalPos][horizontalPos].x+20, board[verticalPos][horizontalPos].y+20, board[verticalPos][horizontalPos].width-40,board[verticalPos][horizontalPos].height-40);
	}
}

class WhiteKnight implements chessPiece {
	final Texture pic = new Texture("jackson.PNG");
	SpriteBatch batch;
	Space[][] board;
	int verticalPos;
	int horizontalPos;
	public WhiteKnight(SpriteBatch batch, Space[][] board, int verticalPos, int horizontalPos) {
		this.batch = batch;
		this.board = board;
		this.verticalPos = verticalPos;
		this.horizontalPos = horizontalPos;
	}

	@Override
	public void showMoves() {

	}

	@Override
	public void move(Space space) {

	}

	public void draw() {
		batch.draw(pic, board[verticalPos][horizontalPos].x + 20, board[verticalPos][horizontalPos].y + 20, board[verticalPos][horizontalPos].width - 40, board[verticalPos][horizontalPos].height - 40);
	}
}

class WhiteKing implements chessPiece {
	final Texture pic = new Texture("fatherboardslogo.png");
	SpriteBatch batch;
	Space[][] board;
	int verticalPos;
	int horizontalPos;
	public WhiteKing(SpriteBatch batch, Space[][] board, int verticalPos, int horizontalPos) {
		this.batch = batch;
		this.board = board;
		this.verticalPos = verticalPos;
		this.horizontalPos = horizontalPos;
	}

	@Override
	public void showMoves() {

	}

	@Override
	public void move(Space space) {

	}

	public void draw() {
		batch.draw(pic, board[verticalPos][horizontalPos].x+20, board[verticalPos][horizontalPos].y+20, board[verticalPos][horizontalPos].width-40,board[verticalPos][horizontalPos].height-40);
	}
}

class WhiteQueen implements chessPiece {
	final Texture pic = new Texture("billini.PNG");
	SpriteBatch batch;
	Space[][] board;
	int verticalPos;
	int horizontalPos;
	public WhiteQueen(SpriteBatch batch, Space[][] board, int verticalPos, int horizontalPos) {
		this.batch = batch;
		this.board = board;
		this.verticalPos = verticalPos;
		this.horizontalPos = horizontalPos;
	}

	@Override
	public void showMoves() {

	}

	@Override
	public void move(Space space) {

	}

	public void draw() {
		batch.draw(pic, board[verticalPos][horizontalPos].x+20, board[verticalPos][horizontalPos].y+20, board[verticalPos][horizontalPos].width-40,board[verticalPos][horizontalPos].height-40);
	}
}

class BlackPawn implements chessPiece {
	final Texture pic = new Texture("vega.PNG");
	SpriteBatch batch;
	Space[][] board;
	int verticalPos;
	int horizontalPos;
	public BlackPawn(SpriteBatch batch, Space[][] board, int verticalPos, int horizontalPos) {
		this.batch = batch;
		this.board = board;
		this.verticalPos = verticalPos;
		this.horizontalPos = horizontalPos;
	}

	@Override
	public void showMoves() {
		try {
			if (verticalPos == 6 && board[4][horizontalPos].piece == null)
				board[4][horizontalPos].highlight();
		} catch(Exception e) {}
		try {
			if (board[verticalPos - 1][horizontalPos].piece == null)
				board[verticalPos - 1][horizontalPos].highlight();
		} catch (Exception e) {}
		try {
			if (board[verticalPos - 1][horizontalPos + 1].piece != null)
				board[verticalPos - 1][horizontalPos + 1].highlight();
		} catch(Exception e) {}
		try {
			if (board[verticalPos - 1][horizontalPos - 1].piece != null)
				board[verticalPos - 1][horizontalPos - 1].highlight();
		} catch(Exception e) {}
	}

	@Override
	public void move(Space space) {
		try {
			if (verticalPos == 6) {
				if (space == board[verticalPos - 2][horizontalPos] && board[verticalPos - 2][horizontalPos].piece == null) {
					board[verticalPos - 2][horizontalPos].piece = this;
					board[verticalPos][horizontalPos].piece = null;
					verticalPos-=2;
				}
			}
		} catch(Exception e) {}
		try {
			if (space == board[verticalPos - 1][horizontalPos] && board[verticalPos - 1][horizontalPos].piece == null) {
				board[verticalPos - 1][horizontalPos].piece = this;
				board[verticalPos][horizontalPos].piece = null;
				verticalPos-=1;
			}
		} catch(Exception e) {}
		try {
			if (space == board[verticalPos - 1][horizontalPos + 1] && board[verticalPos - 1][horizontalPos + 1].piece != null) {
				board[verticalPos - 1][horizontalPos + 1].piece = this;
				board[verticalPos][horizontalPos].piece = null;
				verticalPos-=1;
				horizontalPos+=1;
			}
		} catch(Exception e) {}
		try {
			if (space == board[verticalPos - 1][horizontalPos - 1] && board[verticalPos - 1][horizontalPos - 1].piece != null) {
				board[verticalPos - 1][horizontalPos - 1].piece = this;
				board[verticalPos][horizontalPos].piece = null;
				verticalPos-=1;
				horizontalPos-=1;
			}
		} catch(Exception e) {}
	}

	public void draw() {
		batch.draw(pic, board[verticalPos][horizontalPos].x+20, board[verticalPos][horizontalPos].y+20, board[verticalPos][horizontalPos].width-40,board[verticalPos][horizontalPos].height-40);
	}
}

class BlackRook implements chessPiece {
	final Texture pic = new Texture("brett.png");
	SpriteBatch batch;
	Space[][] board;
	int verticalPos;
	int horizontalPos;
	public BlackRook(SpriteBatch batch, Space[][] board, int verticalPos, int horizontalPos) {
		this.batch = batch;
		this.board = board;
		this.verticalPos = verticalPos;
		this.horizontalPos = horizontalPos;
	}

	@Override
	public void showMoves() {
		try {
			int i=1;
			while(board[verticalPos+i][horizontalPos].piece==null) { board[verticalPos+i][horizontalPos].highlight(); i++; }
			if(board[verticalPos+i][horizontalPos].piece instanceof WhitePawn || board[verticalPos+i][horizontalPos].piece instanceof WhiteRook || board[verticalPos+i][horizontalPos].piece instanceof WhiteBishop || board[verticalPos+i][horizontalPos].piece instanceof WhiteKnight || board[verticalPos+i][horizontalPos].piece instanceof WhiteQueen || board[verticalPos+i][horizontalPos].piece instanceof WhiteKing) board[verticalPos+i][horizontalPos].highlight();
		} catch(Exception e) {}
		try {
			int i=1;
			while(board[verticalPos][horizontalPos+i].piece==null) { board[verticalPos][horizontalPos+i].highlight(); i++; }
			if(board[verticalPos][horizontalPos+i].piece instanceof WhitePawn || board[verticalPos][horizontalPos+i].piece instanceof WhiteRook || board[verticalPos][horizontalPos+i].piece instanceof WhiteBishop || board[verticalPos][horizontalPos+i].piece instanceof WhiteKnight || board[verticalPos][horizontalPos+i].piece instanceof WhiteQueen || board[verticalPos][horizontalPos+i].piece instanceof WhiteKing) board[verticalPos][horizontalPos+i].highlight();
		} catch(Exception e) {}
		try {
			int i=1;
			while(board[verticalPos-i][horizontalPos].piece==null) { board[verticalPos-i][horizontalPos].highlight(); i++; }
			if(board[verticalPos-i][horizontalPos].piece instanceof WhitePawn || board[verticalPos-i][horizontalPos].piece instanceof WhiteRook || board[verticalPos-i][horizontalPos].piece instanceof WhiteBishop || board[verticalPos-i][horizontalPos].piece instanceof WhiteKnight || board[verticalPos-i][horizontalPos].piece instanceof WhiteQueen || board[verticalPos-i][horizontalPos].piece instanceof WhiteKing) board[verticalPos-i][horizontalPos].highlight();
		} catch(Exception e) {}
		try {
			int i=1;
			while(board[verticalPos][horizontalPos-i].piece==null) { board[verticalPos][horizontalPos-i].highlight(); i++; }
			if(board[verticalPos][horizontalPos-i].piece instanceof WhitePawn || board[verticalPos][horizontalPos-i].piece instanceof WhiteRook || board[verticalPos][horizontalPos-i].piece instanceof WhiteBishop || board[verticalPos][horizontalPos-i].piece instanceof WhiteKnight || board[verticalPos][horizontalPos-i].piece instanceof WhiteQueen || board[verticalPos][horizontalPos-i].piece instanceof WhiteKing) board[verticalPos][horizontalPos-i].highlight();
		} catch(Exception e) {}
	}

	@Override
	public void move(Space space) {

	}


	public void draw() {
		batch.draw(pic, board[verticalPos][horizontalPos].x+20, board[verticalPos][horizontalPos].y+20, board[verticalPos][horizontalPos].width-40,board[verticalPos][horizontalPos].height-40);
	}
}

class BlackBishop implements chessPiece {
	final Texture pic = new Texture("berger.PNG");
	SpriteBatch batch;
	Space[][] board;
	int verticalPos;
	int horizontalPos;
	public BlackBishop(SpriteBatch batch, Space[][] board, int verticalPos, int horizontalPos) {
		this.batch = batch;
		this.board = board;
		this.verticalPos = verticalPos;
		this.horizontalPos = horizontalPos;
	}

	@Override
	public void showMoves() {

	}

	@Override
	public void move(Space space) {

	}

	public void draw() {
		batch.draw(pic, board[verticalPos][horizontalPos].x+20, board[verticalPos][horizontalPos].y+20, board[verticalPos][horizontalPos].width-40,board[verticalPos][horizontalPos].height-40);
	}
}

class BlackKnight implements chessPiece {
	final Texture pic = new Texture("carsen.PNG");
	SpriteBatch batch;
	Space[][] board;
	int verticalPos;
	int horizontalPos;
	public BlackKnight(SpriteBatch batch, Space[][] board, int verticalPos, int horizontalPos) {
		this.batch = batch;
		this.board = board;
		this.verticalPos = verticalPos;
		this.horizontalPos = horizontalPos;
	}

	@Override
	public void showMoves() {

	}

	@Override
	public void move(Space space) {

	}


	public void draw() {
		batch.draw(pic, board[verticalPos][horizontalPos].x+20, board[verticalPos][horizontalPos].y+20, board[verticalPos][horizontalPos].width-40,board[verticalPos][horizontalPos].height-40);
	}
}

class BlackKing implements chessPiece {
	final Texture pic = new Texture("zipper.PNG");
	SpriteBatch batch;
	Space[][] board;
	int verticalPos;
	int horizontalPos;
	public BlackKing(SpriteBatch batch, Space[][] board, int verticalPos, int horizontalPos) {
		this.batch = batch;
		this.board = board;
		this.verticalPos = verticalPos;
		this.horizontalPos = horizontalPos;
	}

	@Override
	public void showMoves() {

	}

	@Override
	public void move(Space space) {

	}


	public void draw() {
		batch.draw(pic, board[verticalPos][horizontalPos].x+20, board[verticalPos][horizontalPos].y+20, board[verticalPos][horizontalPos].width-40,board[verticalPos][horizontalPos].height-40);
	}
}

class BlackQueen implements chessPiece {
	final Texture pic = new Texture("revilla.PNG");
	SpriteBatch batch;
	Space[][] board;
	int verticalPos;
	int horizontalPos;
	public BlackQueen(SpriteBatch batch, Space[][] board, int verticalPos, int horizontalPos) {
		this.batch = batch;
		this.board = board;
		this.verticalPos = verticalPos;
		this.horizontalPos = horizontalPos;
	}

	@Override
	public void showMoves() {

	}

	@Override
	public void move(Space space) {

	}

	public void draw() {
		batch.draw(pic, board[verticalPos][horizontalPos].x+20, board[verticalPos][horizontalPos].y+20, board[verticalPos][horizontalPos].width-40,board[verticalPos][horizontalPos].height-40);
	}
}


