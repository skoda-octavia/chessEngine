package chessEngine;

import chessEngine.account.Account;
import chessEngine.account.AccountRepository;
import chessEngine.account.AccountRole;
import chessEngine.currentGame.CurrentGame;
import chessEngine.currentGame.CurrentGameRepository;
import chessEngine.gameRecord.GameRecord;
import chessEngine.gameRecord.GameRecordRepository;
import chessEngine.move.MoveRepository;
import chessEngine.position.Position;
import chessEngine.position.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final CurrentGameRepository currentGameRepository;
    private final GameRecordRepository gameRecordRepository;
    private final MoveRepository moveRepository;
    private final PositionRepository positionRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public SampleDataLoader(AccountRepository accountRepository,
                            CurrentGameRepository currentGameRepository,
                            GameRecordRepository gameRecordRepository,
                            MoveRepository moveRepository,
                            PositionRepository positionRepository) {
        this.accountRepository = accountRepository;
        this.currentGameRepository = currentGameRepository;
        this.gameRecordRepository = gameRecordRepository;
        this.moveRepository = moveRepository;
        this.positionRepository = positionRepository;

    }

    @Override
    public void run(String... args) {
        Account account = new Account();
        account.setUsername("login123");
        account.setEmail("lemonPeter123@gmail.com");
        account.setPassword(passwordEncoder.encode("haslo1"));
        account.setEnabled(true);
        account.setAccountRole(AccountRole.USER);
        accountRepository.save(account);

        String posCode = "bRbkbBbQbKbBbkbR";
        posCode += "bPbPbPbPbPbPbPbP";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "                ";
        posCode += "wPwPwPwPwPwPwPwP";
        posCode += "wRwkwBwQwKwBwkwR";
        Position pos = new Position(posCode, true);
        positionRepository.save(pos);

        GameRecord gameRecord2 = new GameRecord();
        gameRecord2.setAccount(account);
        gameRecord2.setFinished(true);
        gameRecord2.setGameCode("");
        gameRecord2.setStartedAt(LocalDateTime.now());
        gameRecordRepository.save(gameRecord2);

        CurrentGame currentGame = new CurrentGame(account, gameRecord2, pos);
        currentGameRepository.save(currentGame);

        account.setCurrentGame(currentGame);
        accountRepository.save(account);


        Account account2 = new Account();
        account2.setUsername("a");
        account2.setEmail("le3@gmail.com");
        account2.setPassword(passwordEncoder.encode("a"));
        account2.setEnabled(true);
        account2.setAccountRole(AccountRole.USER);
        accountRepository.save(account2);
    }

}
