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
    private MoveCode moveCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move move)) return false;
        return from.equals(move.from) && to.equals(move.to) && moveCode.equals(move.moveCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, moveCode);
    }

    public Move(Field from, Field to) {
        this.from = from;
        this.to = to;
        this.moveCode = MoveCode.NONE;
    }

    public Move(Field from, Field to, MoveCode moveCode) {
        this.from = from;
        this.to = to;
        this.moveCode = moveCode;
    }
}
