import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { PieceColor } from 'src/app/chess/pieces/piece';
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

  sendExampleMove() {
    const move: Move = {
      fromY: 6,
      fromX: 3,
      toY: 5,
      toX: 3,
      moveCode: 0
    }
    this.currentGameService.move(move).subscribe(
      (response: any) => {
        console.log(response)
      },
      (error: HttpErrorResponse) => {
        console.log("error while sending move");
      }
    );;
  }



  ngOnInit(): void {
      this.currentGameService.getCurrentGame().subscribe(
      (response: any) => {
        var movingColor = PieceColor.Black
        if (response.position.whiteMoves) {movingColor = PieceColor.White}
        this.board = new Board(8, 8, this, response.position.positionCode, movingColor);
        this.sendExampleMove()  
      
      },
      (error: HttpErrorResponse) => {
        console.log("player has no current game");
        this.createNewGame()
        this.sendExampleMove()
      }
    );;
    this.pawnTransformationBoard = new pawnTransformationBoard(4, 1, this)
    
  }

}

