import { NONE_TYPE } from "@angular/compiler";
import { BoardComponent } from "src/app/components/board/board.component";
import { Field } from "../../field/field";
import { Piece, PieceColor } from "../../pieces/piece";
import { Bishop } from "../../pieces/piece/infiniteRangePiece/bishop/bishop";
import { BlackBishop } from "../../pieces/piece/infiniteRangePiece/bishop/blackBishop/black-bishop";
import { WhiteBishop } from "../../pieces/piece/infiniteRangePiece/bishop/whiteBishop/white-bishop";
import { BlackQueen } from "../../pieces/piece/infiniteRangePiece/queen/blackQueen/black-queen";
import { Queen } from "../../pieces/piece/infiniteRangePiece/queen/queen";
import { WhiteQueen } from "../../pieces/piece/infiniteRangePiece/queen/whiteQueen/white-queen";
import { BlackRook } from "../../pieces/piece/infiniteRangePiece/rook/blackRook/black-rook";
import { Rook } from "../../pieces/piece/infiniteRangePiece/rook/rook";
import { WhiteRook } from "../../pieces/piece/infiniteRangePiece/rook/whiteRook/white-rook";
import { BlackKnight } from "../../pieces/piece/knight/blackKnight/black-knight";
import { Knight } from "../../pieces/piece/knight/knight";
import { WhiteKnight } from "../../pieces/piece/knight/whiteKnight/white-knight";
import { Pawn } from "../../pieces/piece/pawn/pawn";
import { Board } from "../board";
import { BoardGenerator } from "../board-generator";

export class pawnTransformationBoard {

    boardGenerator: BoardGenerator;
    boardComponent: BoardComponent;
    height: number;
    width: number;
    fields: Field[][] = [];
    pawn: any = null

    buttonClicked(height: number, width: number) {
        console.log("transformation field", height, width)
        var piece = this.fields[height][width].piece
        piece.fieldHeight = this.pawn.fieldHeight
        piece.fieldWidth = this.pawn.fieldWidth
        this.boardComponent.transformationVisible = false
        var pieceString = ""
        if (piece instanceof Queen) { pieceString = "queen" }
        if (piece instanceof Rook) { pieceString = "rook" }
        if (piece instanceof Knight) { pieceString = "knight" }
        if (piece instanceof Bishop) { pieceString = "bishop" }
        var moveCode = this.boardComponent.board.moveCodeDictionary[pieceString]
        this.boardComponent.board.pawnTransformed(this.pawn, piece, moveCode)
    }

    setTransBoard(color: PieceColor) {
        if (color == PieceColor.White) { this.setForWhite() }
        else {this.setForBlack()}
    }

    setForWhite() {
        this.fields[0][0].setPiece(new WhiteQueen(-1, -1, this.boardComponent.board))
        this.fields[0][1].setPiece(new WhiteRook(-1, -1, this.boardComponent.board))
        this.fields[0][2].setPiece(new WhiteBishop(-1, -1, this.boardComponent.board))
        this.fields[0][3].setPiece(new WhiteKnight(-1, -1, this.boardComponent.board))
    }
    
    setForBlack() {
        this.fields[0][0].setPiece(new BlackQueen(-1, -1, this.boardComponent.board))
        this.fields[0][1].setPiece(new BlackRook(-1, -1, this.boardComponent.board))
        this.fields[0][2].setPiece(new BlackBishop(-1, -1, this.boardComponent.board))
        this.fields[0][3].setPiece(new BlackKnight(-1, -1, this.boardComponent.board))
    }

    transformation(pawn: Pawn) {
        this.setTransBoard(pawn.color)
        this.boardComponent.transformationVisible = true
        this.pawn = pawn
    }

    transformEnginesPawn(transformedPawn: Pawn, toY: number, toX: number, moveCode: number) {
        var piece = this.generateEnginePiece(transformedPawn, toY, toX, moveCode)
        this.boardComponent.board.pawnTransformed(transformedPawn, piece, moveCode)
    }

    generateEnginePiece(transformedPawn: Pawn, toY: number, toX: number, moveCode: number) {
        if (transformedPawn.color == PieceColor.White) {
            switch (moveCode) {
                case 2:
                    return new WhiteKnight(toY, toX, this.boardComponent.board)
                case 3:
                    return new WhiteRook(toY, toX, this.boardComponent.board)
                case 4:
                    return new WhiteQueen(toY, toX, this.boardComponent.board)
                case 5:
                    return new WhiteBishop(toY, toX, this.boardComponent.board)
                default:
                    throw Error("incorrect move code")
            }
        }
        else if (transformedPawn.color == PieceColor.Black) {
            switch (moveCode) {
                case 2:
                    return new BlackKnight(toY, toX, this.boardComponent.board)
                case 3:
                    return new BlackRook(toY, toX, this.boardComponent.board)
                case 4:
                    return new BlackQueen(toY, toX, this.boardComponent.board)
                case 5:
                    return new BlackBishop(toY, toX, this.boardComponent.board)
                default:
                    throw Error("incorrect move code")
            }
        }
        throw Error("invorrect pawn color in transformation")
    }

    constructor(height: number, width: number, boardComponent: BoardComponent) {
        this.boardComponent = boardComponent;
        this.height = height;
        this.width = width;
        this.boardGenerator = new BoardGenerator()
        this.boardGenerator.setBaseBoard(this, true)
        this.setForBlack()
    }

}