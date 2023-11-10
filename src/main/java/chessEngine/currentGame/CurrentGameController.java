package chessEngine.currentGame;

import chessEngine.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/current-game/")
public class CurrentGameController {

    private final CurrentGameService currentGameService;
    private final JwtService jwtService;

    @GetMapping("get")
    public ResponseEntity<CurrentGame> get(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            String username = jwtService.extractUsername(jwtToken);
            return ResponseEntity.ok(currentGameService.get(username));
        } else {
            return ResponseEntity.ok(null);
        }
    }
}