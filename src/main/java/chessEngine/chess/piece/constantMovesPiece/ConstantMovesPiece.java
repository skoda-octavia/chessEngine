package chessEngine.chess.piece.constantMovesPiece;

import chessEngine.chess.piece.Piece;

public abstract class ConstantMovesPiece extends Piece {
    private int[][] possibleMoves;

    public ConstantMovesPiece(int height, int width) {
        super(height, width);
    }
}
