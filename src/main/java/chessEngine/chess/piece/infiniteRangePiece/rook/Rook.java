package chessEngine.chess.piece.infiniteRangePiece.rook;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Rook extends InfiniteRangePiece {
    public Rook(byte height, byte width, EnginePosition pos) {
        super(height, width, pos, new byte[][] {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}
        });
    }
}

