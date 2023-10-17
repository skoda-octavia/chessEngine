package chessEngine.position;

import chessEngine.position.positionID.PositionID;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PositionService {

    public List<Position> getPositions() {
        return List.of(
                new Position(
                        new PositionID(1L, true),
                        "5436543"
                )
        );
    }
}
