package chessEngine.chess.piece.pawn;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhitePawn extends Pawn{

    public WhitePawn(byte height, byte width, EnginePosition pos) {
        super(PieceColor.WHITE, pos, (byte)-1);
        this.height = height;
        this.width = width;
    }
}
