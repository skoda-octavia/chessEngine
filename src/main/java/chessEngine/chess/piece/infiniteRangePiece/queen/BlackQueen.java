package chessEngine.chess.piece.infiniteRangePiece.queen;

import chessEngine.chess.piece.PieceColor;

public class BlackQueen extends Queen {
    private final PieceColor  pieceColor = PieceColor.BLACK;

    public BlackQueen(byte height, byte width) {
        super(height, width);
    }
}
