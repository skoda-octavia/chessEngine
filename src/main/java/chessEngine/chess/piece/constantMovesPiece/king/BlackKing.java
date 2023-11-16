package chessEngine.chess.piece.constantMovesPiece.king;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class BlackKing extends King {
    public BlackKing(Field field, EnginePosition pos) {
        super(PieceColor.BLACK, pos, "bK", field);
        if (!correctFieldCoordinates(field.getHeight(), field.getWidth())) {
            throw new IllegalArgumentException("illegal height or width in constructor: " + field);
        }
    }
}
