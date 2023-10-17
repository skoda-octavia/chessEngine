package chessEngine.position.positionID;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PositionID implements Serializable {

    private Long position_id;
    private boolean white_moves;

    public PositionID(Long position_id, boolean white_moves) {
        this.position_id = position_id;
        this.white_moves = white_moves;
    }
    public PositionID() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PositionID that)) return false;
        return white_moves == that.white_moves && Objects.equals(position_id, that.position_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position_id, white_moves);
    }

    public Long getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Long position_id) {
        this.position_id = position_id;
    }

    public boolean isWhite_moves() {
        return white_moves;
    }

    public void setWhite_moves(boolean white_moves) {
        this.white_moves = white_moves;
    }


}
