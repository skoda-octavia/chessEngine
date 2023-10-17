package chessEngine.position;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PositionService {

    public List<Position> getPositions() {
        return List.of(
                new Position(
                        1L,
                        "5436543",
                        true
                )
        );
    }
}
