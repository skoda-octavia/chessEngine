package chessEngine.chessTest.engine;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.Move;
import chessEngine.chess.move.field.Field;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EngineUnveilingMoveTest {

    @Test
    void unveilingMoveTest() {
        String posCode = "";
        posCode += "bRbkbBbQ  bBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "    bB          ";
        posCode += "  wP            ";
        posCode += "wK          bKwR";
        posCode += "                ";
        posCode += "wPwPwPwP  wPwP  ";
        posCode += "wRwkwBwQ  wBwk  ";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        Move pawnCaptureMove = new Move(
                new Field((byte)3, (byte)1),
                new Field((byte)2, (byte)2)
        );
        Move pawnMovesForward = new Move(
                new Field((byte)3, (byte)1),
                new Field((byte)2, (byte)1)
        );
        boolean pawnCaptureUnveiling = enginePosition.unveilingMove(pawnCaptureMove);
        boolean pawnMovesUnveiling = enginePosition.unveilingMove(pawnMovesForward);
        assertEquals(pawnMovesUnveiling, true);
        assertEquals(pawnCaptureUnveiling, false);

    }
}
