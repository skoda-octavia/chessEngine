package chessEngine.chess.piece.constantMovesPiece.king;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;

public class BlackKing extends King {
    private final PieceColor pieceColor = PieceColor.BLACK;

    public BlackKing(byte height, byte width, EnginePosition pos) {
        super(height, width, pos);
    }
}
