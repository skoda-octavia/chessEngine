package chessEngine.chessTest.engine;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EngineInCheckTest {


    @Test
    void inCheckTestStarting() {
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
        boolean whiteInCheck = enginePosition.inCheck(
                PieceColor.WHITE,
                enginePosition.getColorMap(),
                new Field((byte)7, (byte)4));
        boolean blackInCheck = enginePosition.inCheck(
                PieceColor.BLACK,
                enginePosition.getColorMap(),
                new Field((byte)0, (byte)4));
        assertFalse(whiteInCheck);
        assertFalse(blackInCheck);
    }

    @Test
    void inCheckTestInfiniteNPawn() {
        String posCode = "";
        posCode += "bRbkbBbQ  bBbkbR";
        posCode += "bP  bPbPbPbPbPbP";
        posCode += "  bP            ";
        posCode += "wK              ";
        posCode += "            bK  ";
        posCode += "                ";
        posCode += "wPwPwPwP  wPwPwP";
        posCode += "wRwkwBwQ  wBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        boolean whiteInCheck = enginePosition.inCheck(
                PieceColor.WHITE,
                enginePosition.getColorMap(),
                enginePosition.getWhiteKing().getField());
        boolean blackInCheck = enginePosition.inCheck(
                PieceColor.BLACK,
                enginePosition.getColorMap(),
                enginePosition.getBlackKing().getField());
        assertTrue(whiteInCheck);
        assertTrue(blackInCheck);
    }

    @Test
    void inCheckTestKings() {
        String posCode = "";
        posCode += "bRbkbBbQ  bBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "wK              ";
        posCode += "  bK            ";
        posCode += "                ";
        posCode += "wPwPwPwP  wPwPwP";
        posCode += "wRwkwBwQ  wBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        boolean whiteInCheck = enginePosition.inCheck(
                PieceColor.WHITE,
                enginePosition.getColorMap(),
                enginePosition.getWhiteKing().getField());
        boolean blackInCheck = enginePosition.inCheck(
                PieceColor.BLACK,
                enginePosition.getColorMap(),
                enginePosition.getBlackKing().getField());
        assertTrue(whiteInCheck);
        assertTrue(blackInCheck);
    }

    @Test
    void inCheckTestConstantNPawn() {
        String posCode = "";
        posCode += "bRbkbBbQ  bBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "    bk          ";
        posCode += "wK              ";
        posCode += "            bK  ";
        posCode += "              wP";
        posCode += "wPwPwPwP  wPwP  ";
        posCode += "wRwkwBwQ  wBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        boolean whiteInCheck = enginePosition.inCheck(
                PieceColor.WHITE,
                enginePosition.getColorMap(),
                enginePosition.getWhiteKing().getField());
        boolean blackInCheck = enginePosition.inCheck(
                PieceColor.BLACK,
                enginePosition.getColorMap(),
                enginePosition.getBlackKing().getField());
        assertTrue(whiteInCheck);
        assertTrue(blackInCheck);
    }

    @Test
    void inCheckTestConstantNBlocked() {
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
        boolean whiteInCheck = enginePosition.inCheck(
                PieceColor.WHITE,
                enginePosition.getColorMap(),
                enginePosition.getWhiteKing().getField());
        boolean blackInCheck = enginePosition.inCheck(
                PieceColor.BLACK,
                enginePosition.getColorMap(),
                enginePosition.getBlackKing().getField());
        assertFalse(whiteInCheck);
        assertTrue(blackInCheck);
    }
}
