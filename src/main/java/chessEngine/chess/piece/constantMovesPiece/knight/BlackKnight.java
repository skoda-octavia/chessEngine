package chessEngine.chess.piece.constantMovesPiece.knight;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class BlackKnight extends Knight{

    public BlackKnight(Field field, EnginePosition pos) {
        super(PieceColor.BLACK, pos, "bk", field);
        if (!correctFieldCoordinates(field.getHeight(), field.getWidth())) {
            throw new IllegalArgumentException("illegal height or width in constructor: " + field);
        }
    }
}
