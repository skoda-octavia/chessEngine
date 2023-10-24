package chessEngine.chessTest;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.constantMovesPiece.king.BlackKing;
import chessEngine.chess.piece.constantMovesPiece.king.WhiteKing;
import chessEngine.chess.piece.constantMovesPiece.knight.BlackKnight;
import chessEngine.chess.piece.constantMovesPiece.knight.WhiteKnight;
import chessEngine.chess.piece.infiniteRangePiece.bishop.BlackBishop;
import chessEngine.chess.piece.infiniteRangePiece.bishop.WhiteBishop;
import chessEngine.chess.piece.infiniteRangePiece.queen.BlackQueen;
import chessEngine.chess.piece.infiniteRangePiece.queen.WhiteQueen;
import chessEngine.chess.piece.infiniteRangePiece.rook.BlackRook;
import chessEngine.chess.piece.infiniteRangePiece.rook.WhiteRook;
import chessEngine.chess.piece.pawn.BlackPawn;
import chessEngine.chess.piece.pawn.WhitePawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnginePositionTest {

    @Test
    public void readingPositonCode() {
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
        Piece[][] chessBoard = enginePosition.getChessBoard();
        assertTrue(chessBoard[0][0] instanceof BlackRook);
        assertTrue(chessBoard[0][1] instanceof BlackKnight);
        assertTrue(chessBoard[0][2] instanceof BlackBishop);
        assertTrue(chessBoard[0][3] instanceof BlackQueen);
        assertTrue(chessBoard[0][4] instanceof BlackKing);
        assertTrue(chessBoard[1][4] instanceof BlackPawn);
        assertTrue(chessBoard[2][0] == null);
        assertTrue(chessBoard[5][7] == null);
        assertTrue(chessBoard[7][0] instanceof WhiteRook);
        assertTrue(chessBoard[7][1] instanceof WhiteKnight);
        assertTrue(chessBoard[7][2] instanceof WhiteBishop);
        assertTrue(chessBoard[7][3] instanceof WhiteQueen);
        assertTrue(chessBoard[7][4] instanceof WhiteKing);
        assertTrue(chessBoard[6][4] instanceof WhitePawn);
        assertTrue(enginePosition.getBlackPieces().size() == 16);
        assertTrue(enginePosition.getWhitePieces().size() == 16);
    }

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
    void inCheckTestConstantblocked() {
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
