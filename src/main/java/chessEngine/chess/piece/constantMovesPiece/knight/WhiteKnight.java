package chessEngine.chess.piece.constantMovesPiece.knight;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class WhiteKnight extends Knight{
    private final PieceColor pieceColor = PieceColor.WHITE;

    public WhiteKnight(Field field, EnginePosition pos) {
        super(PieceColor.WHITE, pos, "wk", field);
        if (!correctFieldCoordinates(field.height(), field.width())) {
            throw new IllegalArgumentException("illegal height or width in constructor: " + field);
        }
    }
}
