package chessEngine.chess.piece.infiniteRangePiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.field.Field;
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
    protected boolean pinningQueen = false;
    protected boolean pinningKing = false;

    @Override
    public void setMyPossibilities(PieceColor[][] colorMap) {
        PieceColor enemyPieceColor = this.enemyPieceColor();
        ArrayList<EngineMove> movesList = new ArrayList<>();
        HashSet<Field> controlledFields = new HashSet<>();
        Field queensField = null;
        Field kingsField = null;

        try {queensField = this.position.queensField(enemyPieceColor);}
        catch (Exception e) {}
        try {kingsField = this.position.kingsField(enemyPieceColor);}
        catch (Exception e) {throw new IllegalArgumentException("position has no king");}

        for (byte[] direction : this.directions) {
            byte yDir = direction[0];
            byte xDir = direction[1];
            byte nextY = (byte) (this.field.height() + yDir);
            byte nextX = (byte) (this.field.width() + xDir);
            boolean blocked = false;
            Piece pinnedPiece = null;
            while (this.correctFieldCoordinates(nextY, nextX)) {
                PieceColor tempPieceColor = colorMap[nextY][nextX];
                Field nextField = new Field(nextY, nextX);
                if (tempPieceColor.equals(this.pieceColor) && blocked == false) {
                    controlledFields.add(nextField);
                    blocked = true;
                    pinnedPiece = this.position.getChessBoard()[nextY][nextX];
                }
                else if (tempPieceColor.equals(PieceColor.NONE) && blocked == false) {
                    movesList.add(new EngineMove(this.field, nextField));
                    controlledFields.add(nextField);
                }
                else if (tempPieceColor.equals(enemyPieceColor) && blocked == false){
                    blocked = true;
                    pinnedPiece = this.position.getChessBoard()[nextY][nextX];
                    movesList.add(new EngineMove(this.field, nextField));
                    controlledFields.add(nextField);
                }
                else if (blocked && tempPieceColor.equals(pieceColor.NONE)) {}
                else if (blocked && tempPieceColor.equals(enemyPieceColor)){
                    if (nextY == kingsField.height() && nextX == kingsField.width()) {
                        pinnedPiece.setPinnedDirection(new byte[]{yDir, xDir});
                        this.pinningKing = true;
                    } else if (queensField != null && nextY == queensField.height() && nextX == queensField.width()) {
                        this.pinningQueen = true;
                    }
                    pinnedPiece = null;
                    break;
                }
                else {
                    pinnedPiece = null;
                    break;
                }
                nextY += yDir;
                nextX += xDir;
            }
        }
        this.possibleMoves = movesList;
        this.controlledFields = controlledFields;
    }


    public InfiniteRangePiece(PieceColor pieceColor, EnginePosition pos, byte[][] directions) {
        super(pieceColor, pos);
        this.directions = directions;
    }
}
