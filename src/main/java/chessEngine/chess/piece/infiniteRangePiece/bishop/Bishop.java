package chessEngine.chess.piece.infiniteRangePiece.bishop;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;

public abstract class Bishop extends InfiniteRangePiece {

    public Bishop(PieceColor pieceColor, EnginePosition pos) {
        super(pieceColor, pos, new byte[][] {
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}
        });
    }
}
