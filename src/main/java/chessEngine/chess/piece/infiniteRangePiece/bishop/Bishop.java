package chessEngine.chess.piece.infiniteRangePiece.bishop;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.HeuristicGenerator;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;

import java.util.HashMap;

public abstract class Bishop extends InfiniteRangePiece {

    public static final int HEU_VALUE = 30;
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
        if(pinningKing || pinningQueen) {
            val += HeuristicGenerator.PINNING_REWARD;
        }
        if (discoveringPiece != null) {
            val += HeuristicGenerator.DISCOVERY_REWARD;
        }
        return val;
    }

    public Bishop(PieceColor pieceColor, EnginePosition pos, String pieceCode, Field field) {
        super(pieceColor, pos, new byte[][] {
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}},
                pieceCode,
                field
        );
    }
}
