package chessEngine.chess.piece.infiniteRangePiece.queen;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;

public class WhiteQueen  extends Queen {
    private final PieceColor pieceColor = PieceColor.WHITE;

    public WhiteQueen(byte height, byte width, EnginePosition pos) {
        super(height, width, pos);
    }
}
