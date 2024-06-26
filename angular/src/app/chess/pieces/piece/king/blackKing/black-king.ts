import { Board } from "src/app/chess/board/board";
import { Field } from "src/app/chess/field/field";
import { PieceColor } from "../../../piece";
import { King } from "../king";

export class BlackKing extends King {

    iconPath: string = "assets/black_king.svg"
    color: PieceColor = PieceColor.Black;

    override moveTo(height: number, width: number) {
        var castlingMove = false
        if (this.fieldHeight == 0 && this.fieldWidth == 4 && height == 0) {
            if (width == 6) {
                this.moveRook(0, 7, 0, 5)
                castlingMove = true
            }
            else if (width == 2) {
                this.moveRook(0, 0, 0, 3)
                castlingMove = true
            }
        }
        
        this.fieldHeight = height;
        this.fieldWidth = width;

        if (!this.alreadyMoved) {
            this.alreadyMoved = true;
        }
        return castlingMove
    }


    constructor(fieldHeight: number, fieldWidth: number, board: Board) {
        super(fieldHeight, fieldWidth, board)
    }

}
