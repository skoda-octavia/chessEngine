package chessEngine.chess.piece.constantMovesPiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.Move;
import chessEngine.chess.move.field.Field;
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
            byte nextY = (byte) (this.field.height() + yDir);
            byte nextX = (byte) (this.field.width() + xDir);
            if (!this.correctFieldCoordinates(nextY, nextX)) {continue;}
            PieceColor tempPieceColor = colorMap[nextY][nextX];
            if (!tempPieceColor.equals(this.pieceColor)) {movesList.add(new Move(
                    this.field, new Field(nextY, nextX))
            );}
        }
        return movesList;
    }

    @Override
    public ArrayList<Field> controlledFields(PieceColor[][] colorMap) {
        ArrayList<Field> controlledFields = new ArrayList<>();
        for (byte[] direction : this.constantMoves) {
            byte yDir = direction[0];
            byte xDir = direction[1];
            byte nextY = (byte) (this.field.height() + yDir);
            byte nextX = (byte) (this.field.width() + xDir);
            if (!this.correctFieldCoordinates(nextY, nextX)) {continue;}
            controlledFields.add(new Field(nextY, nextX));
        }
        return controlledFields;
    }

    public ConstantMovesPiece(PieceColor pieceColor, EnginePosition pos, byte[][] constantsMoves) {
        super(pieceColor, pos);
        this.constantMoves = constantsMoves;
    }
}
