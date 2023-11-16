package chessEngine.chess.piece.infiniteRangePiece.rook;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class BlackRook extends Rook {

    public BlackRook(Field field, EnginePosition pos) {
        super(PieceColor.BLACK, pos, "bR", field);
        if (!correctFieldCoordinates(field.getHeight(), field.getWidth())) {
            throw new IllegalArgumentException("illegal height or width in constructor: " + field);
        }
    }
}
