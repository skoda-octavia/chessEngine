package chessEngine.chess.piece.constantMovesPiece.knight;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.constantMovesPiece.ConstantMovesPiece;

public abstract class Knight extends ConstantMovesPiece {

    public Knight(PieceColor pieceColor, EnginePosition pos, String pieceCode, Field field) {
        super(pieceColor, pos, new byte[][] {
                {-2, 1},
                {2, 1},
                {-2, -1},
                {2, -1},
                {1, -2},
                {1, 2},
                {-1, 2},
                {-1, -2}},
                pieceCode,
                field
                );
    }
}
