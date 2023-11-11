package chessEngine.gameRecord;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GameRecordService {

    private final GameRecordRepository gameRecordRepository;

    public void save(GameRecord gameRecord) {
        gameRecordRepository.save(gameRecord);
    }
}
