package chessEngine.chessTest.piece.infiniteRangePiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.Move;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InfiniteRangePieceTest {

    @Test
    void possibleMovesstartingPosTest() {
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
        PieceColor[][] colorMap = enginePosition.getColorMap();
        ArrayList<Move> rookMoves = chessBoard[0][0].possibleMoves(colorMap);
        ArrayList<Move> bishopMoves = chessBoard[7][2].possibleMoves(colorMap);
        ArrayList<Move> queenMoves = chessBoard[0][3].possibleMoves(colorMap);
        assertEquals(rookMoves.size() , 0);
        assertEquals(bishopMoves.size() ,0);
        assertEquals(queenMoves.size() , 0);
    }
    @Test
    void possibleMovespiecesInCenterTest() {
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
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        PieceColor[][] colorMap = enginePosition.getColorMap();
        ArrayList<Move> rookMoves = chessBoard[2][5].possibleMoves(colorMap);
        ArrayList<Move> bishopMoves = chessBoard[5][5].possibleMoves(colorMap);
        ArrayList<Move> queenMoves = chessBoard[2][2].possibleMoves(colorMap);
        assertEquals(rookMoves.size(), 8);
        assertEquals(bishopMoves.size(), 7);
        assertEquals(queenMoves.size(), 15);
    }

    @Test
    void controlledFieldsTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbP  bPbP  ";
        posCode += "    wQ    wR    ";
        posCode += "              bP";
        posCode += "        bP      ";
        posCode += "          bB    ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        PieceColor[][] colorMap = enginePosition.getColorMap();
        ArrayList<Field> rookFields = chessBoard[2][5].controlledFields(colorMap);
        ArrayList<Field> bishopField = chessBoard[5][5].controlledFields(colorMap);
        ArrayList<Field> queenField = chessBoard[2][2].controlledFields(colorMap);
        assertEquals(rookFields.size(), 9);
        assertEquals(bishopField.size(), 5);
        assertEquals(queenField.size(), 16);
    }


}
