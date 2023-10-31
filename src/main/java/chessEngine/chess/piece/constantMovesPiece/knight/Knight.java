package chessEngine.chess.piece.constantMovesPiece.knight;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.EngineMove;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.constantMovesPiece.ConstantMovesPiece;

import java.util.ArrayList;

public abstract class Knight extends ConstantMovesPiece {

    @Override
    public ArrayList<EngineMove> possibleMoves(PieceColor[][] colorMap) {
        return this.possibleConstantMoves(colorMap);
    }

    public Knight(PieceColor pieceColor, EnginePosition pos) {
        super(pieceColor, pos, new byte[][] {
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
