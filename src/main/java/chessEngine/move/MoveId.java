package chessEngine.move;


import chessEngine.position.Position;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class MoveId implements Serializable {

    @Column(name = "parent_id", nullable = false)
    private Long parentId;
    @Column(name = "child_id", nullable = false)
    private Long childId;
}
