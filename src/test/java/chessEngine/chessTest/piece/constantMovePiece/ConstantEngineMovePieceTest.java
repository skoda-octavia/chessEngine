package chessEngine.chessTest.piece.constantMovePiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.Piece;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

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
        ArrayList<EngineMove> blackKnightEngineMoves = chessBoard[0][6].getPossibleMoves();
        ArrayList<EngineMove> whiteKnightEngineMoves = chessBoard[7][1].getPossibleMoves();
        ArrayList<EngineMove> whiteKingEngineMoves = chessBoard[5][5].getPossibleMoves();
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
        HashSet<Field> blackKnightFields = chessBoard[0][6].getControlledFields();
        HashSet<Field> whiteKnightFields = chessBoard[3][1].getControlledFields();
        HashSet<Field> whiteKingFields = chessBoard[5][5].getControlledFields();
        assertEquals(blackKnightFields.size(), 3);
        assertEquals(whiteKnightFields.size(), 6);
        assertEquals(whiteKingFields.size(), 8);
    }
}
