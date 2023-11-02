package chessEngine.chessTest.engine.engineMove;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.field.Field;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoveringLineTest {

    @Test
    void coveringLineTestRook() {

        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "                ";
        posCode += "wK      bR      ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQ  wBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<Field> coveringLine = EngineMove.coveringLine(
                enginePosition.getWhiteKing().getField(),
                new Field((byte)4, (byte)4)
        );
        assertEquals(3, coveringLine.size());
    }
    @Test
    void coveringLineTestRookOpposite() {

        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "                ";
        posCode += "bR      wK      ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQ  wBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<Field> coveringLine = EngineMove.coveringLine(
                enginePosition.getWhiteKing().getField(),
                new Field((byte)4, (byte)0)
        );
        assertEquals(3, coveringLine.size());
    }

    @Test
    void coveringLineTestRookClose() {

        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "                ";
        posCode += "bR    bRwK      ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQ  wBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<Field> coveringLine = EngineMove.coveringLine(
                enginePosition.getWhiteKing().getField(),
                new Field((byte)4, (byte)3)
        );
        assertEquals(0, coveringLine.size());
    }

    @Test
    void coveringLineTestBishop() {

        String posCode = "";
        posCode += "bRbkbBbQ  bBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "    wB          ";
        posCode += "                ";
        posCode += "bK      bR      ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<Field> coveringLine = EngineMove.coveringLine(
                enginePosition.getBlackKing().getField(),
                new Field((byte)2, (byte)2)
        );
        assertEquals(1, coveringLine.size());
    }

    @Test
    void coveringLineTestBishopOpposite() {

        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbP  bPbPbPbP";
        posCode += "                ";
        posCode += "                ";
        posCode += "wB      bR      ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<Field> coveringLine = EngineMove.coveringLine(
                enginePosition.getBlackKing().getField(),
                new Field((byte)4, (byte)0)
        );
        assertEquals(3, coveringLine.size());
    }
    @Test
    void coveringLineTestBishopClose() {

        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPwBbPbPbPbP";
        posCode += "                ";
        posCode += "                ";
        posCode += "        bR      ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<Field> coveringLine = EngineMove.coveringLine(
                enginePosition.getBlackKing().getField(),
                new Field((byte)1, (byte)4)
        );
        assertEquals(0, coveringLine.size());
    }


}
