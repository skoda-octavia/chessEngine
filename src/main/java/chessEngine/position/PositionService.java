package chessEngine.position;
import chessEngine.chess.EnginePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PositionService {

    @Autowired
    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Optional<Position> getPositionByDetails(Position nextPosition) {
        return this.positionRepository.findByPositionAttributes(
                nextPosition.isWhiteKingMoved(),
                nextPosition.isBlackKingMoved(),
                nextPosition.isWhiteLeftRookMoved(),
                nextPosition.isWhiteRightRookMoved(),
                nextPosition.isBlackLeftRookMoved(),
                nextPosition.isBlackRightRookMoved(),
                nextPosition.getPositionCode(),
                nextPosition.isWhiteMoves()
        );
    }

    public void save(Position position) {this.positionRepository.save(position);}
    public List<Position> getPositions() {
        return positionRepository.findAll();
    }

    public Position getStartingPosition() {
        return positionRepository.findById(1L).orElseThrow();
    }
}
