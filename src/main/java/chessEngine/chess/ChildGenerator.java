package chessEngine.chess;

import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.EngineMoveCode;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.PieceColor;

import java.util.ArrayList;

public class ChildGenerator {

    public static EnginePosition generateChild(EnginePosition parentPosition, EngineMove move) {

        switch (move.getEngineMoveCode()) {
            case ENPASSANT : return generateChildEnPassant(parentPosition, move);
            case CASTLING: return generateChildCastling(parentPosition, move);
            case NONE: return generateChildNone(parentPosition, move);
            default: return generateChildTransformation(parentPosition, move);
        }
    }

    private static EnginePosition generateChildNone(EnginePosition parentPosition, EngineMove move) {
        String newCode = "";
        Field from = move.getFrom();
        Field to = move.getTo();
        for (byte h = 0;h < parentPosition.getBoardHeight();h++) {
            for(byte w = 0; w < parentPosition.getBoardWidth();w++) {
                if (h== from.height() && w == from.width()) {newCode += "  ";}
                else if (h == to.height() && w == to.width()) {
                        Piece piece = parentPosition.getChessBoard()[from.height()][from.width()];
                        newCode += piece.getPieceCode();
                }
                else {
                    try {
                        Piece piece = parentPosition.getChessBoard()[h][w];
                        newCode += piece.getPieceCode();
                    }
                    catch (Exception e) {
                        newCode += "  ";
                    }
                }
            }
        }
        EnginePosition newEnginePosition =  new EnginePosition(
                newCode,
                !parentPosition.isWhiteMoves(),
                parentPosition.isWhiteKingMoved(),
                parentPosition.isBlackKingMoved(),
                parentPosition.isWhiteLeftRookMoved(),
                parentPosition.isWhiteRightRookMoved(),
                parentPosition.isBlackLeftRookMoved(),
                parentPosition.isBlackRightRookMoved(),
                move
        );

        if (from.height() == 0 && from.width() == 0) {newEnginePosition.setBlackLeftRookMoved(true);}
        else if (from.height() == 0 && from.width() == 7) {newEnginePosition.setBlackRightRookMoved(true);}
        else if (from.height() == 7 && from.width() == 0) {newEnginePosition.setWhiteLeftRookMoved(true);}
        else if (from.height() == 7 && from.width() == 7) {newEnginePosition.setWhiteRightRookMoved(true);}
        else if (from.height() == 0 && from.width() == 4) {newEnginePosition.setBlackKingMoved(true);}
        else if (from.height() == 7 && from.width() == 4) {newEnginePosition.setWhiteKingMoved(true);}
        return newEnginePosition;
    }

    public static EnginePosition generateChildEnPassant(EnginePosition parentPosition, EngineMove move) {
        String newCode = "";
        Field from = move.getFrom();
        Field to = move.getTo();
        Field enPassantField = new Field(move.getFrom().height(),move.getTo().width());
        for (byte h = 0;h < parentPosition.getBoardHeight();h++) {
            for(byte w = 0; w < parentPosition.getBoardWidth();w++) {
                if (h== from.height() && w == from.width()) {newCode += "  ";}
                else if (h == to.height() && w == to.width()) {newCode += parentPosition.isWhiteMoves() ? "wP" : "bP";}
                else if (h == enPassantField.height() && w == enPassantField.width()) {newCode += "  ";}
                else {
                    try {
                        Piece piece = parentPosition.getChessBoard()[h][w];
                        newCode += piece.getPieceCode();
                    }
                    catch (Exception e) {
                        newCode += "  ";
                    }
                }
            }
        }
        return new EnginePosition(
                newCode,
                !parentPosition.isWhiteMoves(),
                parentPosition.isWhiteKingMoved(),
                parentPosition.isBlackKingMoved(),
                parentPosition.isWhiteLeftRookMoved(),
                parentPosition.isWhiteRightRookMoved(),
                parentPosition.isBlackLeftRookMoved(),
                parentPosition.isBlackRightRookMoved(),
                move
        );
    }

