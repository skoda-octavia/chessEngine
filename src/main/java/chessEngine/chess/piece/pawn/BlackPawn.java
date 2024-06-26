package chessEngine.chess.piece.pawn;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class BlackPawn extends Pawn {

    public BlackPawn(Field field, EnginePosition pos) {
        super(PieceColor.BLACK, pos, (byte)1, (byte)1, (byte)4, (byte)7, "bP", field);
        if (!correctFieldCoordinates(field.getHeight(), field.getWidth())) {
            throw new IllegalArgumentException("illegal height or width in constructor: " + field);
        }
    }
}
