package chessEngine.chess.piece.infiniteRangePiece.queen;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlackQueen extends Queen {
    public BlackQueen(byte height, byte width, EnginePosition pos) {

        super(PieceColor.BLACK, pos);
        this.height = height;
        this.width = width;
    }
}
