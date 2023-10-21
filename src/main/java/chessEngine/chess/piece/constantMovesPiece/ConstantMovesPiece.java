package chessEngine.chess.piece.constantMovesPiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.Move;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;

import java.util.ArrayList;

public abstract class ConstantMovesPiece extends Piece {
    protected final byte[][] constantsMoves;

    @Override
    public ArrayList<Move> possibleMoves(PieceColor[][] colorMap) {
        return new ArrayList<>();
    }

    public ConstantMovesPiece(byte height, byte width, EnginePosition pos, byte[][] constantsMoves) {
        super(height, width, pos);
        this.constantsMoves = constantsMoves;
    }
}
