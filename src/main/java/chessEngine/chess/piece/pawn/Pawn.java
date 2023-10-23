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
    protected final byte startingRow;

    @Override
    public ArrayList<Move> possibleMoves(PieceColor[][] colorMap) {
        ArrayList<Move> possibleMoves = new ArrayList<>();
        byte[][] captureFields = new byte[][]{
                {(byte)(this.height + this.movingDirection), (byte)(this.width + 1)},
                {(byte)(this.height + this.movingDirection), (byte)(this.width - 1)}
        };
        for (byte[] field : captureFields) {
            byte nextY = field[0], nextX = field[1];
            if (this.correctFieldCoordinates(nextY, nextX)) {
                PieceColor tempPieceColor = colorMap[nextY][nextX];
                if (tempPieceColor.equals(this.pieceColor) || tempPieceColor.equals(PieceColor.NONE)) {}
                else {possibleMoves.add(new Move(this.height, this.width, nextY, nextX));}
            }
        }

        byte nextY = (byte)(this.height + this.movingDirection), nextX = this.width;
        if (this.correctFieldCoordinates(nextY, nextX) && colorMap[nextY][nextX].equals(PieceColor.NONE)) {
            possibleMoves.add(new Move(this.height, this.width, nextY, nextX));
        }

        if (this.height == this.startingRow && colorMap[nextY][nextX].equals(PieceColor.NONE)) {
            byte nextYJump = (byte)(this.height + 2 * this.movingDirection);
            if (colorMap[nextYJump][nextX].equals(PieceColor.NONE)) {
                possibleMoves.add(new Move(this.height, this.width, nextYJump, nextX));
            }
        }
        // TODO: 24.10.2023 en passant
        return possibleMoves;
    }

    public Pawn(PieceColor pieceColor, EnginePosition pos, byte movingDirection, byte startingRow) {
        super(pieceColor, pos);
        this.startingRow = startingRow;
        this.movingDirection = movingDirection;
    }
}
