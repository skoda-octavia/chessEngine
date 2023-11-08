package chessEngine.account;

import chessEngine.gameRecord.GameRecord;
import chessEngine.registration.EmailValidator;
import chessEngine.registration.RegistrationRequest;
import chessEngine.registration.token.ConfirmationToken;
import chessEngine.registration.token.ConfirmationTokenService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class AccountService implements UserDetailsService {


    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
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
    public String signUpAccount(Account account) {
        String encodedPass = bCryptPasswordEncoder.encode(account.getPassword());
        account.setPassword(encodedPass);

        accountRepository.save(account);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                account
        );
        confirmationTokenService.saveCofirmationToken(confirmationToken);
        return token;
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

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public boolean loginTaken(String username) {
        return accountRepository.findByUsername(username).isPresent();
    }
}
