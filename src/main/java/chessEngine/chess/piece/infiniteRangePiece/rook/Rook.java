package chessEngine.chess.piece.infiniteRangePiece.rook;

import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;

public abstract class Rook extends InfiniteRangePiece {
    private final byte[][] directions = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    public Rook(byte height, byte width) {
        super(height, width);
    }
}
