package chessEngine.chess.piece.constantMovesPiece.king;

import chessEngine.chess.piece.PieceColor;

public class BlackKing extends King {
    private final PieceColor pieceColor = PieceColor.BLACK;

    public BlackKing(byte height, byte width) {
        super(height, width);
    }
}
