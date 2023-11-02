package chessEngine.chess;


import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.EngineMoveCode;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.PieceColor;
import chessEngine.chess.piece.constantMovesPiece.king.BlackKing;
import chessEngine.chess.piece.constantMovesPiece.king.WhiteKing;
import chessEngine.chess.piece.infiniteRangePiece.rook.BlackRook;
import chessEngine.chess.piece.infiniteRangePiece.rook.WhiteRook;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@AllArgsConstructor
@Data
public class CastlingOperator {

    private final EnginePosition enginePosition;

    public ArrayList<EngineMove> possibleCastlingMoves(PieceColor color) {
        switch (color) {
            case WHITE : return this.possibleWhiteCastlingMoves();
            case BLACK : return this.possibleBlackCastlingMoves();

            default:
                String message = "Not supported pieceColor: " + color;
                throw new IllegalArgumentException(message);
        }

    }

    private ArrayList<EngineMove> possibleBlackCastlingMoves() {
        ArrayList<EngineMove> possibleBlackCastlingMoves =  new ArrayList<>();
        if (enginePosition.isBlackKingMoved()) {return possibleBlackCastlingMoves;}
        if (!(enginePosition.getChessBoard()[0][4] instanceof BlackKing)) {return possibleBlackCastlingMoves;}

        Field kingsField = enginePosition.getBlackKing().getField();
        if (this.possibleRightBlackCastling()) {possibleBlackCastlingMoves.add(
                new EngineMove(kingsField, new Field((byte)0, (byte)6), EngineMoveCode.CASTLING)
        );}
        if (this.possibleLeftBlackCastling()) {possibleBlackCastlingMoves.add(
                new EngineMove(kingsField, new Field((byte)0, (byte)2), EngineMoveCode.CASTLING)
        );}
        return possibleBlackCastlingMoves;
    }

    private boolean possibleLeftBlackCastling() {
        if (enginePosition.isBlackLeftRookMoved()) {return false;}
        if (!(enginePosition.getChessBoard()[0][0] instanceof BlackRook)) {return false;}

        if (
                enginePosition.occupiedField(new Field((byte)0, (byte)1)) ||
                enginePosition.occupiedField(new Field((byte)0, (byte)2)) ||
                enginePosition.occupiedField(new Field((byte)0, (byte)3))
        ) {return false;}
        if (
                enginePosition.controlledField(new Field((byte)0, (byte)4), PieceColor.WHITE) ||
                enginePosition.controlledField(new Field((byte)0, (byte)3), PieceColor.WHITE) ||
                enginePosition.controlledField(new Field((byte)0, (byte)2), PieceColor.WHITE)
        ) {return false;}
        return true;
    }

    private boolean possibleRightBlackCastling() {
        if (enginePosition.isBlackRightRookMoved()) {return false;}
        if (!(enginePosition.getChessBoard()[0][7] instanceof BlackRook)) {return false;}

        if (
                enginePosition.occupiedField(new Field((byte)0, (byte)5)) ||
                enginePosition.occupiedField(new Field((byte)0, (byte)6))
        ) {return false;}
        if (
                enginePosition.controlledField(new Field((byte)0, (byte)4), PieceColor.WHITE) ||
                enginePosition.controlledField(new Field((byte)0, (byte)5), PieceColor.WHITE) ||
                enginePosition.controlledField(new Field((byte)0, (byte)6), PieceColor.WHITE)
        ) {return false;}
        return true;
    }

    private ArrayList<EngineMove> possibleWhiteCastlingMoves() {
        ArrayList<EngineMove> possibleWhiteCastlingMoves =  new ArrayList<>();
        if (enginePosition.isWhiteKingMoved()) {return possibleWhiteCastlingMoves;}
        if (!(enginePosition.getChessBoard()[7][4] instanceof WhiteKing)) {return possibleWhiteCastlingMoves;}

        Field kingsField = enginePosition.getWhiteKing().getField();
        if (this.possibleRightWhiteCastling()) {possibleWhiteCastlingMoves.add(
                new EngineMove(kingsField, new Field((byte)7, (byte)6), EngineMoveCode.CASTLING)
        );}
        if (this.possibleLeftWhiteCastling()) {possibleWhiteCastlingMoves.add(
                new EngineMove(kingsField, new Field((byte)7, (byte)2), EngineMoveCode.CASTLING)
        );}
        return possibleWhiteCastlingMoves;
    }

    private boolean possibleLeftWhiteCastling() {
        if (enginePosition.isWhiteLeftRookMoved()) {return false;}
        if (!(enginePosition.getChessBoard()[7][0] instanceof WhiteRook)) {return false;}

        if (
                enginePosition.occupiedField(new Field((byte)7, (byte)1)) ||
                enginePosition.occupiedField(new Field((byte)7, (byte)2)) ||
                enginePosition.occupiedField(new Field((byte)7, (byte)3))
        ) {return false;}
        if (
                enginePosition.controlledField(new Field((byte)7, (byte)4), PieceColor.BLACK) ||
                enginePosition.controlledField(new Field((byte)7, (byte)3), PieceColor.BLACK) ||
                enginePosition.controlledField(new Field((byte)7, (byte)2), PieceColor.BLACK)
        ) {return false;}
        return true;
    }

    private boolean possibleRightWhiteCastling() {
        if (enginePosition.isWhiteRightRookMoved()) {return false;}
        if (!(enginePosition.getChessBoard()[7][7] instanceof WhiteRook)) {return false;}

        if (
                enginePosition.occupiedField(new Field((byte)7, (byte)5)) ||
                enginePosition.occupiedField(new Field((byte)7, (byte)6))
        ) {return false;}
        if (
                enginePosition.controlledField(new Field((byte)7, (byte)4), PieceColor.BLACK) ||
                enginePosition.controlledField(new Field((byte)7, (byte)5), PieceColor.BLACK) ||
                enginePosition.controlledField(new Field((byte)7, (byte)6), PieceColor.BLACK)
        ) {return false;}
        return true;
    }

}
