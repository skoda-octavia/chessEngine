package chessEngine.confirmationToken;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveCofirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByToken(token);
        return  confirmationToken;
    }

    public String generateConfirmationToken() {
        return UUID.randomUUID().toString();
    }

    public void setTokenConfirmed(String token) {
        confirmationTokenRepository.setTokenConfirmed(token, LocalDateTime.now());
    }
}
