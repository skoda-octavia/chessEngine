package chessEngine.chessTest.piece.constantMovePiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.constantMovesPiece.king.BlackKing;
import chessEngine.chess.piece.constantMovesPiece.king.WhiteKing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingTest {

    @Test
    void possibleCastlingMoves() {
        String posCode = "";
        posCode += "bR      bK    bR";
        posCode += "bPbPbPbPbPbP  bP";
        posCode += "                ";
        posCode += "            wR  ";
        posCode += "                ";
        posCode += "                ";
        posCode += "wPwPwPwPwP  wPwP";
        posCode += "wR      wK    wR";
        EnginePosition enginePosition = new EnginePosition(posCode, true);
        enginePosition.set();
        WhiteKing whiteKing = (WhiteKing) enginePosition.getChessBoard()[7][4];
        BlackKing blackKing = (BlackKing)  enginePosition.getChessBoard()[0][4];
        assertEquals(5, whiteKing.getPossibleMoves().size());
        assertEquals(2, blackKing.getPossibleMoves().size());

    }

}
