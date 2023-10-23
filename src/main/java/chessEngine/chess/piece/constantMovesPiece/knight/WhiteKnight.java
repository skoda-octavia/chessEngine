package chessEngine.chess.piece.constantMovesPiece.knight;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhiteKnight extends Knight{
    private final PieceColor pieceColor = PieceColor.WHITE;

    public WhiteKnight(byte height, byte width, EnginePosition pos) {
        super(PieceColor.WHITE, pos);
        this.height = height;
        this.width = width;
    }
}
