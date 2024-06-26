import { Field } from "../field/field";
import { Board } from "./board";
import { BlackBishop } from "../pieces/piece/infiniteRangePiece/bishop/blackBishop/black-bishop";
import { WhiteBishop } from "../pieces/piece/infiniteRangePiece/bishop/whiteBishop/white-bishop";
import { BlackQueen } from "../pieces/piece/infiniteRangePiece/queen/blackQueen/black-queen";
import { WhiteQueen } from "../pieces/piece/infiniteRangePiece/queen/whiteQueen/white-queen";
import { BlackRook } from "../pieces/piece/infiniteRangePiece/rook/blackRook/black-rook";
import { WhiteRook } from "../pieces/piece/infiniteRangePiece/rook/whiteRook/white-rook";
import { BlackKing } from "../pieces/piece/king/blackKing/black-king";
import { WhiteKing } from "../pieces/piece/king/whiteKing/white-king";

import { BlackKnight } from "../pieces/piece/knight/blackKnight/black-knight";
import { WhiteKnight } from "../pieces/piece/knight/whiteKnight/white-knight";
import { WhitePawn } from "../pieces/piece/pawn/whitePawn/white-pawn";
import { BlackPawn } from "../pieces/piece/pawn/blackPawn/black-pawn";
import { Piece, PieceColor } from "../pieces/piece";
import { pawnTransformationBoard } from "./pawnTransBoard/PawnTransformationBoard";

export class BoardGenerator {

    setBaseBoard(board: any, nextSquareWhite: boolean) : void {
        for (let i = 0; i < board.width; i++) {
            board.fields.push([]);
            for (let j = 0; j < board.height; j++) {
                var field = new Field(nextSquareWhite, i, j);
                field.board = board;
                board.fields[i].push(field);
                nextSquareWhite = !nextSquareWhite;
            }
            if (board.width % 2 === 0) {
                nextSquareWhite = !nextSquareWhite;
            }
        }
    }


    generatePawns(color: PieceColor, row: number, board: any) {
        for (let i = 0; i < board.width; i++) {
            if (color == PieceColor.Black) { var pawn = new BlackPawn(row, i, board) }
            else { var pawn = new WhitePawn(row, i, board) }
            board.fields[row][i].setPiece(pawn);
            board.pawns.add(pawn)
        }
    }

    setTransformationBoard(color: PieceColor) {
    }


    pieceGenerator(pieceCode: String, height: number, width: number, board: Board) {
        switch (pieceCode) {
            case "wK":
                return new WhiteKing(height, width, board)
            case "wQ":
                return new WhiteQueen(height, width, board)
            case "wR":
                return new WhiteRook(height, width, board)
            case "wB":
                return new WhiteBishop(height, width, board)
            case "wk":
                return new WhiteKnight(height, width, board)
            case "wP":
                return new WhitePawn(height, width, board)
            case "bK":
                return new BlackKing(height, width, board)
            case "bQ":
                return new BlackQueen(height, width, board)
            case "bR":
                return new BlackRook(height, width, board)
            case "bB":
                return new BlackBishop(height, width, board)
            case "bk":
                return new BlackKnight(height, width, board)
            case "bP":
                return new BlackPawn(height, width, board)
            case "  ":
                return null;
            default:
                throw new Error("illegal piece code: " + pieceCode)
        
        }
    }


    setBasePosition(board: any) {
        board.fields[0][0].setPiece(new BlackRook(0, 0, board));
        board.fields[0][1].setPiece(new BlackKnight(0, 1, board));
        board.fields[0][2].setPiece(new BlackBishop(0, 2, board));
        board.fields[0][3].setPiece(new BlackQueen(0, 3, board));
        board.fields[0][4].setPiece(new BlackKing(0, 4, board));

        board.fields[0][5].setPiece(new BlackBishop(0, 5, board));
        board.fields[0][6].setPiece(new BlackKnight(0, 6, board));
        board.fields[0][7].setPiece(new BlackRook(0, 7, board));

        this.generatePawns(PieceColor.Black, 1, board)

        this.generatePawns(PieceColor.White, 6, board)

        board.fields[7][0].setPiece(new WhiteRook(7, 0, board));
        board.fields[7][1].setPiece(new WhiteKnight(7, 1, board));
        board.fields[7][2].setPiece(new WhiteBishop(7, 2, board));
        board.fields[7][3].setPiece(new WhiteQueen(7, 3, board));
        board.fields[7][4].setPiece(new WhiteKing(7, 4, board));

        board.fields[7][5].setPiece(new WhiteBishop(7, 5, board));
        board.fields[7][6].setPiece(new WhiteKnight(7, 6, board));
        board.fields[7][7].setPiece(new WhiteRook(7, 7, board));
    }

    setPiecesFromCode(posCode: String, board: Board) {
        var i = 0;
        for (let h = 0; h < board.height; h++) {
            for (let w = 0; w < board.width; w++) {
                var pieceCode = posCode.substring(i, i + 2)
                var tempPiece = this.pieceGenerator(pieceCode, h, w, board)
                if (tempPiece != null) {
                    board.fields[h][w].setPiece(tempPiece)
                }
                i+=2
            }
        }
    }

    setDefaultBoard(board: Board) {
        this.setBaseBoard(board, true);
        this.setBasePosition(board);
    }


    setBoardFromCode(positionCode: String, board: Board) {
        this.setBaseBoard(board, true);
        this.setPiecesFromCode(positionCode, board)
    }
}