package chessEngine.chess.piece.constantMovesPiece.king;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.constantMovesPiece.ConstantMovesPiece;

import java.util.ArrayList;

public abstract class King extends ConstantMovesPiece {

    public void setCastlingMoves() {
        PieceColor movingColor = position.isWhiteMoves() ? PieceColor.WHITE : PieceColor.BLACK;
        if (movingColor.equals(this.pieceColor)) {
            ArrayList<EngineMove> castlingMoves = position.possibleCastlingMoves(this.pieceColor);
            this.possibleMoves.addAll(castlingMoves);
        }
    }

    public King(PieceColor pieceColor, EnginePosition pos, String pieceCode, Field field) {
        super(pieceColor, pos, new byte[][] {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1},
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}},
                pieceCode,
                field
                );
    }
}
