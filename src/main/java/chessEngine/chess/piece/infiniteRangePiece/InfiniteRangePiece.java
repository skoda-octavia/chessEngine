package chessEngine.chess.piece.infiniteRangePiece;

import chessEngine.chess.piece.Piece;

public abstract class InfiniteRangePiece extends Piece {
    private int[][] directions;

    public InfiniteRangePiece(int height, int width) {
        super(height, width);
    }
}
