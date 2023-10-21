package chessEngine.chess.piece.infiniteRangePiece.bishop;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;

public abstract class Bishop extends InfiniteRangePiece {

    public Bishop(byte height, byte width, EnginePosition pos) {
        super(height, width, pos, new byte[][] {
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}
        });
    }
}
