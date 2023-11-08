package chessEngine.registration;

import lombok.Data;

@Data
public class RegistrationResponse {
    private int status;
    private String message;

    public RegistrationResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
