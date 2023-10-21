package chessEngine.chess.piece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.Move;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public abstract class Piece {
    protected byte height;
    protected byte width;
    protected EnginePosition position;
    protected PieceColor pieceColor;

    void setHeight(byte height) {
        if (height < 0 || height >= this.position.getBoardHeight()) {
            String message = "Illegal height: " + height;
            throw new IllegalArgumentException(message);
        }
        else {this.height = height;}
    }

    void setWidth(byte width) {
        if (width < 0 || width >= this.position.getBoardWidth()) {
            String message = "Illegal width: " + width;
            throw new IllegalArgumentException(message);
        }
        else {this.width = width;}
    }

    public boolean correctFieldCoordinates(int height, int width) {
        if (height < 0 || height >= this.position.getBoardHeight()) {return false;}
        if (width < 0 || width >= this.position.getBoardWidth()) {return false;}
        return true;
    }

    public abstract ArrayList<Move> possibleMoves(PieceColor[][] colorMap);

    public Piece(byte height, byte width, EnginePosition pos) {
        this.height = height;
        this.width = width;
        this.position = pos;
    }
}
