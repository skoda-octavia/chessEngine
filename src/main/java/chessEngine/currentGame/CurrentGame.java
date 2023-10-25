package chessEngine.currentGame;


import chessEngine.account.Account;
import chessEngine.gameRecord.GameRecord;
import chessEngine.position.Position;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "current_game")
@Data
public class CurrentGame {

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

    @OneToOne(optional = false)
    @JoinColumn(name = "account_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;

    @OneToOne(optional = false)
    @JoinColumn(name = "game_record_id")
    private GameRecord gameRecord;


    @OneToOne(optional = false)
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
