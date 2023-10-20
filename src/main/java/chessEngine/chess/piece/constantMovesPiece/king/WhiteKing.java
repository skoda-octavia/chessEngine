package chessEngine.chess.piece.constantMovesPiece.king;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;

@Getter
public class WhiteKing extends King {
    private final PieceColor pieceColor = PieceColor.WHITE;

    public WhiteKing(byte height, byte width, EnginePosition pos) {
        super(height, width, pos);
    }
}
