package chessEngine.chess.piece.infiniteRangePiece.rook;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlackRook extends Rook {

    public BlackRook(byte height, byte width, EnginePosition pos) {
        super(PieceColor.BLACK, pos);
        this.height = height;
        this.width = width;

    }
}
