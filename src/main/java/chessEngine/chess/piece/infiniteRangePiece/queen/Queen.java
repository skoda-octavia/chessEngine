package chessEngine.chess.piece.infiniteRangePiece.queen;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;

public abstract class Queen  extends InfiniteRangePiece {
    private final byte[][] directions = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1},
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
    };

    public Queen(byte height, byte width, EnginePosition pos) {
        super(height, width, pos);
    }
}
