package chessEngine.chess.piece.constantMovesPiece.king;

import chessEngine.chess.piece.constantMovesPiece.ConstantMovesPiece;

public abstract class King extends ConstantMovesPiece {
    private final int[][] possibleMoves = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1},
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
    };

    public King(int height, int width) {
        super(height, width);
    }
}
