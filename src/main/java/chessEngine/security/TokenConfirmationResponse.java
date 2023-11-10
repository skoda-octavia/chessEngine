package chessEngine.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenConfirmationResponse {
    private int status;
    private String message;

    public TokenConfirmationResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
