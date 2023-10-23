package chessEngine.chess.piece.infiniteRangePiece.rook;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhiteRook extends Rook {

    public WhiteRook(byte height, byte width, EnginePosition pos) {
        super(PieceColor.WHITE, pos);
        this.height = height;
        this.width = width;
    }
}
