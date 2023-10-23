package chessEngine.chess.piece.constantMovesPiece.king;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhiteKing extends King {
    public WhiteKing(byte height, byte width, EnginePosition pos) {
        super(PieceColor.WHITE, pos);
        this.height = height;
        this.width = width;
    }
}
