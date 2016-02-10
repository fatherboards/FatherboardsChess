package zipper.fatherboardschess;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by zipper on 2/10/16.
 */
public class WhiteRook implements chessPiece {
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
    public void move(Space space) {
        try {
            int i = 1;
            while (board[verticalPos+i][horizontalPos].piece == null) {
                if(space == board[verticalPos+i][horizontalPos]) {
                    board[verticalPos+i][horizontalPos].piece = this;
                    board[verticalPos][horizontalPos].piece = null;
                    verticalPos+=i;
                    return;
                }
                i++;
            }
            if(space == board[verticalPos+i][horizontalPos] && !Space.instanceOfWhite(board[verticalPos + i][horizontalPos].piece)) {
                board[verticalPos+i][horizontalPos].piece = this;
                board[verticalPos][horizontalPos].piece = null;
                verticalPos+=i;
                return;
            }
        } catch(Exception e) {}
        try {
            int i = 1;
            while (board[verticalPos-i][horizontalPos].piece == null) {
                if(space == board[verticalPos-i][horizontalPos]) {
                    board[verticalPos-i][horizontalPos].piece = this;
                    board[verticalPos][horizontalPos].piece = null;
                    verticalPos-=i;
                    return;
                }
                i++;
            }
            if(space == board[verticalPos-i][horizontalPos] && !Space.instanceOfWhite(board[verticalPos - i][horizontalPos].piece)) {
                board[verticalPos-i][horizontalPos].piece = this;
                board[verticalPos][horizontalPos].piece = null;
                verticalPos-=i;
                return;
            }
        } catch(Exception e) {}
        try {
            int i = 1;
            while (board[verticalPos][horizontalPos+i].piece == null) {
                if(space == board[verticalPos][horizontalPos+i]) {
                    board[verticalPos][horizontalPos+i].piece = this;
                    board[verticalPos][horizontalPos].piece = null;
                    horizontalPos+=i;
                    return;
                }
                i++;
            }
            if(space == board[verticalPos][horizontalPos+i] && !Space.instanceOfWhite(board[verticalPos][horizontalPos + i].piece)) {
                board[verticalPos][horizontalPos+i].piece = this;
                board[verticalPos][horizontalPos].piece = null;
                horizontalPos+=i;
                return;
            }
        } catch(Exception e) {}
        try {
            int i = 1;
            while (board[verticalPos][horizontalPos-i].piece == null) {
                if(space == board[verticalPos][horizontalPos-i]) {
                    board[verticalPos][horizontalPos-i].piece = this;
                    board[verticalPos][horizontalPos].piece = null;
                    horizontalPos-=i;
                    return;
                }
                i++;
            }
            if(space == board[verticalPos][horizontalPos-i] && !Space.instanceOfWhite(board[verticalPos][horizontalPos - i].piece)) {
                board[verticalPos][horizontalPos-i].piece = this;
                board[verticalPos][horizontalPos].piece = null;
                horizontalPos-=i;
                return;
            }
        } catch(Exception e) {}
    }
    @Override
    public void draw() {
        batch.draw(pic, board[verticalPos][horizontalPos].x+20, board[verticalPos][horizontalPos].y+20, board[verticalPos][horizontalPos].width-40,board[verticalPos][horizontalPos].height-40);
    }
}