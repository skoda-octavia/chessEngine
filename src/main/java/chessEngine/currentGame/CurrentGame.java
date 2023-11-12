package chessEngine.currentGame;


import chessEngine.account.Account;
import chessEngine.gameRecord.GameRecord;
import chessEngine.position.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "current_game")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class CurrentGame {

    @JsonIgnore
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

    @JsonIgnore
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @JsonIgnore
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "game_record_id", referencedColumnName = "id")
    private GameRecord gameRecord;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", unique = false)
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

    @Transient
    private byte status = 0;

    public CurrentGame(Account account, GameRecord gameRecord, Position position) {
        this.account = account;
        this.gameRecord = gameRecord;
        this.position = position;
    }

    public CurrentGame(byte status) {
        this.status = status;
    }
}
