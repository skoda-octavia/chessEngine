package chessEngine.chessTest.piece.constantMovePiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.EngineMove;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstantEngineMovePieceTest {

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
        ArrayList<EngineMove> blackKnightEngineMoves = chessBoard[0][6].possibleMoves(colorMap);
        ArrayList<EngineMove> whiteKnightEngineMoves = chessBoard[7][1].possibleMoves(colorMap);
        ArrayList<EngineMove> whiteKingEngineMoves = chessBoard[5][5].possibleMoves(colorMap);
        assertEquals(blackKnightEngineMoves.size(), 3);
        assertEquals(whiteKnightEngineMoves.size(), 2);
        assertEquals(whiteKingEngineMoves.size(), 5);
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
