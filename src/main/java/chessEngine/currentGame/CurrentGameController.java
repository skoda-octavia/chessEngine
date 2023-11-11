package chessEngine.currentGame;

import chessEngine.move.MoveRequestResponse;
import chessEngine.security.JwtService;
import chessEngine.security.RegistrationToken;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        return ResponseEntity.ok("success");
    }

    @GetMapping("get-current-game")
    public ResponseEntity<CurrentGame> get(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String username = jwtService.extractUsernameFromHeader(authorizationHeader);
        CurrentGame currentGame = currentGameService.get(username);
        if(currentGame != null) {
            return ResponseEntity.ok(currentGameService.get(username));
        }
        else {return ResponseEntity.badRequest().body(null);}
    }

    @PostMapping("move")
    public ResponseEntity getNextMove(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestBody MoveRequestResponse moveRequestResponse
        ) {
        System.out.println("gfsdgfsdgfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffsdgs");
        String username = jwtService.extractUsernameFromHeader(authorizationHeader);
        currentGameService.makeMove(username, moveRequestResponse);
        return ResponseEntity.ok("jest ok");
    }
}