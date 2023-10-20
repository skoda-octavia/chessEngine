package chessEngine.chess.piece.constantMovesPiece;

import chessEngine.chess.piece.Piece;

public abstract class ConstantMovesPiece extends Piece {
    private byte[][] possibleMoves;

    public ConstantMovesPiece(byte height, byte width) {
        super(height, width);
    }
}
