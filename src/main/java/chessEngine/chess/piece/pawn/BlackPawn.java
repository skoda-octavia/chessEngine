package chessEngine.chess.piece.pawn;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlackPawn extends Pawn {
    public BlackPawn(byte height, byte width, EnginePosition pos) {

        super(PieceColor.BLACK, pos, (byte)1, (byte)1);
        this.height = height;
        this.width = width;
    }
}
