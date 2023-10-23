package chessEngine.chess.piece.infiniteRangePiece.queen;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhiteQueen  extends Queen {

    public WhiteQueen(byte height, byte width, EnginePosition pos) {
        super(PieceColor.WHITE, pos);
        this.height = height;
        this.width = width;
    }
}
