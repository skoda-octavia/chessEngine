package chessEngine.position;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(
        name = "Position",
        uniqueConstraints = @UniqueConstraint(columnNames = {"positionCode", "whiteMoves"})
)
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

    public Position(Long id, String positionCode, boolean whiteMoves) {
        this.id = id;
        this.positionCode = positionCode;
        this.whiteMoves = whiteMoves;
    }

    public Position() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public boolean isWhiteMoves() {
        return whiteMoves;
    }

    public void setWhiteMoves(boolean whiteMoves) {
        this.whiteMoves = whiteMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return whiteMoves == position.whiteMoves && Objects.equals(id, position.id) && Objects.equals(positionCode, position.positionCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, positionCode, whiteMoves);
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", positionCode='" + positionCode + '\'' +
                ", whiteMoves=" + whiteMoves +
                '}';
    }
}
