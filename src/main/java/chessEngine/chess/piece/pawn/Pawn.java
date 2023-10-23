package chessEngine.chess.piece.pawn;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.Move;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public abstract class Pawn extends Piece {
    protected final byte movingDirection;

    @Override
    public ArrayList<Move> possibleMoves(PieceColor[][] colorMap) {
        return new ArrayList<>();
    }

    public Pawn(PieceColor pieceColor, EnginePosition pos, byte movingDirection) {
        super(pieceColor, pos);
        this.movingDirection = movingDirection;
    }
}
