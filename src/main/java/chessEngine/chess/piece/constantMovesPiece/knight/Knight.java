package chessEngine.chess.piece.constantMovesPiece.knight;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.HeuristicGenerator;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.constantMovesPiece.ConstantMovesPiece;
import chessEngine.chess.piece.pawn.Pawn;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class Knight extends ConstantMovesPiece {

    public static final int HEU_VALUE = 300;
    @Override
    public int generateHeuristicValue(EnginePosition enginePosition) {
        int val = HEU_VALUE;
        val += controlledFields.size() * HeuristicGenerator.FIELDS_CONTROLLED_RATIO;
        HashMap enemyControls = null;
        int attackingNumber = enginePosition.getWhiteControls().getOrDefault(field, (byte)0) - enginePosition.getBlackControls().getOrDefault(field, (byte)0);
        if (pieceColor.equals(PieceColor.BLACK)) {
            val -= attackingNumber * HeuristicGenerator.ATTACKED_PIECE_RATIO;
        }
        else {
            val += attackingNumber*HeuristicGenerator.ATTACKED_PIECE_RATIO;
        }
        if (pinningPiece != null ) {
            val -= HeuristicGenerator.PINNED_PENALTY;
        }
        if (discoveringPiece != null) {
            val += HeuristicGenerator.DISCOVERY_REWARD;
        }
        if (pawnAttacks(enginePosition)) {
            val -= HEU_VALUE / 2;
        }
        return val;
    }

    public Knight(PieceColor pieceColor, EnginePosition pos, String pieceCode, Field field) {
        super(pieceColor, pos, new byte[][] {
                {-2, 1},
                {2, 1},
                {-2, -1},
                {2, -1},
                {1, -2},
                {1, 2},
                {-1, 2},
                {-1, -2}},
                pieceCode,
                field
                );
    }
}
