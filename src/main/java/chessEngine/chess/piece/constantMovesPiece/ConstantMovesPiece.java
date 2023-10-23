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
    protected final byte[][] constantMoves;

    @Override
    public ArrayList<Move> possibleMoves(PieceColor[][] colorMap) {
        ArrayList<Move> movesList = new ArrayList<>();
        for (byte[] direction : this.constantMoves) {
            byte yDir = direction[0];
            byte xDir = direction[1];
            byte nextY = (byte) (this.height + yDir);
            byte nextX = (byte) (this.width + xDir);
            if (!this.correctFieldCoordinates(nextY, nextX)) {continue;}
            PieceColor tempPieceColor = colorMap[nextY][nextX];
            if (!tempPieceColor.equals(this.pieceColor)) {movesList.add(new Move(this.height, this.width, nextY, nextX));}
        }
        return movesList;
    }

    public ConstantMovesPiece(PieceColor pieceColor, EnginePosition pos, byte[][] constantsMoves) {
        super(pieceColor, pos);
        this.constantMoves = constantsMoves;
    }
}