        public static EnginePosition generateChildTransformation(EnginePosition parentPosition, EngineMove move) {
            String newCode = "";
            Field from = move.getFrom();
            Field to = move.getTo();
            for (byte h = 0;h < parentPosition.getBoardHeight();h++) {
                for(byte w = 0; w < parentPosition.getBoardWidth();w++) {
                    if (h== from.height() && w == from.width()) {newCode += "  ";}
                    else if (h == to.height() && w == to.width()) {
                        PieceColor currentColor = parentPosition.isWhiteMoves() ? PieceColor.WHITE : PieceColor.BLACK;
                        String newPieceCode = EngineMove.getNewPieceCode(move.getEngineMoveCode(), currentColor);
                        newCode += newPieceCode;
                    }
                    else {
                        try {
                            Piece piece = parentPosition.getChessBoard()[h][w];
                            newCode += piece.getPieceCode();
                        }
                        catch (Exception e) {
                            newCode += "  ";
                        }
                    }
                }
            }
            return new EnginePosition(
                    newCode,
                    !parentPosition.isWhiteMoves(),
                    parentPosition.isWhiteKingMoved(),
                    parentPosition.isBlackKingMoved(),
                    parentPosition.isWhiteLeftRookMoved(),
                    parentPosition.isWhiteRightRookMoved(),
                    parentPosition.isBlackLeftRookMoved(),
                    parentPosition.isBlackRightRookMoved(),
                    move
            );
        }


        public static EnginePosition generateChildCastling(EnginePosition parentPosition, EngineMove move) {
            String newCode = "";
            Field kingsOldField = move.getFrom();
            Field kingsNewField = move.getTo();
            String kingsCode = null;
            String rooksCode = null;
            Field rookOldField = null;
            Field rookNewField = null;
            if(kingsNewField.height() == 0 &&kingsNewField.width() == 2) {
                rookOldField = new Field((byte)0, (byte)0);
                rookNewField = new Field((byte)0, (byte)3);
                kingsCode = "bK"; rooksCode = "bR";
            }
            else if(kingsNewField.height() == 0 &&kingsNewField.width() == 6) {
                rookOldField = new Field((byte)0, (byte)7);
                rookNewField = new Field((byte)0, (byte)5);
                kingsCode = "bK"; rooksCode = "bR";
            }
            else if(kingsNewField.height() == 7 &&kingsNewField.width() == 2) {
                rookOldField = new Field((byte)7, (byte)0);
                rookNewField = new Field((byte)7, (byte)3);
                kingsCode = "wK"; rooksCode = "wR";
            }
            else  {
                rookOldField = new Field((byte)7, (byte)7);
                rookNewField = new Field((byte)7, (byte)5);
                kingsCode = "wK"; rooksCode = "wR";
            }
            for (byte h = 0;h < parentPosition.getBoardHeight();h++) {
                for(byte w = 0; w < parentPosition.getBoardWidth();w++) {
                    if (h== kingsOldField.height() && w == kingsOldField.width()) {newCode += "  ";}
                    else if (h== kingsNewField.height() && w == kingsNewField.width()) {newCode += kingsCode;}
                    else if (h== rookOldField.height() && w == rookOldField.width()) {newCode += "  ";}
                    else if (h== rookNewField.height() && w == rookNewField.width()) {newCode += rooksCode;}
                    else {
                        try {
                            Piece piece = parentPosition.getChessBoard()[h][w];
                            newCode += piece.getPieceCode();
                        }
                        catch (Exception e) {
                            newCode += "  ";
                        }
                    }
                }
            }
            EnginePosition newEnginePosition =  new EnginePosition(
                    newCode,
                    !parentPosition.isWhiteMoves(),
                    parentPosition.isWhiteKingMoved(),
                    parentPosition.isBlackKingMoved(),
                    parentPosition.isWhiteLeftRookMoved(),
                    parentPosition.isWhiteRightRookMoved(),
                    parentPosition.isBlackLeftRookMoved(),
                    parentPosition.isBlackRightRookMoved(),
                    move
            );
            if(kingsCode.equals("bK")) {newEnginePosition.setBlackKingMoved(true);}
            else {newEnginePosition.setWhiteKingMoved(true);}
            return newEnginePosition;
        }

}
