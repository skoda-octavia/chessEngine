package chessEngine.chess.piece.infiniteRangePiece.rook;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;

public class WhiteRook extends Rook {
    private PieceColor pieceColor = PieceColor.WHITE;

    public WhiteRook(byte height, byte width, EnginePosition pos) {
        super(height, width, pos);
    }
}
