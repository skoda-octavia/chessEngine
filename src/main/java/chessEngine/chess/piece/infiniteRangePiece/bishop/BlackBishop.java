package chessEngine.chess.piece.infiniteRangePiece.bishop;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlackBishop extends Bishop {

    public BlackBishop(byte height, byte width, EnginePosition pos) {

        super(PieceColor.BLACK, pos);
        this.height = height;
        this.width = width;
    }
}
