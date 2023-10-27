package chessEngine;

import chessEngine.account.Account;
import chessEngine.account.AccountRepository;
import chessEngine.currentGame.CurrentGame;
import chessEngine.currentGame.CurrentGameRepository;
import chessEngine.gameRecord.GameRecord;
import chessEngine.gameRecord.GameRecordRepository;
import chessEngine.move.MoveRepository;
import chessEngine.position.Position;
import chessEngine.position.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private AccountRepository accountRepository;
    private CurrentGameRepository currentGameRepository;
    private GameRecordRepository gameRecordRepository;
    private MoveRepository moveRepository;
    private PositionRepository positionRepository;

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
        account.setLogin("login123");
        account.setEmail("lemonPeter123@gmail.com");
        account.setPassword("haslo1");

        GameRecord gameRecord = new GameRecord();
        gameRecord.setAccount(account);
        gameRecord.setFinished(true);
        gameRecord.setGameCode("fdsfsdfsfsdsdfds");

        GameRecord gameRecord2 = new GameRecord();
        gameRecord2.setAccount(account);
        gameRecord2.setFinished(true);
        gameRecord2.setGameCode("");


        List<GameRecord> gameRecordList = new ArrayList<>();
        gameRecordList.add(gameRecord);
        gameRecordList.add(gameRecord2);

        account.setGameRecordList(gameRecordList);
        accountRepository.save(account);

        gameRecordRepository.save(gameRecord);
        gameRecordRepository.save(gameRecord2);


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

        CurrentGame currentGame = new CurrentGame(account, gameRecord2, pos);
        currentGameRepository.save(currentGame);

    }

}
