package chessEngine.currentGame;

import chessEngine.account.Account;
import chessEngine.account.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CurrentGameService {
    private final AccountService accountService;

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
}
