package chessEngine.chess.piece.pawn;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.Move;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;

import java.util.ArrayList;

public abstract class Pawn extends Piece {
    private byte movingDirection;

    @Override
    public ArrayList<Move> possibleMoves(PieceColor[][] colorMap) {
        return new ArrayList<>();
    }

    public Pawn(byte height, byte width, EnginePosition pos) {
        super(height, width, pos);
    }
}
