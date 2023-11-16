import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Piece, PieceColor } from 'src/app/chess/pieces/piece';
import { Move } from 'src/app/interfaces/move';
import { CurrentGameService } from 'src/app/services/curentGame/current-game.service';
import { Board } from '../../chess/board/board';
import { pawnTransformationBoard } from '../../chess/board/pawnTransBoard/PawnTransformationBoard';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})

export class BoardComponent implements OnInit {
  board: any = null;
  pawnTransformationBoard: any = null;
  transformationVisible = false;

  constructor(private currentGameService: CurrentGameService) {    
  }


  createNewGame() {
    this.board = new Board(8, 8, this, "", PieceColor.None);
    this.currentGameService.createNewGame().subscribe(
       (response: any) => {
          console.log(response)
      },
      (error: HttpErrorResponse) => {
        console.log(error)
      }
    )
  }

  sendMove(fromY: number, fromX: number, toY: number, toX: number, moveCode: number) {
    const move: Move = {
      fromY: fromY,
      fromX: fromX,
      toY: toY,
      toX: toX,
      moveCode: moveCode
    }
    this.currentGameService.move(move).subscribe(
      (response: any) => {
        console.log(response)
        if (response.moveCode == -1) {
          console.log("Game finished!")
          this.board.movingColor = PieceColor.None
        }
        else {
          this.board.firstButtonClicked(response.fromY, response.fromX)
          this.board.secondButtonClicked(response.toY, response.toX)
          // todo transformation
        }
      },
      (error: HttpErrorResponse) => {
        console.log("error while sending move");
      }
    );;
  }


  ngOnInit(): void {
    this.currentGameService.getCurrentGame().subscribe(
    (response: any) => {
    if(response.status == 1) {
      console.log("player has no current game");
      this.pawnTransformationBoard = new pawnTransformationBoard(4, 1, this)
      this.createNewGame()
      
    }  
    else {
      var movingColor = PieceColor.Black
      if (response.position.whiteMoves) {movingColor = PieceColor.White}
      this.board = new Board(8, 8, this, response.positionCode, movingColor);
      this.pawnTransformationBoard = new pawnTransformationBoard(4, 1, this)
    }
    },
    (error: HttpErrorResponse) => {
      console.log(error)
    }
  );;
  }

}

