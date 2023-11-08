package chessEngine.registration;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationResponse {
    private int status;
    private String message;

    public RegistrationResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
