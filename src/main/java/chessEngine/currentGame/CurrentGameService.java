package chessEngine.currentGame;

import chessEngine.account.Account;
import chessEngine.account.AccountService;
import chessEngine.gameRecord.GameRecordService;
import chessEngine.gameRecord.GameRecord;
import chessEngine.position.Position;
import chessEngine.position.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class CurrentGameService {
    private final AccountService accountService;
    private final GameRecordService gameRecordService;
    private final CurrentGameRepository currentGameRepository;
    private final PositionService positionService;

    public CurrentGame get(String username) {
        try {
            Account account = accountService.findByUsername(username).orElseThrow();
            CurrentGame currentGame = account.getCurrentGame();
            return currentGame;
        }
        catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void createFor(String username) {
        try {
            Account account = accountService.findByUsername(username).orElseThrow();
            GameRecord gameRecord = new GameRecord(account);
            gameRecordService.save(gameRecord);
            Position position = positionService.getStartingPosition();
            CurrentGame currentGame = new CurrentGame(account, gameRecord, position);
            currentGameRepository.save(currentGame);
            accountService.updateCurrentGame(account.getEmail(), currentGame.getId());
            accountService.save(account);
        }
        catch (Exception e ) {
            throw new IllegalArgumentException("Error while creating game for: " + username);
        }
    }
}
