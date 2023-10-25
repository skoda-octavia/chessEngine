package chessEngine.account;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AccountConfig {

    @Bean
    CommandLineRunner AccountCommandLineRunner(AccountRepository repository) {
        return args -> {
            String login = "lemonPeter";
            String email = "lemonPeter@gmail.com";
            String password = "haslo";
            Account account = new Account(login, email, password);
            repository.saveAll(List.of(account));
        };
    }

}
