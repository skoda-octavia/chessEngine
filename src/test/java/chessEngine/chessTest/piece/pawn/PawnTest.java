package chessEngine.chessTest.piece.pawn;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.Move;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        PieceColor[][] colorMap = enginePosition.getColorMap();
        ArrayList<Move> aBlackPawn = chessBoard[1][0].possibleMoves(colorMap);
        ArrayList<Move> bBlackPawn = chessBoard[1][1].possibleMoves(colorMap);
        ArrayList<Move> cBlackPawn = chessBoard[1][2].possibleMoves(colorMap);
        ArrayList<Move> gBlackPawn = chessBoard[1][6].possibleMoves(colorMap);
        ArrayList<Move> dWhitePawn = chessBoard[5][3].possibleMoves(colorMap);
        ArrayList<Move> hWhitePawn = chessBoard[6][7].possibleMoves(colorMap);
        assertEquals(aBlackPawn.size() , 2);
        assertEquals(bBlackPawn.size(), 3);
        assertEquals(cBlackPawn.size() , 0);
        assertEquals(gBlackPawn.size() , 4);
        assertEquals(dWhitePawn.size(), 2);
        assertEquals(hWhitePawn.size(), 1);
    }

    @Test
    void fieldsControlled() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "    wk    wk  wk";
        posCode += "                ";
        posCode += "    bPwkbP    bk";
        posCode += "      wP    wP  ";
        posCode += "wPwPwP  wPwP  wP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        PieceColor[][] colorMap = enginePosition.getColorMap();
        ArrayList<Field> aBlackPawn = chessBoard[1][0].controlledFields(colorMap);
        ArrayList<Field> bBlackPawn = chessBoard[1][1].controlledFields(colorMap);
        ArrayList<Field> cBlackPawn = chessBoard[1][2].controlledFields(colorMap);
        ArrayList<Field> hBlackPawn = chessBoard[1][7].controlledFields(colorMap);
        ArrayList<Field> eWhitePawn = chessBoard[6][4].controlledFields(colorMap);
        assertEquals(hBlackPawn.size() , 1);
        assertEquals(eWhitePawn.size(), 2);
        assertEquals(aBlackPawn.size() , 1);
        assertEquals(bBlackPawn.size(), 2);
        assertEquals(cBlackPawn.size() , 2);
    }


}