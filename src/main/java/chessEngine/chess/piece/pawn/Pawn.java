package chessEngine.chess.piece.pawn;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.EngineMove;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.pieceGenerator.PieceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;

@Getter
@Setter
public abstract class Pawn extends Piece {
    protected final byte movingDirection;
    protected final byte startingRow;

    @Override
    public void setMyPossibilities(PieceColor[][] colorMap) {
        PieceColor enemyColor = enemyPieceColor();
        ArrayList<EngineMove> possibleEngineMoves = new ArrayList<>();
        HashSet<Field> controlledFields = new HashSet<>();
        byte[][] captureFields = new byte[][]{
                {(byte)(this.field.height() + this.movingDirection), (byte)(this.field.width() + 1)},
                {(byte)(this.field.height() + this.movingDirection), (byte)(this.field.width() - 1)}
        };
        for (byte[] field : captureFields) {
            byte nextY = field[0], nextX = field[1];
            if (this.correctFieldCoordinates(nextY, nextX)) {
                PieceColor tempPieceColor = colorMap[nextY][nextX];
                if (tempPieceColor.equals(enemyColor)) {
                    possibleEngineMoves.add(new EngineMove(this.field, new Field(nextY, nextX)));
                    }
                controlledFields.add(new Field(nextY, nextX));
            }
        }

        byte nextY = (byte)(this.field.height() + this.movingDirection), nextX = this.field.width();
        if (this.correctFieldCoordinates(nextY, nextX) && colorMap[nextY][nextX].equals(PieceColor.NONE)) {
            possibleEngineMoves.add(new EngineMove(
                    this.field, new Field(nextY, nextX)
            ));
        }

        if (this.field.height() == this.startingRow && colorMap[nextY][nextX].equals(PieceColor.NONE)) {
            byte nextYJump = (byte)(this.field.height() + 2 * this.movingDirection);
            if (colorMap[nextYJump][nextX].equals(PieceColor.NONE)) {
                possibleEngineMoves.add(new EngineMove(
                        this.field, new Field(nextY, nextX))
                );
            }
        }
        // TODO: 24.10.2023 en passant
        this.possibleMoves = possibleEngineMoves;
        this.controlledFields = controlledFields;
     }


    public Pawn(PieceColor pieceColor, EnginePosition pos, byte movingDirection, byte startingRow) {
        super(pieceColor, pos);
        this.startingRow = startingRow;
        this.movingDirection = movingDirection;
    }
}
