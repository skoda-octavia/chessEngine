package chessEngine.position;

import chessEngine.position.positionID.PositionID;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Position {

    @EmbeddedId
    private PositionID positionID;
    @Column(length = 128)
    private String positionCode;

    public Position(PositionID positionID, String positionCode) {
        this.positionID = positionID;
        this.positionCode = positionCode;
    }

    public PositionID getPositionID() {
        return positionID;
    }

    public void setPositionID(PositionID positionID) {
        this.positionID = positionID;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }


}
