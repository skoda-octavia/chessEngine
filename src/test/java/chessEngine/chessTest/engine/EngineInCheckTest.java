package chessEngine.chessTest.engine;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.constantMovesPiece.king.BlackKing;
import chessEngine.chess.piece.constantMovesPiece.king.WhiteKing;
import chessEngine.chess.piece.constantMovesPiece.knight.BlackKnight;
import chessEngine.chess.piece.infiniteRangePiece.queen.WhiteQueen;
import chessEngine.chess.piece.pawn.BlackPawn;
import chessEngine.chess.piece.pawn.WhitePawn;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        ArrayList<Piece> attackingWhite = enginePosition.checkingPieces(PieceColor.WHITE);
        ArrayList<Piece> attackingBlack = enginePosition.checkingPieces(PieceColor.BLACK);
        assertEquals(attackingBlack.size(), 0);
        assertEquals(attackingWhite.size(), 0);

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
        ArrayList<Piece> attackingWhite = enginePosition.checkingPieces(PieceColor.WHITE);
        ArrayList<Piece> attackingBlack = enginePosition.checkingPieces(PieceColor.BLACK);
        assertEquals(attackingBlack.size(), 1);
        assertEquals(attackingWhite.size(), 1);
        assertTrue(attackingWhite.get(0) instanceof WhiteQueen);
        assertTrue(attackingBlack.get(0) instanceof BlackPawn);
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
        ArrayList<Piece> attackingWhite = enginePosition.checkingPieces(PieceColor.WHITE);
        ArrayList<Piece> attackingBlack = enginePosition.checkingPieces(PieceColor.BLACK);
        assertEquals(attackingBlack.size(), 1);
        assertEquals(attackingWhite.size(), 1);
        assertTrue(attackingBlack.get(0) instanceof BlackKing);
        assertTrue(attackingWhite.get(0) instanceof WhiteKing);
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
        ArrayList<Piece> attackingWhite = enginePosition.checkingPieces(PieceColor.WHITE);
        ArrayList<Piece> attackingBlack = enginePosition.checkingPieces(PieceColor.BLACK);
        assertEquals(attackingBlack.size(), 1);
        assertEquals(attackingWhite.size(), 2);
        assertTrue(attackingBlack.get(0) instanceof BlackKnight);
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
        ArrayList<Piece> attackingWhite = enginePosition.checkingPieces(PieceColor.WHITE);
        ArrayList<Piece> attackingBlack = enginePosition.checkingPieces(PieceColor.BLACK);
        WhitePawn whitePawn = (WhitePawn) enginePosition.getChessBoard()[3][1];
        assertEquals(attackingBlack.size(), 0);
        assertEquals(attackingWhite.size(), 2);
        assertTrue(whitePawn.getPinnedDirection() != null);
    }
}
