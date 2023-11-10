package chessEngine.currentGame;


import chessEngine.account.Account;
import chessEngine.gameRecord.GameRecord;
import chessEngine.position.Position;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "current_game")
@Data
@NoArgsConstructor
public class CurrentGame {

    public CurrentGame(Account account, GameRecord gameRecord, Position position) {
        this.account = account;
        this.gameRecord = gameRecord;
        this.position = position;
    }

    @Id
    @SequenceGenerator(
            name = "current_game_sequence",
            sequenceName = "current_game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "current_game_sequence"
    )
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "game_record_id")
    private GameRecord gameRecord;


    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Position position;

    @Column(nullable = false, columnDefinition = "boolean DEFAULT 'false'")
    private boolean whiteKingMoved;

    @Column(nullable = false, columnDefinition = "boolean DEFAULT 'false'")
    private boolean blackKingMoved;

    @Column(nullable = false, columnDefinition = "boolean DEFAULT 'false'")
    private boolean whiteLeftRookMoved;

    @Column(nullable = false, columnDefinition = "boolean DEFAULT 'false'")
    private boolean whiteRightRookMoved;

    @Column(nullable = false, columnDefinition = "boolean DEFAULT 'false'")
    private boolean blackLeftRookMoved;

    @Column(nullable = false, columnDefinition = "boolean DEFAULT 'false'")
    private boolean blackRightRookMoved;





}
