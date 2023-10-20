package chessEngine.chess.piece.constantMovesPiece.knight;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;

public class BlackKnight extends Knight{
    private final PieceColor pieceColor = PieceColor.BLACK;

    public BlackKnight(byte height, byte width, EnginePosition pos) {
        super(height, width, pos);
    }
}
