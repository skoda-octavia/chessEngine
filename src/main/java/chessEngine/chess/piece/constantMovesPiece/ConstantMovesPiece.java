package chessEngine.chess.piece.constantMovesPiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.Move;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public abstract class ConstantMovesPiece extends Piece {
    protected final byte[][] constantsMoves;

    @Override
    public ArrayList<Move> possibleMoves(PieceColor[][] colorMap) {
        return new ArrayList<>();
    }

    public ConstantMovesPiece(PieceColor pieceColor, EnginePosition pos, byte[][] constantsMoves) {
        super(pieceColor, pos);
        this.constantsMoves = constantsMoves;
    }
}
