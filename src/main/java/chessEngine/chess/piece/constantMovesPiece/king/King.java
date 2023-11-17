package chessEngine.chess.piece.constantMovesPiece.king;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.HeuristicGenerator;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.constantMovesPiece.ConstantMovesPiece;
import chessEngine.chess.piece.pawn.Pawn;

import java.util.*;

public abstract class King extends ConstantMovesPiece {

    public static final int HEU_VALUE = 300;
    public void setCastlingMoves() {
        PieceColor movingColor = position.isWhiteMoves() ? PieceColor.WHITE : PieceColor.BLACK;
        if (movingColor.equals(this.pieceColor)) {
            ArrayList<EngineMove> castlingMoves = position.possibleCastlingMoves(this.pieceColor);
            this.possibleMoves.addAll(castlingMoves);
        }
    }

    @Override
    public int generateHeuristicValue(EnginePosition enginePosition) {
        int val = HEU_VALUE;
        val += controlledFields.size();
        HashMap enemyControls = null;
        if (this.pieceColor.equals(PieceColor.WHITE)) {enemyControls = enginePosition.getBlackControls();}
        else {enemyControls = enginePosition.getWhiteControls();}
        if (enemyControls.containsKey(this.field)) {
            val -= HeuristicGenerator.UNDER_CHECK_PENALTY;
        }
        if (discoveringPiece != null) {
            val += HeuristicGenerator.DISCOVERY_REWARD;
        }
        return val;
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
