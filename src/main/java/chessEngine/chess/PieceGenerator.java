package chessEngine.chess;

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
    public static Piece generatePiece(String pieceCode, byte height, byte width) {
        if (pieceCode.length() != 2) {
            throw new IllegalArgumentException("illegal pieceCode length: " + pieceCode);
        }
        switch (pieceCode) {
            case "  " : return null;
            case "wP" : return new WhitePawn(height, width);
            case "wR" : return new WhiteRook(height, width);
            case "wB" : return new WhiteBishop(height, width);
            case "wk" : return new WhiteKnight(height, width);
            case "wK" : return new WhiteKing(height, width);
            case "wQ" : return new WhiteQueen(height, width);

            case "bP" : return new BlackPawn(height, width);
            case "bR" : return new BlackRook(height, width);
            case "bB" : return new BlackBishop(height, width);
            case "bk" : return new BlackKnight(height, width);
            case "bK" : return new BlackKing(height, width);
            case "bQ" : return new BlackQueen(height, width);

            default:
                String message = "Not supported piece code: " + pieceCode;
                message +=  ", on height, widhth: " + height + ", " + width;
                throw new IllegalArgumentException(message);
        }
    }
}
