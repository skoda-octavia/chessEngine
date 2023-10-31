package chessEngine.chess.piece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.EngineMove;
import chessEngine.chess.move.field.Field;
import chessEngine.move.Move;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;

@Getter
@Setter
public abstract class Piece {
    protected Field field;
    protected final EnginePosition position;
    protected final PieceColor pieceColor;
    protected HashSet<Field> controlledFields = null;
    protected ArrayList<EngineMove> possibleMoves = null;
    protected byte[] pinnedDirection;

    public boolean correctFieldCoordinates(int height, int width) {
        if (height < 0 || height >= this.position.getBoardHeight()) {return false;}
        if (width < 0 || width >= this.position.getBoardWidth()) {return false;}
        return true;
    }

    public abstract void setMyPossibilities(PieceColor[][] colorMap);

    public PieceColor enemyPieceColor() {
        if (this.pieceColor.equals(PieceColor.WHITE)) {return PieceColor.BLACK;}
        return PieceColor.WHITE;
    }

    public Piece(PieceColor pieceColor, EnginePosition pos) {
        this.pieceColor = pieceColor;
        this.position = pos;
    }
}
