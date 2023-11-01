package chessEngine.chessTest.piece.pawn;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.EngineMoveCode;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.pawn.BlackPawn;
import chessEngine.chess.piece.pawn.WhitePawn;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

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
        ArrayList<EngineMove> aBlackPawn = chessBoard[1][0].getPossibleMoves();
        ArrayList<EngineMove> bBlackPawn = chessBoard[1][1].getPossibleMoves();
        ArrayList<EngineMove> cBlackPawn = chessBoard[1][2].getPossibleMoves();
        ArrayList<EngineMove> gBlackPawn = chessBoard[1][6].getPossibleMoves();
        ArrayList<EngineMove> dWhitePawn = chessBoard[5][3].getPossibleMoves();
        ArrayList<EngineMove> hWhitePawn = chessBoard[6][7].getPossibleMoves();
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
        HashSet<Field> aBlackPawn = chessBoard[1][0].getControlledFields();
        HashSet<Field> bBlackPawn = chessBoard[1][1].getControlledFields();
        HashSet<Field> cBlackPawn = chessBoard[1][2].getControlledFields();
        HashSet<Field> hBlackPawn = chessBoard[1][7].getControlledFields();
        HashSet<Field> eWhitePawn = chessBoard[6][4].getControlledFields();
        assertEquals(hBlackPawn.size() , 1);
        assertEquals(eWhitePawn.size(), 2);
        assertEquals(aBlackPawn.size() , 1);
        assertEquals(bBlackPawn.size(), 2);
        assertEquals(cBlackPawn.size() , 2);
    }


    @Test
    void enpassantBorderTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bP  bPbPbPbPbPbP";
        posCode += "                ";
        posCode += "wPbP            ";
        posCode += "                ";
        posCode += "                ";
        posCode += "  wPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.setParentMove(new EngineMove(
                        new Field((byte)1, (byte)1),
                        new Field((byte)3, (byte)1)
                )
        );
        enginePosition.set();

        WhitePawn whitePawn =  (WhitePawn) enginePosition.getChessBoard()[3][0];
        assertEquals(2, whitePawn.getPossibleMoves().size());
        assertTrue(whitePawn.getPossibleMoves().contains(
                new EngineMove(
                        new Field((byte)3, (byte)0),
                        new Field((byte)2, (byte)1),
                        EngineMoveCode.ENPASSANT
                )
        ));
    }

    @Test
    void enpassantMiddleTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bP      bPbPbPbP";
        posCode += "  bP            ";
        posCode += "  bPwPbP        ";
        posCode += "                ";
        posCode += "                ";
        posCode += "  wPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.setParentMove(new EngineMove(
                        new Field((byte) 1, (byte) 3),
                        new Field((byte) 3, (byte) 3)
                )
        );
        enginePosition.set();

        WhitePawn whitePawn = (WhitePawn) enginePosition.getChessBoard()[3][2];
        assertEquals(3, whitePawn.getPossibleMoves().size());
        assertTrue(whitePawn.getPossibleMoves().contains(
                new EngineMove(
                        new Field((byte) 3, (byte) 2),
                        new Field((byte) 2, (byte) 3),
                        EngineMoveCode.ENPASSANT
                )
        ));
    }



    @Test
    void enpassantBlackTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bP  bPbPbP  bPbP";
        posCode += "  bP            ";
        posCode += "wP              ";
        posCode += "          bPwP  ";
        posCode += "                ";
        posCode += "  wPwPwPwPwP  wP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.setParentMove(new EngineMove(
                        new Field((byte)6, (byte)6),
                        new Field((byte)4, (byte)6)
                )
        );
        enginePosition.set();

        BlackPawn blackPawn =  (BlackPawn) enginePosition.getChessBoard()[4][5];
        assertEquals(2, blackPawn.getPossibleMoves().size());
        assertTrue(blackPawn.getPossibleMoves().contains(
                new EngineMove(
                        new Field((byte)4, (byte)5),
                        new Field((byte)5, (byte)6),
                        EngineMoveCode.ENPASSANT
                )
        ));
    }

    @Test
    void noEnpassantTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bP  bPbPbP  bPbP";
        posCode += "  bP            ";
        posCode += "wP              ";
        posCode += "          bPwP  ";
        posCode += "                ";
        posCode += "  wPwPwPwPwP  wP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.setParentMove(new EngineMove(
                        new Field((byte)4, (byte)0),
                        new Field((byte)3, (byte)0)
                )
        );
        enginePosition.set();

        BlackPawn blackPawn =  (BlackPawn) enginePosition.getChessBoard()[4][5];
        assertEquals(1, blackPawn.getPossibleMoves().size());
    }

    @Test
    void noEnpassantRookTest() {
        String posCode = "";
        posCode += "bRbkbBbQbKbBbkbR";
        posCode += "bP  bPbPbP  bPbP";
        posCode += "  bP            ";
        posCode += "wP              ";
        posCode += "          bPwR  ";
        posCode += "                ";
        posCode += "  wPwPwPwPwP  wP";
        posCode += "wRwkwBwQwKwBwkwR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.setParentMove(new EngineMove(
                new Field((byte)6, (byte)6),
                new Field((byte)4, (byte)6)
                )
        );
        enginePosition.set();

        BlackPawn blackPawn =  (BlackPawn) enginePosition.getChessBoard()[4][5];
        assertEquals(1, blackPawn.getPossibleMoves().size());
    }


}