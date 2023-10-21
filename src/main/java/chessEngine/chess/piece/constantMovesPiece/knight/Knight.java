package chessEngine.chess.piece.constantMovesPiece.knight;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.constantMovesPiece.ConstantMovesPiece;

public abstract class Knight extends ConstantMovesPiece {


    public Knight(byte height, byte width, EnginePosition pos) {
        super(height, width, pos, new byte[][] {
                {-2, 1},
                {2, 1},
                {-2, -1},
                {2, -1},
                {1, -2},
                {1, 2},
                {-1, 2},
                {-1, -2}
        });
    }
}
