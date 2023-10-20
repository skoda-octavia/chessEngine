package chessEngine.chess;

import chessEngine.chess.piece.Piece;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Move {
    private final byte fromY;
    private final byte fromX;
    private final byte toY;
    private final byte toX;
    private EnginePosition parentPosition;
    private String transformationPieceCode;


    public Move(byte fromY, byte fromX, byte toY, byte toX) {
        this.fromY = fromY;
        this.fromX = fromX;
        this.toY = toY;
        this.toX = toX;
    }

}
