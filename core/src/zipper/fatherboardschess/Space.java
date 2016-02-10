package zipper.fatherboardschess;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by zipper on 2/10/16.
 */
public class Space {
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
    public static boolean instanceOfBlack(chessPiece piece) {
        return piece instanceof BlackPawn || piece instanceof BlackKing || piece instanceof BlackBishop || piece instanceof BlackRook || piece instanceof BlackKnight || piece instanceof BlackQueen;
    }
    public static boolean instanceOfWhite(chessPiece piece) {
        return piece instanceof WhitePawn || piece instanceof WhiteKing || piece instanceof WhiteBishop || piece instanceof WhiteRook || piece instanceof WhiteKnight || piece instanceof WhiteQueen;
    }
}