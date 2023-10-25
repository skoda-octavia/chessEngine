package chessEngine.move;

import chessEngine.position.Position;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Move")
@Data
public class Move {

    @EmbeddedId
    private MoveId moveId;
    @Column(length = 5, nullable = false, name = "move_code")
    private String moveCode;

    @MapsId("child_id")
    @ManyToOne
    @JoinColumn(name = "child_id", referencedColumnName = "id")
    private Position childPosition;

}
