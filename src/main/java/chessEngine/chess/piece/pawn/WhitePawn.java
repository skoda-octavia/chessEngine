package chessEngine.chess.piece.pawn;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhitePawn extends Pawn{

    public WhitePawn(Field field, EnginePosition pos) {
        super(PieceColor.WHITE, pos, (byte)-1, (byte)6);
        if (!correctFieldCoordinates(field.height(), field.width())) {
            throw new IllegalArgumentException("illegal height or width in constructor: " + field);
        }
        this.field = field;
    }
}
