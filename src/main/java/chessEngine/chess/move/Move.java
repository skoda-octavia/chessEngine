package chessEngine.chess.move;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.Piece;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Move {
    private final Field from;
    private final Field to;
    private EnginePosition parentPosition;
    private String transformationPieceCode;

    public Move(Field from, Field to) {
        this.from = from;
        this.to = to;
    }
}
