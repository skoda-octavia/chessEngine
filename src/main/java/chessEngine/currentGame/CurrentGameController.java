package chessEngine.currentGame;

import chessEngine.move.MoveRequestResponse;
import chessEngine.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/current-game/")
public class CurrentGameController {

    private final CurrentGameService currentGameService;
    private final JwtService jwtService;

    @PostMapping("create-new-game")
    public ResponseEntity create(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String username = jwtService.extractUsernameFromHeader(authorizationHeader);
        currentGameService.createFor(username);
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("get-current-game")
    public ResponseEntity<CurrentGameResponse> getCurrentGame(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String username = jwtService.extractUsernameFromHeader(authorizationHeader);
        CurrentGameResponse currentGameResponse = currentGameService.getCurrentGameFor(username);
        return ResponseEntity.ok().body(currentGameResponse);
    }

    @PostMapping("move")
    public ResponseEntity<MoveRequestResponse> getNextMove(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestBody MoveRequestResponse moveRequestResponse
        ) {
        String username = jwtService.extractUsernameFromHeader(authorizationHeader);
        System.out.println("\n");
        System.out.println(username + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        System.out.println("\n");
        MoveRequestResponse myMove = currentGameService.returnMove(username, moveRequestResponse);
        return ResponseEntity.ok().body(myMove);
    }
}