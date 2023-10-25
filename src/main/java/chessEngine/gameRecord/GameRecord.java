package chessEngine.game;


import chessEngine.account.Account;
import chessEngine.currentGame.CurrentGame;
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

    @Column(name = "finished", nullable = false)
    private boolean finished;


    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;


    @Column(name = "game_code", columnDefinition = "TEXT")
    private String gameCode;

    @OneToOne(mappedBy = "game", optional = true)
    private CurrentGame currentGame;

}
