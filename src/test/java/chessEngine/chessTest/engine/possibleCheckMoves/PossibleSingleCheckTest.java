package chessEngine.chessTest.engine.possibleCheckMoves;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.infiniteRangePiece.bishop.WhiteBishop;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PossibleSingleCheckTest {


    @Test
    void singleCheckRookTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "    bB  bR      ";
        posCode += "  wP      wB    ";
        posCode += "                ";
        posCode += "                ";
        posCode += "wPwPwPwP  wPwP  ";
        posCode += "wRwkwB  wKwB    ";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<EngineMove> legalMove = enginePosition.possibleLegalMoves();
        assertEquals(4, legalMove.size());
    }

    @Test
    void singleCheckCapturingRookTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "    bB          ";
        posCode += "  wP      wB    ";
        posCode += "                ";
        posCode += "    wk          ";
        posCode += "wPwPwPwPbRwPwP  ";
        posCode += "wRwkwB  wKwB    ";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<EngineMove> legalMove = enginePosition.possibleLegalMoves();
        assertEquals(4, legalMove.size());
    }

    @Test
    void singleCheckBishopTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "              bB";
        posCode += "  wP      wB    ";
        posCode += "      wP        ";
        posCode += "      wPwK      ";
        posCode += "wPwPwP    wPwP  ";
        posCode += "wRwkwB    wB  wR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<EngineMove> legalMove = enginePosition.possibleLegalMoves();
        assertEquals(5, legalMove.size());
    }

    @Test
    void singleCheckBishopCloseTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "              bB";
        posCode += "  wP      wB    ";
        posCode += "      wP  bB    ";
        posCode += "      wPwK      ";
        posCode += "wPwPwP    wPwP  ";
        posCode += "wRwkwB    wB  wR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<EngineMove> legalMove = enginePosition.possibleLegalMoves();
        assertEquals(3, legalMove.size());
    }

    @Test
    void singleCheckQueenTest() {
        String posCode = "";
        posCode += "bRbkbBbQ  bBbkbR";
        posCode += "  bPbPbPbPbP  bP";
        posCode += "  bK            ";
        posCode += "                ";
        posCode += "        bk      ";
        posCode += "            bP  ";
        posCode += "  wPwPwPwPwQwP  ";
        posCode += "wRwkwB  wKwB    ";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        ArrayList<EngineMove> legalMove = enginePosition.possibleLegalMoves();
        assertEquals(6, legalMove.size());
    }

    @Test
    void singleCheckQueenVerticaTest() {
        String posCode = "";
        posCode += "bRbkbB    bBbkbR";
        posCode += "  bPbPbPbPbP  bP";
        posCode += "  bK            ";
        posCode += "    bQ          ";
        posCode += "        bk      ";
        posCode += "  wQ        bP  ";
        posCode += "  wPwPwPwP  wP  ";
        posCode += "wRwkwB  wKwB    ";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        ArrayList<EngineMove> legalMove = enginePosition.possibleLegalMoves();
        assertEquals(3, legalMove.size());
    }


    @Test
    void singleCheckQueenPinnedTest() {
        String posCode = "";
        posCode += "bRbkbB    bBbkbR";
        posCode += "  bPbPbPbPbP  bP";
        posCode += "  bK            ";
        posCode += "    bQ          ";
        posCode += "      wBbk      ";
        posCode += "  wQ        bP  ";
        posCode += "  wPwPwPwP  wP  ";
        posCode += "wRwkwB  wKwB    ";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        ArrayList<EngineMove> legalMove = enginePosition.possibleLegalMoves();
        assertEquals(1, legalMove.size());
    }
    @Test
    void singleCheckQueenPinnedPawnTest() {
        String posCode = "";
        posCode += "bRbkbB    bBbkbR";
        posCode += "  bPbPbPbPbP  bP";
        posCode += "  bK            ";
        posCode += "    bP          ";
        posCode += "  wQ    bk      ";
        posCode += "            bP  ";
        posCode += "  wPwPwPwPwBwP  ";
        posCode += "wRwkwB  wKwB    ";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        ArrayList<EngineMove> legalMove = enginePosition.possibleLegalMoves();
        assertEquals(1, legalMove.size());
    }


    @Test
    void singleCheckPawnTest() {
        String posCode = "";
        posCode += "bR      wK      ";
        posCode += "  wP        bR  ";
        posCode += "                ";
        posCode += "    bP          ";
        posCode += "        bk      ";
        posCode += "            bP  ";
        posCode += "  wPwPwPwPwBwPbK";
        posCode += "  wkwB    wB    ";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<EngineMove> legalMove = enginePosition.possibleLegalMoves();
        assertEquals(8, legalMove.size());
    }

    @Test
    void singleCheckPawnPinnedTest() {
        String posCode = "";
        posCode += "bR  wK          ";
        posCode += "  wP        bR  ";
        posCode += "bB              ";
        posCode += "    bP          ";
        posCode += "        bk      ";
        posCode += "            bP  ";
        posCode += "  wPwPwPwPwBwPbK";
        posCode += "  wkwB    wB    ";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<EngineMove> legalMove = enginePosition.possibleLegalMoves();
        assertEquals(0, legalMove.size());
    }

    @Test
    void singleCheckPawnSecTest() {
        String posCode = "";
        posCode += "bRwK            ";
        posCode += "  wP        bR  ";
        posCode += "    wR          ";
        posCode += "    bP          ";
        posCode += "        bk      ";
        posCode += "            bP  ";
        posCode += "  wPwPwPwPwBwPbK";
        posCode += "  wkwB    wB    ";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<EngineMove> legalMove = enginePosition.possibleLegalMoves();
        assertEquals(5, legalMove.size());
    }

    @Test
    void singleCheckPawnCheckingTest() {
        String posCode = "";
        posCode += "    bK          ";
        posCode += "  wP        wR  ";
        posCode += "                ";
        posCode += "    bP          ";
        posCode += "        bk      ";
        posCode += "            bP  ";
        posCode += "  wPwPwPwPwBwPbk";
        posCode += "  wkwB  wKwB    ";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        ArrayList<EngineMove> legalMove = enginePosition.possibleLegalMoves();
        assertEquals(2, legalMove.size());
    }

    @Test
    void singleCheckPawnToCaptureCheckingTest() {
        String posCode = "";
        posCode += "    bK          ";
        posCode += "  wP        wR  ";
        posCode += "  bR            ";
        posCode += "    bP          ";
        posCode += "        bk      ";
        posCode += "            bP  ";
        posCode += "  wPwPwPwPwBwPbk";
        posCode += "  wkwB  wKwB    ";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        ArrayList<EngineMove> legalMove = enginePosition.possibleLegalMoves();
        assertEquals(3, legalMove.size());
    }

    @Test
    void singleCheckKnightCheckingTest() {
        String posCode = "";
        posCode += "    bK          ";
        posCode += "            wR  ";
        posCode += "  wk            ";
        posCode += "    bP          ";
        posCode += "        bk      ";
        posCode += "            bP  ";
        posCode += "  wPwPwPwPwBwPbk";
        posCode += "  wkwB  wKwB    ";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        ArrayList<EngineMove> legalMove = enginePosition.possibleLegalMoves();
        assertEquals(2, legalMove.size());
    }


}
