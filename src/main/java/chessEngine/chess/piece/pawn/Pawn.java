package chessEngine.chess.piece.pawn;

import chessEngine.chess.piece.Piece;

public abstract class Pawn extends Piece {
    private byte movingDirection;

    public Pawn(byte height, byte width) {
        super(height, width);
    }
}
