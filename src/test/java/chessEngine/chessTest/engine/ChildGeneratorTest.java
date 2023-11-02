package chessEngine.chessTest.engine;


import chessEngine.chess.ChildGenerator;
import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.EngineMoveCode;
import chessEngine.chess.engineMove.field.Field;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChildGeneratorTest {

    @Test
    void generateCastlingWhiteLeft() {
        String posCode = "";
        posCode += "bR      bK    bR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "                ";
        posCode += "        wB      ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wR      wK    wR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        Field kingsField = enginePosition.getWhiteKing().getField();
        EngineMove leftCastling = new EngineMove(
                kingsField,
                new Field((byte)7, (byte)2),
                EngineMoveCode.CASTLING
        );
        EnginePosition newPos = ChildGenerator.generateChild(enginePosition, leftCastling);
        String posCodeN = "";
        posCodeN += "bR      bK    bR";
        posCodeN += "bPbPbPbPbPbPbPbP";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "        wB      ";
        posCodeN += "                ";
        posCodeN += "wPwPwPwPwPwPwPwP";
        posCodeN += "    wKwR      wR";
        assertEquals(newPos.getPositionCode(), posCodeN);
        assertTrue(newPos.isWhiteKingMoved());
    }

    @Test
    void generateCastlingWhiteRight() {
        String posCode = "";
        posCode += "bR      bK    bR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "  wQ            ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wR      wK    wR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        Field kingsField = enginePosition.getWhiteKing().getField();
        EngineMove rightCastling = new EngineMove(
                kingsField,
                new Field((byte)7, (byte)6),
                EngineMoveCode.CASTLING
        );
        EnginePosition newPos = ChildGenerator.generateChild(enginePosition, rightCastling);
        String posCodeN = "";
        posCodeN += "bR      bK    bR";
        posCodeN += "bPbPbPbPbPbPbPbP";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "  wQ            ";
        posCodeN += "wPwPwPwPwPwPwPwP";
        posCodeN += "wR        wRwK  ";
        assertEquals(newPos.getPositionCode(), posCodeN);
        assertTrue(newPos.isWhiteKingMoved());
    }

    @Test
    void generateCastlingBlackRight() {
        String posCode = "";
        posCode += "bR      bK    bR";
        posCode += "bPbPbPbPbP  bPbP";
        posCode += "                ";
        posCode += "          bP    ";
        posCode += "                ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wR      wK    wR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        Field kingsField = enginePosition.getBlackKing().getField();
        EngineMove rightCastling = new EngineMove(
                kingsField,
                new Field((byte)0, (byte)6),
                EngineMoveCode.CASTLING
        );
        EnginePosition newPos = ChildGenerator.generateChild(enginePosition, rightCastling);
        String posCodeN = "";
        posCodeN += "bR        bRbK  ";
        posCodeN += "bPbPbPbPbP  bPbP";
        posCodeN += "                ";
        posCodeN += "          bP    ";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "wPwPwPwPwPwPwPwP";
        posCodeN += "wR      wK    wR";
        assertEquals(newPos.getPositionCode(), posCodeN);
        assertTrue(newPos.isBlackKingMoved());
    }

    @Test
    void generateCastlingBlackLeft() {
        String posCode = "";
        posCode += "bR      bK    bR";;
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "      bk        ";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wR      wK    wR";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        Field kingsField = enginePosition.getBlackKing().getField();
        EngineMove leftCastling = new EngineMove(
                kingsField,
                new Field((byte)0, (byte)2),
                EngineMoveCode.CASTLING
        );
        EnginePosition newPos = ChildGenerator.generateChild(enginePosition, leftCastling);
        String posCodeN = "";
        posCodeN += "    bKbR      bR";
        posCodeN += "bPbPbPbPbPbPbPbP";
        posCodeN += "      bk        ";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "wPwPwPwPwPwPwPwP";
        posCodeN += "wR      wK    wR";
        assertEquals(newPos.getPositionCode(), posCodeN);
        assertTrue(newPos.isBlackKingMoved());
        assertTrue(newPos.isWhiteMoves());
    }

    @Test
    void transformationQueen() {
        String posCode = "";
        posCode += "                ";
        posCode += "wP              ";
        posCode += "                ";
        posCode += "                ";
        posCode += "bK              ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        EngineMove move = new EngineMove(
                new Field((byte)1, (byte)0),
                new Field((byte)0, (byte)0),
                EngineMoveCode.QUEEN
        );
        EnginePosition newPos = ChildGenerator.generateChild(enginePosition, move);
        String posCodeN = "";
        posCodeN += "wQ              ";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "bK              ";
        posCodeN += "                ";
        posCodeN += "wPwPwPwPwPwPwPwP";
        posCodeN += "wRwkwBwQwKwBwkwR";
        assertEquals(newPos.getPositionCode(), posCodeN);
        assertFalse(newPos.isWhiteMoves());
    }
    @Test
    void transformationBishop() {
        String posCode = "";
        posCode += "  bR            ";
        posCode += "wP              ";
        posCode += "                ";
        posCode += "                ";
        posCode += "bK              ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        EngineMove move = new EngineMove(
                new Field((byte)1, (byte)0),
                new Field((byte)0, (byte)1),
                EngineMoveCode.BISHOP
        );
        EnginePosition newPos = ChildGenerator.generateChild(enginePosition, move);
        String posCodeN = "";
        posCodeN += "  wB            ";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "bK              ";
        posCodeN += "                ";
        posCodeN += "wPwPwPwPwPwPwPwP";
        posCodeN += "wRwkwBwQwKwBwkwR";
        assertEquals(newPos.getPositionCode(), posCodeN);
        assertFalse(newPos.isWhiteMoves());
    }

    @Test
    void transformationRook() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "              wK";
        posCode += "                ";
        posCode += "                ";
        posCode += "  bP            ";
        posCode += "  wRwQ          ";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        EngineMove move = new EngineMove(
                new Field((byte)6, (byte)1),
                new Field((byte)7, (byte)2),
                EngineMoveCode.ROOK
        );
        EnginePosition newPos = ChildGenerator.generateChild(enginePosition, move);
        String posCodeN = "";
        posCodeN += "bRbkbBbQbKbBbkbR";
        posCodeN += "bPbPbPbPbPbPbPbP";
        posCodeN += "                ";
        posCodeN += "              wK";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "  wRbR          ";
        assertEquals(newPos.getPositionCode(), posCodeN);
        assertTrue(newPos.isWhiteMoves());
    }
    @Test
    void enPassantWhite() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbP  bPbPbPbP";
        posCode += "                ";
        posCode += "    wPbP      wK";
        posCode += "                ";
        posCode += "                ";
        posCode += "  bP            ";
        posCode += "  wRwQ          ";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        EngineMove move = new EngineMove(
                new Field((byte)3, (byte)2),
                new Field((byte)2, (byte)3),
                EngineMoveCode.ENPASSANT
        );
        EnginePosition newPos = ChildGenerator.generateChild(enginePosition, move);
        String posCodeN = "";
        posCodeN += "bRbkbBbQbKbBbkbR";
        posCodeN += "bPbPbP  bPbPbPbP";
        posCodeN += "      wP        ";
        posCodeN += "              wK";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "  bP            ";
        posCodeN += "  wRwQ          ";
        assertEquals(newPos.getPositionCode(), posCodeN);
        assertFalse(newPos.isWhiteMoves());
    }

    @Test
    void enPassantBlack() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbP  bPbPbPbP";
        posCode += "                ";
        posCode += "              wK";
        posCode += "  bPwP          ";
        posCode += "                ";
        posCode += "                ";
        posCode += "  wRwQ          ";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        EngineMove move = new EngineMove(
                new Field((byte)4, (byte)2),
                new Field((byte)5, (byte)1),
                EngineMoveCode.ENPASSANT
        );
        EnginePosition newPos = ChildGenerator.generateChild(enginePosition, move);
        String posCodeN = "";
        posCodeN += "bRbkbBbQbKbBbkbR";
        posCodeN += "bPbPbP  bPbPbPbP";
        posCodeN += "                ";
        posCodeN += "              wK";
        posCodeN += "                ";
        posCodeN += "  wP            ";
        posCodeN += "                ";
        posCodeN += "  wRwQ          ";
        assertEquals(newPos.getPositionCode(), posCodeN);
        assertFalse(newPos.isWhiteMoves());
    }

    @Test
    void capturing() {
        String posCode = "";
        posCode += "  bR  wR        ";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "bK              ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        EngineMove move = new EngineMove(
                new Field((byte)0, (byte)3),
                new Field((byte)0, (byte)1)
        );
        EnginePosition newPos = ChildGenerator.generateChild(enginePosition, move);
        String posCodeN = "";
        posCodeN += "  wR            ";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "bK              ";
        posCodeN += "                ";
        posCodeN += "wPwPwPwPwPwPwPwP";
        posCodeN += "wRwkwBwQwKwBwkwR";
        assertEquals(newPos.getPositionCode(), posCodeN);
        assertFalse(newPos.isWhiteMoves());
    }

    @Test
    void base() {
        String posCode = "";
        posCode += "  bR  wR        ";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "bK              ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        EngineMove move = new EngineMove(
                new Field((byte)0, (byte)3),
                new Field((byte)0, (byte)2)
        );
        EnginePosition newPos = ChildGenerator.generateChild(enginePosition, move);
        String posCodeN = "";
        posCodeN += "  bRwR          ";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "                ";
        posCodeN += "bK              ";
        posCodeN += "                ";
        posCodeN += "wPwPwPwPwPwPwPwP";
        posCodeN += "wRwkwBwQwKwBwkwR";
        assertEquals(newPos.getPositionCode(), posCodeN);
        assertFalse(newPos.isWhiteMoves());
    }
}


