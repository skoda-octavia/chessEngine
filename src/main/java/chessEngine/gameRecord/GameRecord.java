package chessEngine.gameRecord;


import chessEngine.account.Account;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.chess.engineMove.EngineMoveCode;
import chessEngine.chess.engineMove.field.Field;
import chessEngine.currentGame.CurrentGame;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "game_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameRecord {
    @Id
    @SequenceGenerator(
            name = "game_record_sequence",
            sequenceName = "game_record_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "game_record_sequence"
    )
    private Long id;

    public static final int MOVE_LENGTH = 5;

    @Column(name = "finished", nullable = false)
    private boolean finished;

    @Column(nullable = false)
    private LocalDateTime startedAt;

    @Column()
    private LocalDateTime finishedAt;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;


    @Column(name = "game_code", columnDefinition = "TEXT")
    private String gameCode;

    public GameRecord(Account account) {
        this.account = account;
        this.finished = false;
        this.gameCode = "";
        this.startedAt = LocalDateTime.now();
    }

    public EngineMove getParentEngineMove() {
        if (gameCode.length() == 0) {return null;}
        try {
            String lastMoveCode = gameCode.substring(gameCode.length() - MOVE_LENGTH);
            byte fromY = (byte) Character.getNumericValue(lastMoveCode.charAt(0));
            byte fromX = (byte) Character.getNumericValue(lastMoveCode.charAt(1));
            byte toY = (byte) Character.getNumericValue(lastMoveCode.charAt(2));
            byte toX = (byte) Character.getNumericValue(lastMoveCode.charAt(3));
            int moveCodeValue = Character.getNumericValue(lastMoveCode.charAt(4));
            return new EngineMove(
                    new Field(fromY, fromX),
                    new Field(toY, toX),
                    EngineMoveCode.fromInt(moveCodeValue)
            );
        } catch (Exception e) {
            throw new IllegalStateException("Exception while decoding last move from code: " + gameCode);
        }
    }
}
