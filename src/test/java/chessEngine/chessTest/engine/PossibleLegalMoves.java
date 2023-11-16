package chessEngine.chessTest.engine;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.infiniteRangePiece.bishop.WhiteBishop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PossibleLegalMoves {

    @Test
    void legalPinnedKnightMoves() {
        String posCode = "";
        posCode += "                ";
        posCode += "    bK          ";
        posCode += "  bk            ";
        posCode += "wB              ";
        posCode += "                ";
        posCode += "                ";
        posCode += "      wPwRwP    ";
        posCode += "        wK      ";
        EnginePosition enginePosition = new EnginePosition(posCode, false);
        enginePosition.set();
        Piece[][] chessBoard = enginePosition.getChessBoard();
        assertEquals(7, enginePosition.possibleLegalMoves().size());
    }
}
