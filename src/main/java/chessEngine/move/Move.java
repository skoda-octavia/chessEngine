package chessEngine.move;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Move")
@Data
public class Move {

    @EmbeddedId
    private MoveId moveId;
    @Column(length = 5, nullable = false, name = "move_code")
    private String moveCode;
}
