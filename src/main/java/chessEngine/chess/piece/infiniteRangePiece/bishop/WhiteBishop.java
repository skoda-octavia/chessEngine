package chessEngine.chess.piece.infiniteRangePiece.bishop;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;

public class WhiteBishop extends Bishop {

    private final PieceColor pieceColor = PieceColor.WHITE;

    public WhiteBishop(byte height, byte width, EnginePosition pos) {
        super(height, width, pos);
    }
}
