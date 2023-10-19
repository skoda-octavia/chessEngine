package chessEngine.chess.piece.infiniteRangePiece.queen;

import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;

public abstract class Queen  extends InfiniteRangePiece {
    private final int[][] directions = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1},
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
    };

    public Queen(int height, int width) {
        super(height, width);
    }
}
