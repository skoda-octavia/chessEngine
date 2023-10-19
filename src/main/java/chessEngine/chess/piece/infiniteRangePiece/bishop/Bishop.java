package chessEngine.chess.piece.infiniteRangePiece.bishop;

import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;

public abstract class Bishop extends InfiniteRangePiece {
    private final int [][] directions = {
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
    };

    public Bishop(int height, int width) {
        super(height, width);
    }
}
