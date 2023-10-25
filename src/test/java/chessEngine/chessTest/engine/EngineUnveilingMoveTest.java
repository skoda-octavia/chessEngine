package chessEngine.chessTest.engine;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.Move;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.constantMovesPiece.king.BlackKing;
import chessEngine.chess.piece.constantMovesPiece.knight.BlackKnight;
import chessEngine.chess.piece.infiniteRangePiece.bishop.WhiteBishop;
import chessEngine.chess.piece.infiniteRangePiece.queen.WhiteQueen;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        ArrayList<Move> bishopMoves = whiteBishop.possibleMoves(enginePosition.getColorMap());
        for (Move move : bishopMoves) {
            assertEquals(enginePosition.unveilingMove(move), true);
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
        ArrayList<Move> queenMoves = whiteQueen.possibleMoves(enginePosition.getColorMap());
        int correctMovesCount = 0;
        Move correctMove = null;
        for (Move move : queenMoves) {
            boolean correct = !enginePosition.unveilingMove(move);
            if (correct) {
                correctMovesCount += 1;
                correctMove = move;
            }
        }
        assertEquals(correctMovesCount, 1);
        assertEquals(
                correctMove,
                new Move(
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
        ArrayList<Move> knightsMoves = blackKnight.possibleMoves(enginePosition.getColorMap());
        for (Move move : knightsMoves) {
            assertEquals(enginePosition.unveilingMove(move), true);
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
        ArrayList<Move> kingsMoves = blackKing.possibleMoves(enginePosition.getColorMap());
        ArrayList<Move> correctMoves = new ArrayList<>();
        for (Move move : kingsMoves) {
            boolean correct = !enginePosition.unveilingMove(move);
            if (correct) {
                correctMoves.add(move);
            }
        }
        assertEquals(correctMoves.size(), 2);
        assertTrue(correctMoves.contains(
                new Move(
                        new Field((byte)2, (byte)5),
                        new Field((byte)2, (byte)6)
                )
        ));
        assertTrue(correctMoves.contains(
                new Move(
                        new Field((byte)2, (byte)5),
                        new Field((byte)3, (byte)4)
                )
        ));
    }
}
