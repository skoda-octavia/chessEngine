package chessEngine.chess.piece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.Move;
import chessEngine.chess.move.field.Field;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public abstract class Piece {
    protected Field field;
    protected final EnginePosition position;
    protected final PieceColor pieceColor;


    public boolean correctFieldCoordinates(int height, int width) {
        if (height < 0 || height >= this.position.getBoardHeight()) {return false;}
        if (width < 0 || width >= this.position.getBoardWidth()) {return false;}
        return true;
    }

    public abstract ArrayList<Move> possibleMoves(PieceColor[][] colorMap);

    public Piece(PieceColor pieceColor, EnginePosition pos) {
        this.pieceColor = pieceColor;
        this.position = pos;
    }
}
