package chessEngine.chess.piece.infiniteRangePiece.rook;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.HeuristicGenerator;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.infiniteRangePiece.InfiniteRangePiece;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public abstract class Rook extends InfiniteRangePiece {

    public static final int HEU_VALUE = 50;
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
        if (rooksConnected(enginePosition)) {val += HeuristicGenerator.ROOKS_CONNECTED_REWARD;}
        if (discoveringPiece != null) {
            val += HeuristicGenerator.DISCOVERY_REWARD;
        }

        return val;
    }

    protected boolean rooksConnected(EnginePosition enginePosition) {
        Piece[][] chessboard = enginePosition.getChessBoard();
        for (Field controlledField: controlledFields) {
            if (chessboard[controlledField.getHeight()][controlledField.getWidth()] != null) {
                Piece tempPiece = chessboard[controlledField.getHeight()][controlledField.getWidth()];
                if(tempPiece instanceof Rook && tempPiece.getPieceColor().equals(pieceColor)) {
                    return true;
                }
            }
        }
        return false;
    }


    public Rook(PieceColor pieceColor, EnginePosition pos, String pieceCode, Field field) {
        super(pieceColor, pos, new byte[][] {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}},
                pieceCode,
                field
                );
    }
}

