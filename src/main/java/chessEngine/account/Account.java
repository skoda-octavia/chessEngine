package chessEngine.account;

import chessEngine.currentGame.CurrentGame;
import chessEngine.gameRecord.GameRecord;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    private Long id;

    @Column(name = "login", length = 50, unique = true, nullable = false)
    private String login;

    @Column(name = "email", length = 50, unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(mappedBy = "account", optional = true)
    @PrimaryKeyJoinColumn(name = "current_game_id")
    private CurrentGame currentGame;

    @OneToMany(mappedBy = "account")
    private List<GameRecord> gameRecordList;


    public Account(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }
}
