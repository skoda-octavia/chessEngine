package chessEngine.chess.piece.infiniteRangePiece.rook;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlackRook extends Rook {
    private PieceColor pieceColor = PieceColor.BLACK;

    public BlackRook(byte height, byte width, EnginePosition pos) {
        super(height, width, pos);

    }
}
