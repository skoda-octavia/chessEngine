package chessEngine.chess;

import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.Piece;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Iterator;

@AllArgsConstructor
public class HeuristicGenerator {

    public static final int FIELDS_CONTROLLED_RATIO = 8;
    public static final int PINNING_REWARD = 60;
    public static final int PINNED_PENALTY = 45;
    public static final int MOVING_REWARD = 60;
    public static final int WINNING_REWARD = 100000;
    public static final int UNDER_CHECK_PENALTY = 200;
    public static final int ATTACKED_PIECE_RATIO = 8;
    public static final int ROOKS_CONNECTED_REWARD = 110;

    public static final int DISCOVERY_REWARD = 90;
    public static final int DOUBLED_PAWN_PENALTY = 65;
    public static final int CASTLING_THRESHOLD = 100;

    private final EnginePosition enginePosition;

    public int generate() {
        if (whiteWon()) {return WINNING_REWARD;}
        if(blackWon()) {return -WINNING_REWARD;}
        if(draw()) {return 0;}
        int value = 0;
        Iterator<Piece> iterator = enginePosition.getWhitePieces().iterator();
        while (iterator.hasNext()) {
            Piece tempPiece = iterator.next();
            value += tempPiece.generateHeuristicValue(enginePosition);
        }
        iterator = enginePosition.getBlackPieces().iterator();
        while (iterator.hasNext()) {
            Piece tempPiece = iterator.next();
            value -= tempPiece.generateHeuristicValue(enginePosition);
        }
        value += enginePosition.isWhiteMoves() ? MOVING_REWARD : -MOVING_REWARD;


        return value;
    }

    private boolean draw() {
        if(enginePosition.getPossibleLegalMoves().size() == 0) {
            return true;
        }
        return false;
    }

    private boolean whiteWon() {
        if (!enginePosition.isWhiteMoves() && enginePosition.getPossibleLegalMoves().size() == 0) {
            Field blackKingsField = enginePosition.getBlackKing().getField();
            if (enginePosition.getWhiteControls().containsKey(blackKingsField)) {
                return true;
            }
        }
        return false;
    }

    private boolean blackWon() {
        if (enginePosition.isWhiteMoves() && enginePosition.getPossibleLegalMoves().size() == 0) {
            Field whiteKingsField = enginePosition.getWhiteKing().getField();
            if (enginePosition.getWhiteControls().containsKey(whiteKingsField)) {
                return true;
            }
        }
        return false;
    }
}
