package zipper.fatherboardschess;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by zipper on 2/10/16.
 */
public class WhiteKnight implements chessPiece {
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
    public void move(Space space) {
        try {
            if (space == board[verticalPos+2][horizontalPos+1] && !Space.instanceOfWhite(board[verticalPos+2][horizontalPos+1].piece)) {
                board[verticalPos+2][horizontalPos+1].piece = this;
                board[verticalPos][horizontalPos].piece = null;
                verticalPos+=2;
                horizontalPos+=1;
                return;
            }
        } catch(Exception e) {}
        try {
            if (space == board[verticalPos+2][horizontalPos-1] && !Space.instanceOfWhite(board[verticalPos+2][horizontalPos-1].piece)) {
                board[verticalPos+2][horizontalPos-1].piece = this;
                board[verticalPos][horizontalPos].piece = null;
                verticalPos+=2;
                horizontalPos-=1;
                return;
            }
        } catch(Exception e) {}
        try {
            if (space == board[verticalPos+1][horizontalPos+2] && !Space.instanceOfWhite(board[verticalPos+1][horizontalPos+2].piece)) {
                board[verticalPos+1][horizontalPos+2].piece = this;
                board[verticalPos][horizontalPos].piece = null;
                verticalPos+=1;
                horizontalPos+=2;
                return;
            }
        } catch(Exception e) {}
        try {
            if (space == board[verticalPos-1][horizontalPos+2] && !Space.instanceOfWhite(board[verticalPos-1][horizontalPos+2].piece)) {
                board[verticalPos-1][horizontalPos+2].piece = this;
                board[verticalPos][horizontalPos].piece = null;
                verticalPos-=1;
                horizontalPos+=2;
                return;
            }
        } catch(Exception e) {}
        try {
            if (space == board[verticalPos-2][horizontalPos+1] && !Space.instanceOfWhite(board[verticalPos-2][horizontalPos+1].piece)) {
                board[verticalPos-2][horizontalPos+1].piece = this;
                board[verticalPos][horizontalPos].piece = null;
                verticalPos-=2;
                horizontalPos+=1;
                return;
            }
        } catch(Exception e) {}
        try {
            if (space == board[verticalPos-2][horizontalPos-1] && !Space.instanceOfWhite(board[verticalPos-2][horizontalPos-1].piece)) {
                board[verticalPos-2][horizontalPos-1].piece = this;
                board[verticalPos][horizontalPos].piece = null;
                verticalPos-=2;
                horizontalPos-=1;
                return;
            }
        } catch(Exception e) {}
        try {
            if (space == board[verticalPos+1][horizontalPos-2] && !Space.instanceOfWhite(board[verticalPos+1][horizontalPos-2].piece)) {
                board[verticalPos+1][horizontalPos-2].piece = this;
                board[verticalPos][horizontalPos].piece = null;
                verticalPos+=1;
                horizontalPos-=2;
                return;
            }
        } catch(Exception e) {}
        try {
            if (space == board[verticalPos-1][horizontalPos-2] && !Space.instanceOfWhite(board[verticalPos-1][horizontalPos-2].piece)) {
                board[verticalPos-1][horizontalPos-2].piece = this;
                board[verticalPos][horizontalPos].piece = null;
                verticalPos-=1;
                horizontalPos-=2;
                return;
            }
        } catch(Exception e) {}
    }
    @Override
    public void draw() {
        batch.draw(pic, board[verticalPos][horizontalPos].x + 20, board[verticalPos][horizontalPos].y + 20, board[verticalPos][horizontalPos].width - 40, board[verticalPos][horizontalPos].height - 40);
    }
}