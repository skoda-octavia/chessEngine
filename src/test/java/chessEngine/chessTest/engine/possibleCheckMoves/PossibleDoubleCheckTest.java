package chessEngine.chessTest.engine.possibleCheckMoves;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.field.Field;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PossibleDoubleCheckTest {

    @Test
    void checkRookNBishop () {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "    bB          ";
        posCode += "                ";
        posCode += "wK            bR";
        posCode += "        bR      ";
        posCode += "wPwPwPwP  wPwP  ";
        posCode += "wRwkwBwQ  wBwk  ";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<EngineMove> validKingsMoves = enginePosition.possibleLegalMoves();
        assertEquals(1, validKingsMoves.size());
        assertTrue(
                validKingsMoves.contains(
                        new EngineMove(
                                new Field((byte)4, (byte)0),
                                new Field((byte)3, (byte)0)
                        )
                )
        );
    }


    @Test
    void checkRookNKnight () {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "  bk            ";
        posCode += "                ";
        posCode += "wK            bR";
        posCode += "        bR      ";
        posCode += "wPwPwPwP  wPwP  ";
        posCode += "wRwkwBwQ  wBwk  ";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<EngineMove> validKingsMoves = enginePosition.possibleLegalMoves();
        assertEquals(2, validKingsMoves.size());
        assertTrue(
                validKingsMoves.contains(
                        new EngineMove(
                                new Field((byte)4, (byte)0),
                                new Field((byte)3, (byte)0)
                        )
                )
        );
        assertTrue(
                validKingsMoves.contains(
                        new EngineMove(
                                new Field((byte)4, (byte)0),
                                new Field((byte)3, (byte)1)
                        )
                )
        );
    }

    @Test
    void checkQueenNRook () {
        String posCode = "";
        posCode += "bRbkbBbQ  bBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "  wQ            ";
        posCode += "bK            wR";
        posCode += "  bP    bR      ";
        posCode += "wP  wPwPwPwPwP  ";
        posCode += "wR    wQwKwBwk  ";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        ArrayList<EngineMove> validKingsMoves = enginePosition.possibleLegalMoves();
        assertEquals(2, validKingsMoves.size());
        assertTrue(
                validKingsMoves.contains(
                        new EngineMove(
                                new Field((byte)4, (byte)0),
                                new Field((byte)3, (byte)1)
                        )
                )
        );
        assertTrue(
                validKingsMoves.contains(
                        new EngineMove(
                                new Field((byte)4, (byte)0),
                                new Field((byte)5, (byte)0)
                        )
                )
        );
    }

    @Test
    void checkQueenNRookDefended () {
        String posCode = "";
        posCode += "bRbkbBbQ  bBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "  wQ        wR  ";
        posCode += "bK            wR";
        posCode += "  bP    bR      ";
        posCode += "wP  wPwPwPwPwP  ";
        posCode += "wR    wQwKwBwk  ";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        ArrayList<EngineMove> validKingsMoves = enginePosition.possibleLegalMoves();
        assertEquals(1, validKingsMoves.size());
        assertTrue(
                validKingsMoves.contains(
                        new EngineMove(
                                new Field((byte)4, (byte)0),
                                new Field((byte)5, (byte)0)
                        )
                )
        );
    }


    @Test
    void checkQueenNRookNoMoves () {
        String posCode = "";
        posCode += "bRbkbBbQ  bBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "  wQ        wR  ";
        posCode += "bK            wR";
        posCode += "wPbP    bR      ";
        posCode += "    wPwPwPwPwP  ";
        posCode += "wR    wQwKwBwk  ";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        ArrayList<EngineMove> validKingsMoves = enginePosition.possibleLegalMoves();
        assertEquals(0, validKingsMoves.size());
    }

}
