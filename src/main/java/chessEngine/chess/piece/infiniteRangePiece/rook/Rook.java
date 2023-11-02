package chessEngine.chess.piece.infiniteRangePiece.rook;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Rook extends InfiniteRangePiece {
    public Rook(PieceColor pieceColor, EnginePosition pos, String pieceCode, Field field) {
        super(pieceColor, pos, new byte[][] {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}},
                pieceCode,
                field
                );
    }
}

