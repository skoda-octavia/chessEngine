package chessEngine.chess.piece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@Getter
@Setter
public abstract class Piece {
    protected final EnginePosition position;
    protected final PieceColor pieceColor;
    protected final String pieceCode;
    protected  final Field field;

    protected HashSet<Field> controlledFields = null;
    protected ArrayList<EngineMove> possibleMoves = null;
    protected InfiniteRangePiece pinningPiece = null;

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

    public void setFieldMap(HashMap<Field, Byte> map) {
        for(Field field : controlledFields) {
            byte number = map.getOrDefault(field, (byte)0);
            number++;
            map.put(field, number);
        }
    }

    public Piece(PieceColor pieceColor, EnginePosition position, String pieceCode, Field field) {
        this.position = position;
        this.pieceColor = pieceColor;
        this.pieceCode = pieceCode;
        this.field = field;
    }
}
