package chessEngine.chess.piece.infiniteRangePiece;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.Move;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;

import java.util.ArrayList;

public abstract class InfiniteRangePiece extends Piece {
    private byte[][] directions;

    @Override
    public ArrayList<Move> possibleMoves(PieceColor[][] colorMap) {
        ArrayList<Move> movesList = new ArrayList<>();
//        for (byte[] direction : this.directions) {
//            byte yDir = direction[0];
//            byte xDir = direction[1];
//            byte nextY = this.height + yDir;
//            int nextX = this.width + xDir;
//            while (this.correctFieldCoordinates(nextY, nextX)) {
//
//            }
//
//        }

        return movesList;
    }

    public InfiniteRangePiece(byte height, byte width, EnginePosition pos) {
        super(height, width, pos);
    }
}
