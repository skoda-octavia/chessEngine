package chessEngine.chess.piece.pawn;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.piece.PieceColor;

public class WhitePawn extends Pawn{
    private final byte movingDirection = -1;
    private final PieceColor pieceColor = PieceColor.WHITE;

    public WhitePawn(byte height, byte width, EnginePosition pos) {
        super(height, width, pos);
    }
}
