package chessEngine.gameRecord;

import chessEngine.chess.engineMove.EngineMove;
import chessEngine.move.Move;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GameRecordService {

    private final GameRecordRepository gameRecordRepository;

    public void save(GameRecord gameRecord) {
        gameRecordRepository.save(gameRecord);
    }

    public void updateGameRecord(Long id, String moveCode) {
        if (moveCode.length() != GameRecord.MOVE_LENGTH) {
            throw new IllegalArgumentException("Given move code has illegal length: " + moveCode);
        }
        else {
            gameRecordRepository.extendGameCodeById(id, moveCode);
        }
    }
}
