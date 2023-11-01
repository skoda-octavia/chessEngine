package chessEngine.chess.piece.constantMovesPiece.knight;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.constantMovesPiece.ConstantMovesPiece;

public abstract class Knight extends ConstantMovesPiece {


    @Override
    public void setMyPossibilities(PieceColor[][] colorMap) {
        setConstantPiecePossibilities(colorMap);
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
