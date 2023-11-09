package chessEngine.security;

import chessEngine.account.Account;
import chessEngine.account.AccountRepository;
import chessEngine.account.AccountRole;
import chessEngine.registration.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticateResponse register(RegistrationRequest request) {
        var account = Account.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountRole(AccountRole.USER)
                .locked(false)
                .enabled(true)
                .accountRole(AccountRole.USER)
                .build();
        accountRepository.save(account);
        var jwtToken = jwtService.generateToken(account);
        return AuthenticateResponse.builder().token(jwtToken).build();
    }

    public AuthenticateResponse authenticate(AuthenticateRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        var account = accountRepository.findByUsername(authRequest.getUsername()).orElseThrow();
        System.out.println(account);
        var jwtToken = jwtService.generateToken(account);
        return AuthenticateResponse.builder().token(jwtToken).build();
    }
}
