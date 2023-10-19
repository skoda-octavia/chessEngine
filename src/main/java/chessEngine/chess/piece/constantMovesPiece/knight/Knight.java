package chessEngine.chess.piece.constantMovesPiece.knight;

import chessEngine.chess.piece.constantMovesPiece.ConstantMovesPiece;

public abstract class Knight extends ConstantMovesPiece {
    private final int [][] possibleMoves = {
            {-2, 1},
            {2, 1},
            {-2, -1},
            {2, -1},
            {1, -2},
            {1, 2},
            {-1, 2},
            {-1, -2}
    };

    public Knight(int height, int width) {
        super(height, width);
    }
}
