package chessEngine.chessTest.engine.possibleCheckMoves;

import chessEngine.chess.EnginePosition;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PossibleStandardMovesTest {

    @Test
    void startingPosTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList possibleMove = enginePosition.possibleLegalMoves();
        assertEquals(20, possibleMove.size());
    }

    @Test
    void startingPosBlackTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        ArrayList possibleMove = enginePosition.possibleLegalMoves();
        assertEquals(20, possibleMove.size());
    }


    @Test
    void midGameTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbP  bPbPbPbP";
        posCode += "                ";
        posCode += "      bP        ";
        posCode += "    wB  wP      ";
        posCode += "  wP      wk    ";
        posCode += "wPwBwPwP  wPwPwP";
        posCode += "wRwk  wQwK    wR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList possibleMove = enginePosition.possibleLegalMoves();
        assertEquals(39, possibleMove.size());
    }
    @Test
    void midGamePawnPinnedTest() {
        String posCode = "";
        posCode += "bRbkbB  bKbBbkbR";
        posCode += "bPbPbP  bPbPbPbP";
        posCode += "        bQ      ";
        posCode += "      bP        ";
        posCode += "    wB  wP      ";
        posCode += "  wP      wkwPbB";
        posCode += "wPwBwPwP  wP  wP";
        posCode += "wRwk  wQwK    wR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList possibleMove = enginePosition.possibleLegalMoves();
        assertEquals(33, possibleMove.size());
    }


    @Test
    void midGameQueenPinnedTest() {
        String posCode = "";
        posCode += "bRbkbB  bKbBbkbR";
        posCode += "bPbPbP  bPbPbPbP";
        posCode += "        bQ      ";
        posCode += "      bP        ";
        posCode += "    wB  wQ      ";
        posCode += "  wP      wkwPbB";
        posCode += "wPwBwPwP  wP  wP";
        posCode += "wRwk    wK    wR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList possibleMove = enginePosition.possibleLegalMoves();
        assertEquals(35, possibleMove.size());
    }

    @Test
    void lateGamePawnPinnedTest() {
        String posCode = "";
        posCode += "bRbkbB  bKbBbkbR";
        posCode += "bPbPbP  bPbPbPbP";
        posCode += "        bQ      ";
        posCode += "      wP        ";
        posCode += "        bR      ";
        posCode += "  wK          bB";
        posCode += "        bR    wP";
        posCode += "                ";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList possibleMove = enginePosition.possibleLegalMoves();
        assertEquals(3, possibleMove.size());
    }
}
