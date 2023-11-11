package chessEngine.move;

import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.EngineMoveCode;
import chessEngine.chess.engineMove.field.Field;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoveRequestResponse {
    private byte fromY;
    private byte fromX;
    private byte toY;
    private byte toX;
    private byte moveCode;


    public EngineMove getEngineMove() {
        return new EngineMove(
                new Field(this.fromY, fromX),
                new Field(toY, toX),
                EngineMoveCode.fromInt(moveCode)
        );
    }

    @Override
    public String toString() {
        String code = "";
        code += String.valueOf(fromY);
        code += String.valueOf(fromX);
        code += String.valueOf(toY);
        code += String.valueOf(toX);
        code += String.valueOf(moveCode);
        return code;
    }
}
