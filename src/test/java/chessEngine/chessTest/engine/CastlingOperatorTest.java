package chessEngine.chessTest.engine;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.EngineMoveCode;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CastlingOperatorTest {

    @Test
    void castlingOperatorDefaultTest() {
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
        ArrayList<EngineMove> whiteCastlingMoves =  enginePosition.getCastlingOperator()
                .possibleCastlingMoves(PieceColor.WHITE);
        ArrayList<EngineMove> blackCastlingMoves =  enginePosition.getCastlingOperator()
                .possibleCastlingMoves(PieceColor.BLACK);
        assertEquals(0, whiteCastlingMoves.size());
        assertEquals(0, blackCastlingMoves.size());
    }


    @Test
    void castlingOperatorBothTest() {
        String posCode = "";
        posCode += "bR      bK    bR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wR      wK    wR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<EngineMove> whiteCastlingMoves =  enginePosition.getCastlingOperator()
                .possibleCastlingMoves(PieceColor.WHITE);
        ArrayList<EngineMove> blackCastlingMoves =  enginePosition.getCastlingOperator()
                .possibleCastlingMoves(PieceColor.BLACK);
        assertEquals(2, whiteCastlingMoves.size());
        assertEquals(2, blackCastlingMoves.size());
        assertTrue(
                whiteCastlingMoves.contains(
                        new EngineMove(
                                new Field((byte)7, (byte)4),
                                new Field((byte)7, (byte)2),
                                EngineMoveCode.LEFTCASTLING
                        )
                )
        );
        assertTrue(
                whiteCastlingMoves.contains(
                        new EngineMove(
                                new Field((byte)7, (byte)4),
                                new Field((byte)7, (byte)6),
                                EngineMoveCode.RIGHTCASTLING
                        )
                )
        );
    }

    @Test
    void castlingOperatorOccupiedNControlledTest() {
        String posCode = "";
        posCode += "bRbk    bK    bR";
        posCode += "bPbPbPbPbPbP  bP";
        posCode += "                ";
        posCode += "            wR  ";
        posCode += "                ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPbBwP";
        posCode += "wR      wK    wR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<EngineMove> whiteCastlingMoves =  enginePosition.getCastlingOperator()
                .possibleCastlingMoves(PieceColor.WHITE);
        ArrayList<EngineMove> blackCastlingMoves =  enginePosition.getCastlingOperator()
                .possibleCastlingMoves(PieceColor.BLACK);
        assertEquals(1, whiteCastlingMoves.size());
        assertEquals(0, blackCastlingMoves.size());
        assertTrue(
                whiteCastlingMoves.contains(
                        new EngineMove(
                                new Field((byte)7, (byte)4),
                                new Field((byte)7, (byte)2),
                                EngineMoveCode.LEFTCASTLING
                        )
                )
        );
    }

    @Test
    void castlingOperatorOccupiedByKingTest() {
        String posCode = "";
        posCode += "bR      bK    bR";
        posCode += "bPwKbPbPbPbP  bP";
        posCode += "                ";
        posCode += "            wR  ";
        posCode += "                ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPbBwP";
        posCode += "wR            wR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<EngineMove> whiteCastlingMoves =  enginePosition.getCastlingOperator()
                .possibleCastlingMoves(PieceColor.WHITE);
        ArrayList<EngineMove> blackCastlingMoves =  enginePosition.getCastlingOperator()
                .possibleCastlingMoves(PieceColor.BLACK);
        assertEquals(0, whiteCastlingMoves.size());
        assertEquals(0, blackCastlingMoves.size());
    }

    @Test
    void castlingOperatorOccupiedByBlackKingTest() {
        String posCode = "";
        posCode += "bR            bR";
        posCode += "bP  bPbPbPbP  bP";
        posCode += "                ";
        posCode += "            wR  ";
        posCode += "                ";
        posCode += "                ";
        posCode += "wPbKwPwPwPwPbBwP";
        posCode += "wR      wK    wR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        ArrayList<EngineMove> whiteCastlingMoves =  enginePosition.getCastlingOperator()
                .possibleCastlingMoves(PieceColor.WHITE);
        ArrayList<EngineMove> blackCastlingMoves =  enginePosition.getCastlingOperator()
                .possibleCastlingMoves(PieceColor.BLACK);
        assertEquals(0, whiteCastlingMoves.size());
        assertEquals(0, blackCastlingMoves.size());
    }

}
