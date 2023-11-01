package chessEngine.chessTest.engine;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.Piece;
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

import static org.junit.jupiter.api.Assertions.*;

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
//
//    @Test
//    void unveilingColorMapTest () {
//        String posCode = "";
//        posCode += "bRbkbBbQbKbBbkbR";
//        posCode += "bPbPbPbPbPbPbPbP";
//        posCode += "                ";
//        posCode += "                ";
//        posCode += "                ";
//        posCode += "                ";
//        posCode += "wPwPwPwPwPwPwPwP";
//        posCode += "wRwkwBwQwKwBwkwR";
//        EnginePosition enginePosition = new EnginePosition(posCode, true);
//        enginePosition.set();
//        EngineMove engineMove = new EngineMove(
//                new Field((byte)6, (byte)2),
//                new Field((byte)5, (byte)2)
//        );
//        PieceColor[][] colorMapAfter = enginePosition.getColorMap();
//        enginePosition.unveilingMove(engineMove);
//        enginePosition.setColorMap(null);
//        PieceColor[][] colorMapBefore = enginePosition.getColorMap();
//        for(int h = 0; h < enginePosition.getBoardHeight(); h++) {
//            for (int w = 0; w < enginePosition.getBoardWidth(); w++) {
//                assertEquals(colorMapBefore[h][w], colorMapAfter[h][w]);
//            }
//        }
//    }





}
