package chessEngine.chess;

import chessEngine.chess.move.Move;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.constantMovesPiece.king.BlackKing;
import chessEngine.chess.piece.constantMovesPiece.king.WhiteKing;
import chessEngine.chess.pieceGenerator.PieceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

@Getter
@Setter
public class EnginePosition {
    private final byte boardHeight = 8;
    private final byte boardWidth = 8;
    private EnginePosition parentPosition = null;
    private int value = 0;
    private String positionCode = "";
    private boolean whiteMoves;
    private Piece[][] chessBoard = new Piece[this.boardHeight][this.boardWidth];
    private PieceColor[][] colorMap = null;
    private LinkedList<Piece> blackPieces = new LinkedList<>();
    private LinkedList<Piece> whitePieces = new LinkedList<>();
    private WhiteKing whiteKing = null;
    private BlackKing blackKing = null;

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

    public boolean revealingMove(Move move) {
        Field from = move.getFrom();
        Field to = move.getTo();
        PieceColor[][] tempColorMap = getColorMap();
        PieceColor capturedColor = tempColorMap[to.height()][to.width()];
        tempColorMap[from.height()][from.width()] = PieceColor.NONE;
        tempColorMap[to.height()][to.width()] = getChessBoard()[from.height()][from.width()].getPieceColor();

        LinkedList<Piece> oppositePieces = !whiteMoves ? this.whitePieces : this.blackPieces;
        Iterator<Piece> iterator = oppositePieces.iterator();
        while (iterator.hasNext()) {
            Piece piece = iterator.next();
        }



        return false;
    }

    private void setPiece(Piece tempPiece) {
        if (tempPiece.getPieceColor().equals(PieceColor.WHITE)) {
            this.whitePieces.push(tempPiece);
            if (tempPiece instanceof WhiteKing) {this.whiteKing = (WhiteKing) tempPiece;}
        }
        else {
            this.blackPieces.push(tempPiece);
            if (tempPiece instanceof BlackKing) {this.blackKing = (BlackKing) tempPiece;}
        }
    }

    public void set() {
        int codeIndex = 0;
        for (byte height = 0; height < this.boardHeight; height++) {
            for (byte width = 0; width < this.boardWidth; width++) {
                String pieceCode = "";
                char char1 = positionCode.charAt(codeIndex);
                char char2 = positionCode.charAt(codeIndex + 1);
                pieceCode += String.valueOf(char1) + String.valueOf(char2);
                Piece tempPiece = PieceGenerator.generatePiece(pieceCode, height, width, this);
                if (tempPiece != null) {
                    this.setPiece(tempPiece);
                }
                chessBoard[height][width] = tempPiece;
                codeIndex += 2;
            }
        }
    }
    public EnginePosition(String positionCode, boolean whiteMoves) {
        if (positionCode.length() != this.boardHeight * this.boardWidth * 2) {
            String message = "Incorrect len of positionCode: " + positionCode.length();
            throw new IllegalArgumentException(message);
        }
        this.positionCode = positionCode;
        this.whiteMoves = whiteMoves;
    }
}
