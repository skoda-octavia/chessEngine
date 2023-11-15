package chessEngine.move;

import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.EngineMoveCode;
import chessEngine.chess.engineMove.field.Field;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class MoveRequestResponse {
    private int fromY;
    private int fromX;
    private int toY;
    private int toX;
    private int moveCode;


    public EngineMove generateEngineMove() {
        return new EngineMove(
                new Field((byte)fromY, (byte)fromX),
                new Field((byte)toY, (byte)toX),
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
