package chessEngine.confirmationToken;

import chessEngine.account.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {
    @Id
    @SequenceGenerator(
            name = "token_sequence",
            sequenceName = "token_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "token_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String token;
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    @ManyToOne
    @JoinColumn(nullable = false, name = "account_id")
    private Account account;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, Account account) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiredAt;
        this.account = account;
    }

    private LocalDateTime confirmedAt;
}
