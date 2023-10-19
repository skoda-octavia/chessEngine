package chessEngine.chess.piece;

import lombok.Getter;

@Getter
public abstract class Piece {
    private PieceColor pieceColor;
    private int height;
    private int width;

    void setHeight(int height) {
        if (height < 0 || height > 7) {
            String message = "Illegal height: " + height;
            throw new IllegalArgumentException(message);
        }
        else {this.height = height;}
    }

    void setWidth(int width) {
        if (width < 0 || width > 7) {
            String message = "Illegal width: " + width;
            throw new IllegalArgumentException(message);
        }
        else {this.width = width;}
    }

    public Piece(int height, int width) {
        this.height = height;
        this.width = width;
    }
}
