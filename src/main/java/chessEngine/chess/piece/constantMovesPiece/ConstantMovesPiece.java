package chessEngine.chess.piece.constantMovesPiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.EngineMove;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;

@Getter
@Setter
public abstract class ConstantMovesPiece extends Piece {
    protected final byte[][] constantMoves;

    @Override
    public void setMyPossibilities(PieceColor[][] colorMap) {
        ArrayList<EngineMove> movesList = new ArrayList<>();
        HashSet<Field> controlledFields = new HashSet<>();
        for (byte[] direction : this.constantMoves) {
            byte yDir = direction[0];
            byte xDir = direction[1];
            byte nextY = (byte) (this.field.height() + yDir);
            byte nextX = (byte) (this.field.width() + xDir);
            if (!this.correctFieldCoordinates(nextY, nextX)) {continue;}
            PieceColor tempPieceColor = colorMap[nextY][nextX];
            if (!tempPieceColor.equals(this.pieceColor)) {
                movesList.add(new EngineMove(this.field, new Field(nextY, nextX)));
            }
            controlledFields.add(new Field(nextY, nextX));
        }
        this.possibleMoves = movesList;
        this.controlledFields = controlledFields;
    }


    public ConstantMovesPiece(PieceColor pieceColor, EnginePosition pos, byte[][] constantsMoves) {
        super(pieceColor, pos);
        this.constantMoves = constantsMoves;
    }
}
