package chessEngine.chess.engineMove;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import chessEngine.move.MoveRequestResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter
@ToString
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
        byte yDiff = (byte)(kingsField.getHeight() - attackingField.getHeight());
        byte xDiff = (byte)(kingsField.getWidth() - attackingField.getWidth());
        if (Math.abs(yDiff) == 1 || Math.abs(xDiff) == 1) {return coveringLine;}
        byte ySgn = sgn(yDiff), xSgn = sgn(xDiff);
        byte nextY = (byte)(attackingField.getHeight() + ySgn), nextX = (byte)(attackingField.getWidth() + xSgn);
        Field nextField = new Field(nextY, nextX);
        do {
            coveringLine.add(nextField);
            nextY += ySgn; nextX += xSgn;
            nextField = new Field(nextY, nextX);
        } while (!nextField.equals(kingsField));
        return coveringLine;
    }


    public static String getNewPieceCode(EngineMoveCode engineMoveCode, PieceColor pieceColor) {
        String colorChar = pieceColor.equals(PieceColor.WHITE) ? "w" : "b";
        switch (engineMoveCode) {
            case QUEEN : return colorChar + "Q";
            case ROOK : return colorChar + "R";
            case BISHOP: return colorChar + "B";
            case KNIGHT: return colorChar + "k";
            default:
                throw new IllegalArgumentException("Invalid EngineMoveCode");
        }
    }

    public MoveRequestResponse getRequestResponse() {
        return new MoveRequestResponse(
                this.from.getHeight(),
                this.from.getWidth(),
                this.to.getHeight(),
                this.to.getWidth(),
                this.engineMoveCode.number
        );
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
