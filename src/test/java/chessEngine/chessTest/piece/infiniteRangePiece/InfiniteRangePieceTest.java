package chessEngine.chessTest.piece.infiniteRangePiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.Move;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InfiniteRangePieceTest {

    @Test
    void startingPosTest() {
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
        Piece[][] chessBoard = enginePosition.getChessBoard();
        PieceColor[][] colorMap = enginePosition.getColorMap();
        ArrayList<Move> rookMoves = chessBoard[0][0].possibleMoves(colorMap);
        ArrayList<Move> bishopMoves = chessBoard[7][2].possibleMoves(colorMap);
        ArrayList<Move> queenMoves = chessBoard[0][3].possibleMoves(colorMap);
        assertTrue(rookMoves.size() == 0);
        assertTrue(bishopMoves.size() == 0);
        assertTrue(queenMoves.size() == 0);
    }
    @Test
    void piecesInCenterTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "    wQ    wR    ";
        posCode += "                ";
        posCode += "                ";
        posCode += "          bB    ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        Piece[][] chessBoard = enginePosition.getChessBoard();
        PieceColor[][] colorMap = enginePosition.getColorMap();
        ArrayList<Move> rookMoves = chessBoard[2][5].possibleMoves(colorMap);
        ArrayList<Move> bishopMoves = chessBoard[5][5].possibleMoves(colorMap);
        ArrayList<Move> queenMoves = chessBoard[2][2].possibleMoves(colorMap);
        assertTrue(rookMoves.size() == 8);
        assertTrue(bishopMoves.size() == 7);
        assertTrue(queenMoves.size() == 15);
    }
}
