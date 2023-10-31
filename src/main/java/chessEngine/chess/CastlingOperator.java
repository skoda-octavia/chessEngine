package chessEngine.chess;


import chessEngine.chess.move.EngineMove;
import chessEngine.chess.piece.PieceColor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@AllArgsConstructor
@Data
public class CastlingOperator {

    private final EnginePosition enginePosition;

    ArrayList<EngineMove> possibleCastlingMoves(PieceColor color) {
        switch (color) {
            case WHITE : return this.possibleWhiteCastllingMoves();
            case BLACK : return this.possibleBlackCastlingMoves();

            default:
                String message = "Not supported pieceColor: " + color;
                throw new IllegalArgumentException(message);
        }

    }

    private ArrayList<EngineMove> possibleBlackCastlingMoves() {
        return new ArrayList<>();
    }

    private ArrayList<EngineMove> possibleWhiteCastllingMoves() {
        return null;
    }

}
