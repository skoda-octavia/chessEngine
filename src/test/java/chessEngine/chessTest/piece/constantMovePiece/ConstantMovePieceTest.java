package chessEngine.chessTest.piece.constantMovePiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.Move;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstantMovePieceTest {

    @Test
    void ConstantMovePieceTest () {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbP  bPbPbP";
        posCode += "                ";
        posCode += "                ";
        posCode += "    wK          ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQ  wBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        Piece[][] chessBoard = enginePosition.getChessBoard();
        PieceColor[][] colorMap = enginePosition.getColorMap();
        ArrayList<Move> blackKnightMoves = chessBoard[0][6].possibleMoves(colorMap);
        ArrayList<Move> whiteKnightMoves = chessBoard[7][1].possibleMoves(colorMap);
        ArrayList<Move> whiteKingMoves = chessBoard[4][2].possibleMoves(colorMap);
        assertTrue(blackKnightMoves.size() == 3);
        assertTrue(whiteKnightMoves.size() == 2);
        assertTrue(whiteKingMoves.size() == 8);
    }
}
