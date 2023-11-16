package chessEngine.chess.piece.infiniteRangePiece.rook;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class WhiteRook extends Rook {

    public WhiteRook(Field field, EnginePosition pos) {
        super(PieceColor.WHITE, pos, "wR", field);
        if (!correctFieldCoordinates(field.getHeight(), field.getWidth())) {
            throw new IllegalArgumentException("illegal height or width in constructor: " + field);
        }
    }
}
