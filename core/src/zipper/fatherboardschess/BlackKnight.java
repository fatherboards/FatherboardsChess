package zipper.fatherboardschess;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by zipper on 2/10/16.
 */
public class BlackKnight implements chessPiece {
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
    public void move(Space space) {

    }
    @Override
    public void draw() {
        batch.draw(pic, board[verticalPos][horizontalPos].x+20, board[verticalPos][horizontalPos].y+20, board[verticalPos][horizontalPos].width-40,board[verticalPos][horizontalPos].height-40);
    }
}