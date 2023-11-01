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
    protected boolean pinsQueen = false;
    protected boolean pinsKing = false;

    @Override
    public void setMyPossibilities(PieceColor[][] colorMap) {
        PieceColor enemyPieceColor = this.enemyPieceColor();
        ArrayList<EngineMove> movesList = new ArrayList<>();
        HashSet<Field> controlledFields = new HashSet<>();
        Field queensField = this.position.queensField(enemyPieceColor);
        Field kingsField = this.position.kingsField(enemyPieceColor);
        for (byte[] direction : this.directions) {
            byte yDir = direction[0];
            byte xDir = direction[1];
            byte nextY = (byte) (this.field.height() + yDir);
            byte nextX = (byte) (this.field.width() + xDir);
            boolean blocked = false;
            Piece pinnedPiece = null;
            while (this.correctFieldCoordinates(nextY, nextX)) {
                PieceColor tempPieceColor = colorMap[nextY][nextX];
                if (tempPieceColor.equals(this.pieceColor) && blocked == false) {
                    controlledFields.add(new Field(nextY, nextX));
                    blocked = true;
                    pinnedPiece = this.position.getChessBoard()[nextY][nextX];
                    nextY += yDir;
                    nextX += xDir;
                }
                else if (tempPieceColor.equals(PieceColor.NONE) && blocked == false) {
                    movesList.add(new EngineMove(
                            this.field, new Field(nextY, nextX)
                    ));
                    controlledFields.add(new Field(nextY, nextX));
                    nextY += yDir;
                    nextX += xDir;
                }
                else if (tempPieceColor.equals(enemyPieceColor) && blocked == false){
                    blocked = true;
                    pinnedPiece = this.position.getChessBoard()[nextY][nextX];
                    movesList.add(new EngineMove(this.field, new Field(nextY, nextX)));
                    controlledFields.add(new Field(nextY, nextX));
                    nextY += yDir;
                    nextX += xDir;
                }
                else if (blocked && tempPieceColor.equals(pieceColor.NONE)) {
                    nextY += yDir;
                    nextX += xDir;
                }
                else if (blocked && tempPieceColor.equals(enemyPieceColor)){
                    if (nextY == kingsField.height() && nextX == kingsField.width()) {
                        pinnedPiece.setPinnedDirection(new byte[]{yDir, xDir});
                        this.pinsKing = true;
                    } else if (nextY == queensField.height() && nextX == queensField.width()) {
                        this.pinsQueen = true;
                    }
                    pinnedPiece = null;
                    break;
                }
                else {
                    pinnedPiece = null;
                    break;
                }
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
