import { Component, OnInit } from '@angular/core';
import { TokenService } from 'src/app/services/tokenService/token-service.service';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-token-confirmation',
  templateUrl: './token-confirmation.component.html',
  styleUrls: ['./token-confirmation.component.css']
})
export class TokenConfirmationComponent implements OnInit {

  message: String = "";
  header: String = "";

  constructor(private route: ActivatedRoute, private tokenService: TokenService, private router: Router) {}
  
  
  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      const token = params['token'];
      if (token) {
        this.tokenService.confirmToken(token).subscribe(
      (response: any) => {
            if (response.status === 0) {
          this.header = "Congratulations!"
          this.message = "Your account has been activated! Enjoy the game!"
        }
            else {
              this.header = "Incorrect token :("
              this.message = response.message
        }
      
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );;
      }
    });
  }


  logPage() {
    this.router.navigate(['/login']);
  }

}





