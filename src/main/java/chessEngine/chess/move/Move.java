package chessEngine.chess.move;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.Piece;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Move {
    private final Field from;
    private final Field to;
    private EnginePosition parentPosition;
    private String transformationPieceCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move move)) return false;
        return from.equals(move.from) && to.equals(move.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, parentPosition, transformationPieceCode);
    }

    public Move(Field from, Field to) {
        this.from = from;
        this.to = to;
    }
}
