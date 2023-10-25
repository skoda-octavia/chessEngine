package chessEngine.chessTest.engine;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.EngineMove;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.constantMovesPiece.king.BlackKing;
import chessEngine.chess.piece.constantMovesPiece.knight.BlackKnight;
import chessEngine.chess.piece.infiniteRangePiece.bishop.WhiteBishop;
import chessEngine.chess.piece.infiniteRangePiece.queen.WhiteQueen;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EngineUnveilingEngineMoveTest {

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
        EngineMove pawnCaptureEngineMove = new EngineMove(
                new Field((byte)3, (byte)1),
                new Field((byte)2, (byte)2)
        );
        EngineMove pawnMovesForward = new EngineMove(
                new Field((byte)3, (byte)1),
                new Field((byte)2, (byte)1)
        );
        boolean pawnCaptureUnveiling = enginePosition.unveilingMove(pawnCaptureEngineMove);
        boolean pawnMovesUnveiling = enginePosition.unveilingMove(pawnMovesForward);
        assertEquals(pawnMovesUnveiling, true);
        assertEquals(pawnCaptureUnveiling, false);

    }

    @Test
    void unveilingMoveTestBishop() {
        String posCode = "";
        posCode += "bRbkbBbQ  bBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "    bB  bR      ";
        posCode += "  wP    wB      ";
        posCode += "        wK  bKwR";
        posCode += "                ";
        posCode += "wPwPwPwP  wPwP  ";
        posCode += "wRwkwBwQ  wBwk  ";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        WhiteBishop whiteBishop = (WhiteBishop) chessBoard[3][4];
        ArrayList<EngineMove> bishopEngineMoves = whiteBishop.possibleMoves(enginePosition.getColorMap());
        for (EngineMove engineMove : bishopEngineMoves) {
            assertEquals(enginePosition.unveilingMove(engineMove), true);
        }
    }

    @Test
    void unveilingMoveTestQueen() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "    bB  bR      ";
        posCode += "  wP  wPwQ      ";
        posCode += "        wK    wR";
        posCode += "                ";
        posCode += "wPwPwPwP  wPwP  ";
        posCode += "wRwkwBwQ  wBwk  ";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        WhiteQueen whiteQueen = (WhiteQueen) chessBoard[3][4];
        ArrayList<EngineMove> queenEngineMoves = whiteQueen.possibleMoves(enginePosition.getColorMap());
        int correctMovesCount = 0;
        EngineMove correctEngineMove = null;
        for (EngineMove engineMove : queenEngineMoves) {
            boolean correct = !enginePosition.unveilingMove(engineMove);
            if (correct) {
                correctMovesCount += 1;
                correctEngineMove = engineMove;
            }
        }
        assertEquals(correctMovesCount, 1);
        assertEquals(
                correctEngineMove,
                new EngineMove(
                        new Field((byte)3, (byte)4),
                        new Field((byte)2, (byte)4)
                )
        );
    }

    @Test
    void unveilingMoveTestBlackKnight() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbkbPbPbP";
        posCode += "    bB          ";
        posCode += "  wP  wPwQ      ";
        posCode += "              wR";
        posCode += "                ";
        posCode += "wPwPwPwP  wPwP  ";
        posCode += "wRwkwBwQwKwBwk  ";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        BlackKnight blackKnight = (BlackKnight) chessBoard[1][4];
        ArrayList<EngineMove> knightsEngineMoves = blackKnight.possibleMoves(enginePosition.getColorMap());
        for (EngineMove engineMove : knightsEngineMoves) {
            assertEquals(enginePosition.unveilingMove(engineMove), true);
        }
    }

    @Test
    void unveilingMoveTestBlackKing() {
        String posCode = "";
        posCode += "bRbkbBbQ  bBbkbR";
        posCode += "bPbPbPbPbkbP  bP";
        posCode += "    bB    bK    ";
        posCode += "  wP  wPwQ      ";
        posCode += "              wR";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwP  ";
        posCode += "wRwkwBwQwKwBwk  ";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        BlackKing blackKing = (BlackKing) chessBoard[2][5];
        ArrayList<EngineMove> kingsEngineMoves = blackKing.possibleMoves(enginePosition.getColorMap());
        ArrayList<EngineMove> correctEngineMoves = new ArrayList<>();
        for (EngineMove engineMove : kingsEngineMoves) {
            boolean correct = !enginePosition.unveilingMove(engineMove);
            if (correct) {
                correctEngineMoves.add(engineMove);
            }
        }
        assertEquals(correctEngineMoves.size(), 2);
        assertTrue(correctEngineMoves.contains(
                new EngineMove(
                        new Field((byte)2, (byte)5),
                        new Field((byte)2, (byte)6)
                )
        ));
        assertTrue(correctEngineMoves.contains(
                new EngineMove(
                        new Field((byte)2, (byte)5),
                        new Field((byte)3, (byte)4)
                )
        ));
    }

    @Test
    void unveilingMoveTestBlackKingCantCapture() {
        String posCode = "";
        posCode += "bRbkbBbQ  bBbkbR";
        posCode += "bPbPbPbPbkbP  bP";
        posCode += "    bB    bK    ";
        posCode += "  wP  wPwQ    wR";
        posCode += "                ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwP  ";
        posCode += "wRwkwBwQwKwBwk  ";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        BlackKing blackKing = (BlackKing) chessBoard[2][5];
        ArrayList<EngineMove> kingsEngineMoves = blackKing.possibleMoves(enginePosition.getColorMap());
        ArrayList<EngineMove> correctEngineMoves = new ArrayList<>();
        for (EngineMove engineMove : kingsEngineMoves) {
            boolean correct = !enginePosition.unveilingMove(engineMove);
            if (correct) {
                correctEngineMoves.add(engineMove);
            }
        }
        assertEquals(correctEngineMoves.size(), 1);
        assertTrue(correctEngineMoves.contains(
                new EngineMove(
                        new Field((byte)2, (byte)5),
                        new Field((byte)2, (byte)6)
                )
        ));
        assertFalse(correctEngineMoves.contains(
                new EngineMove(
                        new Field((byte)2, (byte)5),
                        new Field((byte)3, (byte)4)
                )
        ));
    }
}
