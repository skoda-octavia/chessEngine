package chessEngine.chess.piece.infiniteRangePiece.bishop;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import org.apache.catalina.Engine;

public class BlackBishop extends Bishop {

    private final PieceColor pieceColor = PieceColor.BLACK;

    public BlackBishop(byte height, byte width, EnginePosition pos) {
        super(height, width, pos);
    }
}
