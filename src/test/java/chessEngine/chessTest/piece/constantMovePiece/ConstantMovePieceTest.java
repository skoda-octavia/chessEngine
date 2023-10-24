package chessEngine.chessTest.piece.constantMovePiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.Move;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstantMovePieceTest {

    @Test
    void possibleMovesTest () {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbP  bPbPbP";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "          wK    ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQ  wBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        PieceColor[][] colorMap = enginePosition.getColorMap();
        ArrayList<Move> blackKnightMoves = chessBoard[0][6].possibleMoves(colorMap);
        ArrayList<Move> whiteKnightMoves = chessBoard[7][1].possibleMoves(colorMap);
        ArrayList<Move> whiteKingMoves = chessBoard[5][5].possibleMoves(colorMap);
        assertEquals(blackKnightMoves.size(), 3);
        assertEquals(whiteKnightMoves.size(), 2);
        assertEquals(whiteKingMoves.size(), 5);
    }


    @Test
    void fieldsControlledTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "  wk            ";
        posCode += "                ";
        posCode += "          wK    ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQ  wBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        PieceColor[][] colorMap = enginePosition.getColorMap();
        ArrayList<Field> blackKnightFields = chessBoard[0][6].controlledFields(colorMap);
        ArrayList<Field> whiteKnightFields = chessBoard[3][1].controlledFields(colorMap);
        ArrayList<Field> whiteKingFields = chessBoard[5][5].controlledFields(colorMap);
        assertEquals(blackKnightFields.size(), 3);
        assertEquals(whiteKnightFields.size(), 6);
        assertEquals(whiteKingFields.size(), 8);
    }
}
