package chessEngine.chess.piece.constantMovesPiece.king;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.constantMovesPiece.ConstantMovesPiece;

public abstract class King extends ConstantMovesPiece {

    public King(byte height, byte width, EnginePosition pos) {
        super(height, width, pos, new byte[][] {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1},
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}
        });
    }
}
