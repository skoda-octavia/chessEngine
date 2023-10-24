package chessEngine.chessTest.piece.pawn;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.Move;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTest {


    @Test
    void possiblePawnMovesTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "    wk    wk  wk";
        posCode += "                ";
        posCode += "    bPwkbP    bk";
        posCode += "      wP        ";
        posCode += "wPwPwP  wPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        Piece[][] chessBoard = enginePosition.getChessBoard();
        PieceColor[][] colorMap = enginePosition.getColorMap();
        ArrayList<Move> aBlackPawn = chessBoard[1][0].possibleMoves(colorMap);
        ArrayList<Move> bBlackPawn = chessBoard[1][1].possibleMoves(colorMap);
        ArrayList<Move> cBlackPawn = chessBoard[1][2].possibleMoves(colorMap);
        ArrayList<Move> gBlackPawn = chessBoard[1][6].possibleMoves(colorMap);
        ArrayList<Move> dWhitePawn = chessBoard[5][3].possibleMoves(colorMap);
        ArrayList<Move> hWhitePawn = chessBoard[6][7].possibleMoves(colorMap);
        assertTrue(aBlackPawn.size() == 2);
        assertTrue(bBlackPawn.size() == 3);
        assertTrue(cBlackPawn.size() == 0);
        assertTrue(gBlackPawn.size() == 4);
        assertTrue(dWhitePawn.size() == 2);
        assertTrue(hWhitePawn.size() == 1);
    }
}