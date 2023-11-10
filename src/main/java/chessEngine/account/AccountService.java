package chessEngine.account;

import chessEngine.gameRecord.GameRecord;
import chessEngine.security.EmailValidator;
import chessEngine.confirmationToken.ConfirmationToken;
import chessEngine.confirmationToken.ConfirmationTokenService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class AccountService implements UserDetailsService {


    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailValidator emailValidator;

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

    public boolean validEmail(String email) {
        boolean validEmail = emailValidator.test(email);
        if (!validEmail) {return false;}
        boolean emailTaken = this.accountRepository.findByEmail(email).isPresent();
        if (emailTaken) {return false;}
        return true;
    }

    @Transactional
    public void signUpAccount(Account account) {
        String encodedPass = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodedPass);

        accountRepository.save(account);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            return accountRepository.findByUsername(username)
                    .orElseThrow(() ->
                            new UsernameNotFoundException(
                                    String.format("User with username %s not found", username)));

    }

    public void setAccountEnabled(ConfirmationToken confirmationToken) {
        Account account = confirmationToken.getAccount();
        accountRepository.enableAccount(account.getEmail());

    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public boolean emailTaken(String email) {return accountRepository.findByEmail(email).isPresent();}
    public boolean loginTaken(String username) {
        return accountRepository.findByUsername(username).isPresent();
    }
}
