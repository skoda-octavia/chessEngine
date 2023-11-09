package chessEngine.account;

import chessEngine.gameRecord.GameRecord;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/account/")
public class AccountController {

    private final AccountService accountService;

//    @GetMapping("all")
//    public List<Account> getAllAccounts() {
//        System.out.println("get account req");
//        return this.accountService.getAllAccounts();
//    }

    @GetMapping("all")
    public String getAllAccounts() {
        System.out.println("get account req");
        return "accounts";
    }
}
