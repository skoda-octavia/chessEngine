package chessEngine.chessTest.piece.infiniteRangePiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.infiniteRangePiece.bishop.BlackBishop;
import chessEngine.chess.piece.infiniteRangePiece.bishop.WhiteBishop;
import chessEngine.chess.piece.infiniteRangePiece.queen.BlackQueen;
import chessEngine.chess.piece.infiniteRangePiece.queen.WhiteQueen;
import chessEngine.chess.piece.infiniteRangePiece.rook.BlackRook;
import chessEngine.chess.piece.infiniteRangePiece.rook.WhiteRook;
import chessEngine.chess.piece.pawn.WhitePawn;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

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
        ArrayList<EngineMove> rookEngineMoves = chessBoard[0][0].getPossibleMoves();
        ArrayList<EngineMove> bishopEngineMoves = chessBoard[7][2].getPossibleMoves();
        ArrayList<EngineMove> queenEngineMoves = chessBoard[0][3].getPossibleMoves();
        assertEquals(rookEngineMoves.size() , 0);
        assertEquals(bishopEngineMoves.size() ,0);
        assertEquals(queenEngineMoves.size() , 0);
        BlackQueen blackQueen = (BlackQueen) chessBoard[0][3];
        assertFalse(blackQueen.isPinningKing());
    }
    @Test
    void possibleMovespiecesInCenterTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbk  ";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "    wQ    wR    ";
        posCode += "                ";
        posCode += "                ";
        posCode += "      bR  bB    ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        PieceColor[][] colorMap = enginePosition.getColorMap();
        ArrayList<EngineMove> rookEngineMoves = chessBoard[2][5].getPossibleMoves();
        ArrayList<EngineMove> bishopEngineMoves = chessBoard[5][5].getPossibleMoves();
        ArrayList<EngineMove> queenEngineMoves = chessBoard[2][2].getPossibleMoves();
        assertEquals(rookEngineMoves.size(), 8);
        assertEquals(bishopEngineMoves.size(), 7);
        assertEquals(queenEngineMoves.size(), 15);
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
        HashSet<Field> rookFields = chessBoard[2][5].getControlledFields();
        HashSet<Field> bishopField = chessBoard[5][5].getControlledFields();
        HashSet<Field> queenField = chessBoard[2][2].getControlledFields();
        assertEquals(rookFields.size(), 9);
        assertEquals(bishopField.size(), 5);
        assertEquals(queenField.size(), 16);
    }

    @Test
    void pinningTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbk  ";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "    wQ    wR    ";
        posCode += "              bB";
        posCode += "      bR    bP  ";
        posCode += "                ";
        posCode += "wPwPwPwP  wPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        WhiteRook whiteRook = (WhiteRook) chessBoard[2][5];
        WhiteQueen whiteQueen = (WhiteQueen) chessBoard[2][2];
        BlackRook blackRook = (BlackRook) chessBoard[4][3];
        BlackBishop blackBishop = (BlackBishop) chessBoard[3][7];
        assertTrue(whiteQueen.isPinningKing());
        assertFalse(whiteQueen.isPinningQueen());
        assertFalse(whiteRook.isPinningKing());
        assertFalse(whiteRook.isPinningQueen());
        assertTrue(blackRook.isPinningQueen());
        assertFalse(blackRook.isPinningKing());
        assertTrue(blackBishop.isPinningQueen());
    }

    @Test
    void discoveryCheckPawn() {
        String posCode = "";
        posCode += "                ";
        posCode += "      bPbK      ";
        posCode += "        wP      ";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "wPwPwPwPwQwPwPwP";
        posCode += "wRwkwB  wKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        WhitePawn whitePawn = (WhitePawn) chessBoard[2][4];
        assertEquals(1, whitePawn.getPossibleMoves().size());
    }
    @Test
    void discoveryCheckBishop() {
        String posCode = "";
        posCode += "                ";
        posCode += "      bPbK      ";
        posCode += "        wB      ";
        posCode += "      bP  bP    ";
        posCode += "                ";
        posCode += "                ";
        posCode += "      wPwRwP    ";
        posCode += "        wK      ";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        WhiteBishop whiteBishop = (WhiteBishop) chessBoard[2][4];
        assertEquals(14, enginePosition.possibleLegalMoves().size());
    }

}
