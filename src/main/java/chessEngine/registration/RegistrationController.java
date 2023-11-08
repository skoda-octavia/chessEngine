package chessEngine.registration;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest request) throws MessagingException {
        if (!this.registrationService.validEmail(request.getEmail())) {
            RegistrationResponse response = new RegistrationResponse(1, "Email invalid or already taken");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        if (registrationService.loginTaken(request.getUsername())) {
            RegistrationResponse response = new RegistrationResponse(1, "Login already taken");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        registrationService.register(request);
        RegistrationResponse response = new RegistrationResponse(0, "Successful registration");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path="confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
