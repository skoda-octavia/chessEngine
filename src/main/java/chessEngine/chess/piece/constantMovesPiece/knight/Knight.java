package chessEngine.chess.piece.constantMovesPiece.knight;

import chessEngine.chess.piece.constantMovesPiece.ConstantMovesPiece;

public abstract class Knight extends ConstantMovesPiece {
    private final byte [][] possibleMoves = {
            {-2, 1},
            {2, 1},
            {-2, -1},
            {2, -1},
            {1, -2},
            {1, 2},
            {-1, 2},
            {-1, -2}
    };

    public Knight(byte height, byte width) {
        super(height, width);
    }
}
