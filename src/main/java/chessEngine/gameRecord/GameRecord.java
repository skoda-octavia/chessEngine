package chessEngine.gameRecord;


import chessEngine.account.Account;
import chessEngine.currentGame.CurrentGame;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "game_record")
@Data
public class GameRecord {
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

    @OneToOne(optional = true)
    @JoinColumn(name = "current_game_id")
    private CurrentGame currentGame;

}
