package chessEngine.position;
import chessEngine.chess.EnginePosition;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(
        name = "Position",
        uniqueConstraints = @UniqueConstraint(columnNames = {"positionCode", "whiteMoves"})
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
    @Column(length = 128)
    private String positionCode;
    private boolean whiteMoves;

    @Transient
    private EnginePosition enginePosition;

    public Position(Long id, String positionCode, boolean whiteMoves) {
        this.id = id;
        this.positionCode = positionCode;
        this.whiteMoves = whiteMoves;
    }

}
