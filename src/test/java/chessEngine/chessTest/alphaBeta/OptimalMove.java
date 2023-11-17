package chessEngine.chessTest.alphaBeta;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.EngineMoveCode;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.tree.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptimalMove {


    @Test
    void queenCaptureTest() {
        String posCode = "";
        posCode += "bRbkbB  bKbBbkbR";
        posCode += "bPbPbP  bPbPbPbP";
        posCode += "                ";
        posCode += "        bQ      ";
        posCode += "      wP        ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        Node primeNode = new Node(enginePosition, 4);
        primeNode.generateChildrenNodes();
        EngineMove optimalMove = primeNode.findOptimalChildMove();
        assertEquals(
                new EngineMove(
                        new Field((byte)4, (byte)3),
                        new Field((byte)3, (byte)4),
                        EngineMoveCode.NONE
                ),
                optimalMove
        );
    }
}
