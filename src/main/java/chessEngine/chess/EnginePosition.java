package chessEngine.chess;

import chessEngine.chess.move.EngineMove;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.constantMovesPiece.king.BlackKing;
import chessEngine.chess.piece.constantMovesPiece.king.King;
import chessEngine.chess.piece.constantMovesPiece.king.WhiteKing;
import chessEngine.chess.piece.infiniteRangePiece.queen.BlackQueen;
import chessEngine.chess.piece.infiniteRangePiece.queen.WhiteQueen;
import chessEngine.chess.pieceGenerator.PieceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

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
    private WhiteQueen whiteQueen = null;
    private BlackQueen blackQueen = null;
    private boolean whiteKingMoved;
    private boolean blackKingMoved;
    private boolean whiteLeftRookMoved;
    private boolean whiteRightRookMoved;
    private boolean blackLeftRookMoved;
    private boolean blackRightRookMoved;

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
//
//    public boolean unveilingMove(EngineMove engineMove) {
//        boolean answer = false;
//        Field from = engineMove.getFrom();
//        Field to = engineMove.getTo();
//        PieceColor[][] tempColorMap = getColorMap();
//        PieceColor oldColor = tempColorMap[to.height()][to.width()];
//        tempColorMap[to.height()][to.width()] = tempColorMap[from.height()][from.width()];
//        tempColorMap[from.height()][from.width()] = PieceColor.NONE;
//
//
//
//        Field kingsField = null;
//        PieceColor movingColor = whiteMoves ? PieceColor.WHITE : PieceColor.BLACK;
//        if (chessBoard[from.height()][from.width()] instanceof King &&
//                tempColorMap[to.height()][to.width()].equals(movingColor)) {
//                kingsField = new Field(to.height(), to.width());
//        }
//        else {kingsField = whiteMoves ? whiteKing.getField() : blackKing.getField();}
//
//        if (inCheck(movingColor, tempColorMap, kingsField)) {answer = true;}
//
//        tempColorMap[from.height()][from.width()] = tempColorMap[to.height()][to.width()];
//        tempColorMap[to.height()][to.width()] = oldColor;
//
//        return answer;
//    }
//
    public ArrayList<Piece> checkingPieces(PieceColor pieceColor) {
        LinkedList<Piece> oppositePieces = pieceColor.equals(PieceColor.WHITE) ? this.whitePieces : this.blackPieces;
        Field kingsField = pieceColor.equals(PieceColor.WHITE) ? this.blackKing.getField() : this.whiteKing.getField();
        ArrayList<Piece> checkingPieces = new ArrayList<>();
        Iterator<Piece> iterator = oppositePieces.iterator();
        while (iterator.hasNext()) {
            Piece tempPiece = iterator.next();
            Field tempPieceField = tempPiece.getField();
            HashSet<Field> controlledFields = tempPiece.getControlledFields();
            if (controlledFields.contains(kingsField)) {checkingPieces.add(tempPiece);}
        }
        return checkingPieces;
    }

    public Field kingsField(PieceColor pieceColor) {
        if (pieceColor.equals(PieceColor.WHITE)) {return this.whiteKing.getField();}
        else if (pieceColor.equals(PieceColor.BLACK)) {return this.blackKing.getField();}
        else {throw new IllegalArgumentException("pieceColor is none");}
    }

    public Field queensField(PieceColor pieceColor) {
        if (pieceColor.equals(PieceColor.WHITE)) {return this.whiteQueen.getField();}
        else if (pieceColor.equals(PieceColor.BLACK)) {return this.blackQueen.getField();}
        else {throw new IllegalArgumentException("pieceColor is none");}
    }


    public void setPiece(Piece tempPiece) {
        if (tempPiece.getPieceColor().equals(PieceColor.WHITE)) {
            this.whitePieces.push(tempPiece);
            if (tempPiece instanceof WhiteKing) {this.whiteKing = (WhiteKing) tempPiece;}
            else if (tempPiece instanceof WhiteQueen) {this.whiteQueen = (WhiteQueen) tempPiece;}
        }
        else {
            this.blackPieces.push(tempPiece);
            if (tempPiece instanceof BlackKing) {this.blackKing = (BlackKing) tempPiece;}
            else if (tempPiece instanceof BlackQueen) {this.blackQueen = (BlackQueen) tempPiece;}
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
        PieceColor[][] colorMap = generateColorMap();
        for (Piece piece : blackPieces) {piece.setMyPossibilities(colorMap);}
        for (Piece piece : whitePieces) {piece.setMyPossibilities(colorMap);}
    }

    public EnginePosition(String positionCode,
                          boolean whiteMoves,
                          boolean whiteKingMoved,
                          boolean blackKingMoved,
                          boolean whiteLeftRookMoved,
                          boolean whiteRightRookMoved,
                          boolean blackLeftRookMoved,
                          boolean blackRightRookMoved) {
        this.positionCode = positionCode;
        this.whiteMoves = whiteMoves;
        this.whiteKingMoved = whiteKingMoved;
        this.blackKingMoved = blackKingMoved;
        this.whiteLeftRookMoved = whiteLeftRookMoved;
        this.whiteRightRookMoved = whiteRightRookMoved;
        this.blackLeftRookMoved = blackLeftRookMoved;
        this.blackRightRookMoved = blackRightRookMoved;
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
