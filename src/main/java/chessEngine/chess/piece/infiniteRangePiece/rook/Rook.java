package chessEngine.chess.piece.infiniteRangePiece.rook;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Rook extends InfiniteRangePiece {
    public Rook(PieceColor pieceColor, EnginePosition pos) {
        super(pieceColor, pos, new byte[][] {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}
        });
    }
}

