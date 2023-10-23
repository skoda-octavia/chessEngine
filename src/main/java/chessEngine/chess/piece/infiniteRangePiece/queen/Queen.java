package chessEngine.chess.piece.infiniteRangePiece.queen;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Queen  extends InfiniteRangePiece {

    public Queen(PieceColor pieceColor, EnginePosition pos) {
        super(pieceColor, pos, new byte[][] {
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
