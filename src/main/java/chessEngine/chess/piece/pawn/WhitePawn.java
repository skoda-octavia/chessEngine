package chessEngine.chess.piece.pawn;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class WhitePawn extends Pawn{

    public WhitePawn(Field field, EnginePosition pos) {
        super(PieceColor.WHITE, pos, (byte)-1, (byte)6, (byte)3, (byte)0, "wP", field);
        if (!correctFieldCoordinates(field.getHeight(), field.getWidth())) {
            throw new IllegalArgumentException("illegal height or width in constructor: " + field);
        }
    }
}
