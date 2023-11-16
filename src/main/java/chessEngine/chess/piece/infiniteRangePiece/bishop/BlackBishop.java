package chessEngine.chess.piece.infiniteRangePiece.bishop;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class BlackBishop extends Bishop {

    public BlackBishop(Field field, EnginePosition pos) {

        super(PieceColor.BLACK, pos, "bB", field);
        if (!correctFieldCoordinates(field.getHeight(), field.getWidth())) {
            throw new IllegalArgumentException("illegal height or width in constructor: " + field);
        }
    }
}
