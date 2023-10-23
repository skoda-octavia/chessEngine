package chessEngine.chess.piece.constantMovesPiece.knight;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlackKnight extends Knight{

    public BlackKnight(byte height, byte width, EnginePosition pos) {
        super(PieceColor.BLACK, pos);
        this.height = height;
        this.width = width;
    }
}
