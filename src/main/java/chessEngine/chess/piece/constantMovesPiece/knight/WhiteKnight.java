package chessEngine.chess.piece.constantMovesPiece.knight;

import chessEngine.chess.piece.PieceColor;

public class WhiteKnight extends Knight{
    private final PieceColor pieceColor = PieceColor.WHITE;

    public WhiteKnight(byte height, byte width) {
        super(height, width);
    }
}
