package chessEngine.chess.piece.infiniteRangePiece;

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
public abstract class InfiniteRangePiece extends Piece {
    protected final byte[][] directions;

    @Override
    public ArrayList<EngineMove> possibleMoves(PieceColor[][] colorMap) {
        ArrayList<EngineMove> movesList = new ArrayList<>();
        for (byte[] direction : this.directions) {
            byte yDir = direction[0];
            byte xDir = direction[1];
            byte nextY = (byte) (this.field.height() + yDir);
            byte nextX = (byte) (this.field.width() + xDir);
            while (this.correctFieldCoordinates(nextY, nextX)) {
                PieceColor tempPieceColor = colorMap[nextY][nextX];
                if (tempPieceColor.equals(this.pieceColor)) {
                    break;
                }
                else if (tempPieceColor.equals(PieceColor.NONE)) {
                    movesList.add(new EngineMove(
                            this.field, new Field(nextY, nextX)
                    ));
                    nextY += yDir;
                    nextX += xDir;
                }
                else {
                    movesList.add(new EngineMove(
                            this.field, new Field(nextY, nextX)
                    ));
                    break;
                }
            }
        }
        return movesList;
    }

    @Override
    public HashSet<Field> controlledFields(PieceColor[][] colorMap) {
        if (this.controlledFields != null) {return this.controlledFields;}
        HashSet<Field> controlledFields = new HashSet<>();
        for (byte[] direction : this.directions) {
            byte yDir = direction[0];
            byte xDir = direction[1];
            byte nextY = (byte) (this.field.height() + yDir);
            byte nextX = (byte) (this.field.width() + xDir);
            while (this.correctFieldCoordinates(nextY, nextX)) {
                PieceColor tempPieceColor = colorMap[nextY][nextX];
                controlledFields.add(new Field(nextY, nextX));
                if (tempPieceColor.equals(PieceColor.NONE)) {
                    nextY += yDir;
                    nextX += xDir;
                }
                else {
                    break;
                }
            }
        }
        this.controlledFields = controlledFields;
        return controlledFields;
    }

    public InfiniteRangePiece(PieceColor pieceColor, EnginePosition pos, byte[][] directions) {
        super(pieceColor, pos);
        this.directions = directions;
    }
}
