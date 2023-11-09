package chessEngine.registration;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Builder
public class RegistrationRequest {
    private final String username;
    private final String email;
    private final String password;
}
