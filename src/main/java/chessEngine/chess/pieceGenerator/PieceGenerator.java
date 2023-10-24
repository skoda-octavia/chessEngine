package chessEngine.chess.pieceGenerator;

import chessEngine.chess.EnginePosition;
import chessEngine.chess.move.field.Field;
import chessEngine.chess.piece.Piece;
import chessEngine.chess.piece.constantMovesPiece.king.BlackKing;
import chessEngine.chess.piece.constantMovesPiece.king.WhiteKing;
import chessEngine.chess.piece.constantMovesPiece.knight.BlackKnight;
import chessEngine.chess.piece.constantMovesPiece.knight.WhiteKnight;
import chessEngine.chess.piece.infiniteRangePiece.bishop.BlackBishop;
import chessEngine.chess.piece.infiniteRangePiece.bishop.WhiteBishop;
import chessEngine.chess.piece.infiniteRangePiece.queen.BlackQueen;
import chessEngine.chess.piece.infiniteRangePiece.queen.WhiteQueen;
import chessEngine.chess.piece.infiniteRangePiece.rook.BlackRook;
import chessEngine.chess.piece.infiniteRangePiece.rook.WhiteRook;
import chessEngine.chess.piece.pawn.BlackPawn;
import chessEngine.chess.piece.pawn.WhitePawn;

public class PieceGenerator {
    public static Piece generatePiece(String pieceCode, byte height, byte width, EnginePosition pos) {
        if (pieceCode.length() != 2) {
            throw new IllegalArgumentException("illegal pieceCode length: " + pieceCode);
        }
        switch (pieceCode) {
            case "  " : return null;
            case "wP" : return new WhitePawn(new Field(height, width), pos);
            case "wR" : return new WhiteRook(new Field(height, width), pos);
            case "wB" : return new WhiteBishop(new Field(height, width), pos);
            case "wk" : return new WhiteKnight(new Field(height, width), pos);
            case "wK" : return new WhiteKing(new Field(height, width), pos);
            case "wQ" : return new WhiteQueen(new Field(height, width), pos);

            case "bP" : return new BlackPawn(new Field(height, width), pos);
            case "bR" : return new BlackRook(new Field(height, width), pos);
            case "bB" : return new BlackBishop(new Field(height, width), pos);
            case "bk" : return new BlackKnight(new Field(height, width), pos);
            case "bK" : return new BlackKing(new Field(height, width), pos);
            case "bQ" : return new BlackQueen(new Field(height, width), pos);

            default:
                String message = "Not supported piece code: " + pieceCode;
                message +=  ", on height, widhth: " + height + ", " + width;
                throw new IllegalArgumentException(message);
        }
    }
}
