package chessEngine.chess.piece.pawn;

import chessEngine.chess.piece.PieceColor;

public class BlackPawn extends Pawn {
    private final byte movingDirection = 1;
    private final PieceColor pieceColor = PieceColor.BLACK;

    public BlackPawn(byte height, byte width) {
        super(height, width);
    }
}
