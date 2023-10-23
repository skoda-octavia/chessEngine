package chessEngine.chess.piece.infiniteRangePiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.Move;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
public abstract class InfiniteRangePiece extends Piece {
    protected final byte[][] directions;

    @Override
    public ArrayList<Move> possibleMoves(PieceColor[][] colorMap) {
        ArrayList<Move> movesList = new ArrayList<>();
        for (byte[] direction : this.directions) {
            byte yDir = direction[0];
            byte xDir = direction[1];
            byte nextY = (byte) (this.height + yDir);
            byte nextX = (byte) (this.width + xDir);
            while (this.correctFieldCoordinates(nextY, nextX)) {
                PieceColor tempPieceColor = colorMap[nextY][nextX];
                if (tempPieceColor.equals(this.pieceColor)) {
                    break;
                }
                else if (tempPieceColor.equals(PieceColor.NONE)) {
                    movesList.add(new Move(this.height, this.width, nextY, nextX));
                    nextY += yDir;
                    nextX += xDir;
                }
                else {
                    movesList.add(new Move(this.height, this.width, nextY, nextX));
                    break;
                }
            }
        }
        return movesList;
    }

    public InfiniteRangePiece(PieceColor pieceColor, EnginePosition pos, byte[][] directions) {
        super(pieceColor, pos);
        this.directions = directions;
    }
}
