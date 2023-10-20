package chessEngine.chess.piece.infiniteRangePiece;

import chessEngine.chess.piece.Piece;

public abstract class InfiniteRangePiece extends Piece {
    private byte[][] directions;

    public InfiniteRangePiece(byte height, byte width) {
        super(height, width);
    }
}
