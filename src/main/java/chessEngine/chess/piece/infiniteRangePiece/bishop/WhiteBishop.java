package chessEngine.chess.piece.infiniteRangePiece.bishop;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhiteBishop extends Bishop {
    public WhiteBishop(byte height, byte width, EnginePosition pos) {

        super(PieceColor.WHITE, pos);
        this.height = height;
        this.width = width;
    }
}
