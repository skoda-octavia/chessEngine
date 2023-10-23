package chessEngine.chess;

import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EnginePosition {
    private final byte boardHeight = 8;
    private final byte boardWidth = 8;
    private EnginePosition parentPosition = null;
    private int value = 0;
    private String positionCode = "";
    private PieceColor movingColor = PieceColor.NONE;
    private Piece[][] chessBoard = new Piece[this.boardHeight][this.boardWidth];
    private PieceColor[][] colorMap = null;
    private LinkedList<Piece> blackPieces;
    private LinkedList<Piece> whitePieces;

    public PieceColor[][] getColorMap() {
        if (this.colorMap == null) {
            this.colorMap = this.generateColorMap();
        }
        return this.colorMap;
    }

    private PieceColor[][] generateColorMap() {

        PieceColor[][] colorMap = new PieceColor[this.boardHeight][this.boardWidth];
        for (byte h = 0; h < this.boardHeight; h++) {
            for (byte w = 0; w< this.boardWidth; w++ ) {
                Piece tempPiece = this.chessBoard[h][w];
                if (tempPiece == null) {colorMap[h][w] = PieceColor.NONE;}
                else {colorMap[h][w] = tempPiece.getPieceColor();}
            }
        }
        return colorMap;
    }

    public EnginePosition(String positionCode, boolean whiteMoves) {
        if (positionCode.length() != this.boardHeight * this.boardWidth * 2) {
            String message = "Incorrect len of positionCode: " + positionCode.length();
            throw new IllegalArgumentException(message);
        }

        PieceColor movingColor = whiteMoves ? PieceColor.WHITE : PieceColor.BLACK;
        this.setMovingColor(movingColor);
        this.setPositionCode(positionCode);

        int codeIndex = 0;
        for (byte height = 0; height < this.boardHeight; height++) {
            for (byte width = 0; width < this.boardWidth; width++) {
                String pieceCode = "";
                char char1 = positionCode.charAt(codeIndex);
                char char2 = positionCode.charAt(codeIndex + 1);
                pieceCode += String.valueOf(char1) + String.valueOf(char2);
                Piece tempPiece = PieceGenerator.generatePiece(pieceCode, height, width, this);
                chessBoard[height][width] = tempPiece;
                codeIndex += 2;
            }
        }
    }
}
