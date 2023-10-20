package chessEngine.chess.piece.infiniteRangePiece.bishop;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;

public abstract class Bishop extends InfiniteRangePiece {
    private final byte [][] directions = {
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
    };

    public Bishop(byte height, byte width, EnginePosition pos) {
        super(height, width, pos);
    }
}
