package chessEngine.chess.engineMove;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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

    public static byte sgn(int x) {
        if (x > 0) {
            return (byte)1;
        } else if (x < 0) {
            return (byte)-1;
        } else {
            return (byte)0;
        }
    }

    public static ArrayList<Field> coveringLine(Field kingsField, Field attackingField) {
        ArrayList<Field> coveringLine = new ArrayList<>();
        byte yDiff = (byte)(kingsField.height() - attackingField.height());
        byte xDiff = (byte)(kingsField.width() - attackingField.width());
        if (Math.abs(yDiff) == 1 || Math.abs(xDiff) == 1) {return coveringLine;}
        byte ySgn = sgn(yDiff), xSgn = sgn(xDiff);
        byte nextY = (byte)(attackingField.height() + ySgn), nextX = (byte)(attackingField.width() + xSgn);
        Field nextField = new Field(nextY, nextX);
        do {
            coveringLine.add(nextField);
            nextY += ySgn; nextX += xSgn;
            nextField = new Field(nextY, nextX);
        } while (!nextField.equals(kingsField));
        return coveringLine;
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
