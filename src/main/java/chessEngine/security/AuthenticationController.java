package chessEngine.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticateService authenticateService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticateResponse> register(
            @RequestBody RegistrationRequest request
            ) {
        return ResponseEntity.ok(authenticateService.register(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticateResponse> authenticate(
            @RequestBody AuthenticateRequest authRequest
    ) { return ResponseEntity.ok(authenticateService.authenticate(authRequest));}

    @PostMapping(path="/confirm")
    public ResponseEntity<TokenConfirmationResponse> confirm(@RequestBody RegistrationToken token) {
        return ResponseEntity.ok(authenticateService.confirmToken(token.getToken()));
    }

}
