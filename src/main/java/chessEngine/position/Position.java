package chessEngine.position;
import chessEngine.chess.EnginePosition;
import chessEngine.move.Move;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(
        name = "Position",
        uniqueConstraints = @UniqueConstraint(columnNames = {"position_code", "white_moves"})
)

@Data
@NoArgsConstructor
public class Position {

    @Id
    @SequenceGenerator(
            name = "position_sequence",
            sequenceName = "position_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "position_sequence"
    )
    private Long id;
    @Column(length = 128, nullable = false, name = "position_code")
    private String positionCode;
    @Column(nullable = false, name = "white_moves")
    private boolean whiteMoves;

    @OneToMany
    @JoinColumn(name = "parent_id")
    private List<Move> childMoves;

    @Transient
    private EnginePosition enginePosition;

    public Position(Long id, String positionCode, boolean whiteMoves) {
        this.id = id;
        this.positionCode = positionCode;
        this.whiteMoves = whiteMoves;
    }

    public Position(String positionCode, boolean whiteMoves) {
        this.positionCode = positionCode;
        this.whiteMoves = whiteMoves;
    }
}
