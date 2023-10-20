package chessEngine.chess.piece.constantMovesPiece.knight;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import org.apache.catalina.Engine;

public class WhiteKnight extends Knight{
    private final PieceColor pieceColor = PieceColor.WHITE;

    public WhiteKnight(byte height, byte width, EnginePosition pos) {
        super(height, width, pos);
    }
}
