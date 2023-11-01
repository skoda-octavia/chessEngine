package chessEngine.chessTest.piece.pawn;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.pawn.BlackPawn;
import chessEngine.chess.piece.pawn.WhitePawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTransformationTest {


    @Test
    void possibleTransformations() {
        String posCode = "";
        posCode += "bR    bK      bR";
        posCode += "bPwPbPbPbPwP  bP";
        posCode += "                ";
        posCode += "            wR  ";
        posCode += "                ";
        posCode += "                ";
        posCode += "wPbPwPwPwP  wPwP";
        posCode += "wR  wk  wK    wR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();

        WhitePawn whitePawnB = (WhitePawn) enginePosition.getChessBoard()[1][1];
        WhitePawn whitePawnF = (WhitePawn) enginePosition.getChessBoard()[1][5];
        BlackPawn blackPawn = (BlackPawn) enginePosition.getChessBoard()[6][1];

        assertEquals(8, whitePawnB.getPossibleMoves().size());
        assertEquals(4, whitePawnF.getPossibleMoves().size());
        assertEquals(12, blackPawn.getPossibleMoves().size());

    }

}
