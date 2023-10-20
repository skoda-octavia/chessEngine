package chessEngine.chess.piece;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Piece {
    private byte height;
    private byte width;

    void setHeight(byte height) {
        if (height < 0 || height > 7) {
            String message = "Illegal height: " + height;
            throw new IllegalArgumentException(message);
        }
        else {this.height = height;}
    }

    void setWidth(byte width) {
        if (width < 0 || width > 7) {
            String message = "Illegal width: " + width;
            throw new IllegalArgumentException(message);
        }
        else {this.width = width;}
    }

    public Piece(byte height, byte width) {
        this.height = height;
        this.width = width;
    }
}
