package chessEngine.account;

import chessEngine.gameRecord.GameRecord;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    public List<GameRecord> getGameRecords() {
        Account account = getAccountById(1L);
        return account.getGameRecordList();
    }

    public Account getAccountById(Long id) {
        Optional accountOptionan =  accountRepository.findById(id);
        if (accountOptionan.isPresent()) {
            return (Account) accountOptionan.get();
        } else {
            throw new EntityNotFoundException("Konto o podanym ID nie istnieje");
        }
    }


    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
