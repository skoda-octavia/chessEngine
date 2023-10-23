package chessEngine.chess.piece.constantMovesPiece.king;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlackKing extends King {
    public BlackKing(byte height, byte width, EnginePosition pos) {
        super(PieceColor.BLACK, pos);
        this.height = height;
        this.width = width;
    }
}
