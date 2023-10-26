package chessEngine.account;

import chessEngine.gameRecord.GameRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<GameRecord> getGameRecords() {
        return this.accountService.getAccountById(1L).getGameRecordList();
    }
}
