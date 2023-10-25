package chessEngine.game;


import chessEngine.account.Account;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Game")
@Data
public class Game {
    @Id
    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "game_sequence"
    )
    private Long id;

    @Column(name = "finished")
    private boolean finished;


    @ManyToOne
    @JoinColumn(name = "account_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;


    @Column(name = "game_code", columnDefinition = "TEXT")
    private String gameCode;
}
