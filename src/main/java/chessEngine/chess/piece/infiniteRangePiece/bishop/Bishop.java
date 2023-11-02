package chessEngine.chess.piece.infiniteRangePiece.bishop;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;

public abstract class Bishop extends InfiniteRangePiece {

    public Bishop(PieceColor pieceColor, EnginePosition pos, String pieceCode, Field field) {
        super(pieceColor, pos, new byte[][] {
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}},
                pieceCode,
                field
        );
    }
}
