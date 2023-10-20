package chessEngine.chess.piece.infiniteRangePiece.queen;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;

public class BlackQueen extends Queen {
    private final PieceColor  pieceColor = PieceColor.BLACK;

    public BlackQueen(byte height, byte width, EnginePosition pos) {
        super(height, width, pos);
    }
}
