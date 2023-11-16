package chessEngine.chess.piece.infiniteRangePiece.queen;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class BlackQueen extends Queen {
    public BlackQueen(Field field, EnginePosition pos) {

        super(PieceColor.BLACK, pos, "bQ", field);
        if (!correctFieldCoordinates(field.getHeight(), field.getWidth())) {
            throw new IllegalArgumentException("illegal height or width in constructor: " + field);
        }
    }
}
