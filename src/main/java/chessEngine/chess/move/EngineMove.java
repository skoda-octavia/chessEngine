package chessEngine.chess.move;

import chessEngine.chess.move.field.Field;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class EngineMove {
    private final Field from;
    private final Field to;
    private EngineMoveCode engineMoveCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EngineMove engineMove)) return false;
        return from.equals(engineMove.from) && to.equals(engineMove.to) && engineMoveCode.equals(engineMove.engineMoveCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, engineMoveCode);
    }

    public EngineMove(Field from, Field to) {
        this.from = from;
        this.to = to;
        this.engineMoveCode = EngineMoveCode.NONE;
    }

    public EngineMove(Field from, Field to, EngineMoveCode engineMoveCode) {
        this.from = from;
        this.to = to;
        this.engineMoveCode = engineMoveCode;
    }
}
