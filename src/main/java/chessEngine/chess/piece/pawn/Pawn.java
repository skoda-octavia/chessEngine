package chessEngine.chess.piece.pawn;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.EngineMoveCode;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;

@Getter
@Setter
public abstract class Pawn extends Piece {
    protected final byte movingDirection;
    protected final byte startingRow;

    protected final byte enpassantRow;
    protected final byte transformationRow;

    private boolean possibleEnPassant(PieceColor[][] colorMap) {
        EngineMove parenMove = position.getParentMove();
        if (parenMove == null || this.field.getHeight() != this.enpassantRow) {return false;}

        Field from = parenMove.getFrom();
        Field to = parenMove.getTo();

        if (
                from.getHeight() != this.field.getHeight() + 2 * this.movingDirection ||
                to.getHeight() != this.field.getHeight()
        ) {return false;}

        if (
                from.getWidth() != to.getWidth() ||
                Math.abs(this.field.getWidth() - to.getWidth()) != 1
        ) {return false;}

        if (
                !position.pawnOnField(to) ||
                !colorMap[to.getHeight()][to.getWidth()].equals(enemyPieceColor())
        ) {return false;}
        return true;
    }

    private void addTransformationMoves(Field oldField, Field newField, ArrayList<EngineMove> list) {
        EngineMoveCode[] transformations = new EngineMoveCode[] {
            EngineMoveCode.QUEEN,
            EngineMoveCode.ROOK,
            EngineMoveCode.BISHOP,
            EngineMoveCode.KNIGHT
        };
        for (EngineMoveCode engineMoveCode : transformations) {
            list.add(
                    new EngineMove(
                        oldField,
                        newField,
                        engineMoveCode
                    )
            );
        }
    }

    @Override
    public void setMyPossibilities(PieceColor[][] colorMap) {
        PieceColor enemyColor = enemyPieceColor();
        ArrayList<EngineMove> possibleEngineMoves = new ArrayList<>();
        HashSet<Field> controlledFields = new HashSet<>();
        byte[][] captureFields = new byte[][]{
                {(byte)(this.field.getHeight() + this.movingDirection), (byte)(this.field.getWidth() + 1)},
                {(byte)(this.field.getHeight() + this.movingDirection), (byte)(this.field.getWidth() - 1)}
        };
        for (byte[] field : captureFields) {
            byte nextY = field[0], nextX = field[1];
            if (this.correctFieldCoordinates(nextY, nextX)) {
                PieceColor tempPieceColor = colorMap[nextY][nextX];
                if (tempPieceColor.equals(enemyColor)) {
                    Field nextField = new Field(nextY, nextX);
                    if (nextY == this.transformationRow) {
                        addTransformationMoves(this.field, nextField, possibleEngineMoves);
                    }
                    else {
                        possibleEngineMoves.add(new EngineMove(this.field, new Field(nextY, nextX)));
                    }
                }
                controlledFields.add(new Field(nextY, nextX));
            }
        }

        byte nextY = (byte)(this.field.getHeight() + this.movingDirection), nextX = this.field.getWidth();
        if (this.correctFieldCoordinates(nextY, nextX) && colorMap[nextY][nextX].equals(PieceColor.NONE)) {
            Field nextField = new Field(nextY, nextX);
            if(nextY == this.transformationRow) {
                addTransformationMoves(this.field, nextField, possibleEngineMoves);
            }
            else {possibleEngineMoves.add(new EngineMove(this.field, nextField));}
        }

        if (this.field.getHeight() == this.startingRow && colorMap[nextY][nextX].equals(PieceColor.NONE)) {
            byte nextYJump = (byte)(this.field.getHeight() + 2 * this.movingDirection);
            if (colorMap[nextYJump][nextX].equals(PieceColor.NONE)) {
                possibleEngineMoves.add(new EngineMove(
                        this.field, new Field(nextYJump, nextX))
                );
            }
        }

        if (possibleEnPassant(colorMap)) {
            EngineMove parentMove = position.getParentMove();
            possibleEngineMoves.add(new EngineMove(
                    this.field,
                    new Field(
                            (byte)(this.field.getHeight() + movingDirection),
                            parentMove.getTo().getWidth()),
                            EngineMoveCode.ENPASSANT
                    )
                );
        }

        this.possibleMoves = possibleEngineMoves;
        this.controlledFields = controlledFields;
     }


    public Pawn(PieceColor pieceColor,
                EnginePosition pos,
                byte movingDirection,
                byte startingRow,
                byte enpassantRow,
                byte transformationRow,
                String pieceCode,
                Field field) {
        super(pieceColor, pos, pieceCode, field);
        this.startingRow = startingRow;
        this.movingDirection = movingDirection;
        this.enpassantRow = enpassantRow;
        this.transformationRow = transformationRow;
    }
}
