package chessEngine.chess.piece.infiniteRangePiece.queen;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;

public abstract class Queen  extends InfiniteRangePiece {

    public Queen(byte height, byte width, EnginePosition pos) {
        super(height, width, pos, new byte[][] {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1},
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}
        });
    }
}
