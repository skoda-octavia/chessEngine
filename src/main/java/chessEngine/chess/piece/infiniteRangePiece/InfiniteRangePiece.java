package chessEngine.chess.piece.infiniteRangePiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.constantMovesPiece.king.King;
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
        Field enemyQueensField = null;
        Field enemyKingsField = null;

        try {enemyQueensField = this.position.queensField(enemyPieceColor);}
        catch (Exception e) {}
        try {enemyKingsField = this.pieceColor.equals(PieceColor.WHITE) ?
                position.getBlackKing().getField() : position.getWhiteKing().getField();}
        catch (Exception e) {throw new IllegalArgumentException("position has no king");}

        for (byte[] direction : this.directions) {
            byte yDir = direction[0];
            byte xDir = direction[1];
            byte nextY = (byte) (this.field.getHeight() + yDir);
            byte nextX = (byte) (this.field.getWidth() + xDir);
            boolean blocked = false;
            Piece pinnedPiece = null;
            Piece discoveryPiece = null;
            while (this.correctFieldCoordinates(nextY, nextX)) {
                PieceColor tempPieceColor = colorMap[nextY][nextX];
                Field nextField = new Field(nextY, nextX);
                if (tempPieceColor.equals(this.pieceColor) && blocked == false) {
                    controlledFields.add(nextField);
                    blocked = true;
                    discoveryPiece = this.position.getChessBoard()[nextY][nextX];
                }
                else if (tempPieceColor.equals(PieceColor.NONE) && blocked == false) {
                    movesList.add(new EngineMove(this.field, nextField));
                    controlledFields.add(nextField);
                }
                else if (tempPieceColor.equals(enemyPieceColor) && blocked == false){
                    controlledFields.add(nextField);
                    blocked = true;
                    pinnedPiece = this.position.getChessBoard()[nextY][nextX];
                    if (pinnedPiece instanceof King) {
                        nextY += yDir;
                        nextX += xDir;
                        nextField = new Field(nextY, nextX);
                        if (!correctFieldCoordinates(nextY, nextX)) {break;}
                        controlledFields.add(nextField);
                        pinnedPiece = null;
                        discoveryPiece = null;
                        break;
                    }
                    movesList.add(new EngineMove(this.field, nextField));

                }
                else if (blocked && tempPieceColor.equals(pieceColor.NONE)) {}
                else if (blocked && tempPieceColor.equals(enemyPieceColor)){
                    if (nextY == enemyKingsField.getHeight() && nextX == enemyKingsField.getWidth()) {
                        if (pinnedPiece != null){
                            pinnedPiece.setPinningPiece(this);
                        } else if (discoveryPiece != null) {
                            discoveryPiece.setDiscoveringPiece(this);
                        }
                        this.pinningKing = true;
                    } else if (enemyQueensField != null && nextY == enemyQueensField.getHeight() && nextX == enemyQueensField.getWidth()) {
                        this.pinningQueen = true;
                    }
                    pinnedPiece = null;
                    discoveryPiece = null;
                    break;
                }
                else {
                    pinnedPiece = null;
                    discoveryPiece = null;
                    break;
                }
                nextY += yDir;
                nextX += xDir;
            }
        }
        this.possibleMoves = movesList;
        this.controlledFields = controlledFields;
    }


    public InfiniteRangePiece(PieceColor pieceColor, EnginePosition pos, byte[][] directions, String pieceCode, Field field) {
        super(pieceColor, pos, pieceCode, field);
        this.directions = directions;
    }
}
