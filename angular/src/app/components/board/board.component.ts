import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { PieceColor } from 'src/app/chess/pieces/piece';
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

  ngOnInit(): void {
      this.currentGameService.getCurrentGame().subscribe(
      (response: any) => {
        if (response.position != null) {
          var movingColor = PieceColor.Black
          if (response.position.whiteMoves) {movingColor = PieceColor.White}
          this.board = new Board(8, 8, this, response.position.positionCode, movingColor);
        }
        else {
          this.board = new Board(8, 8, this, "", PieceColor.None);
        }
        this.pawnTransformationBoard = new pawnTransformationBoard(4, 1, this)
      
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );;
  }

}

