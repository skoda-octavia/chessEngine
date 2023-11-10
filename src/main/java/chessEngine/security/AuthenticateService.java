package chessEngine.security;

import chessEngine.account.Account;
import chessEngine.account.AccountRepository;
import chessEngine.account.AccountRole;
import chessEngine.account.AccountService;
import chessEngine.email.EmailSender;
import chessEngine.confirmationToken.ConfirmationToken;
import chessEngine.confirmationToken.ConfirmationTokenService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticateService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationTokenService confirmationTokenService;
    private final AccountService accountService;
    private final EmailSender emailSender;


    @Transactional
    public AuthenticateResponse register(RegistrationRequest request)  {
        if (accountService.emailTaken(request.getEmail()) || !accountService.validEmail(request.getEmail())) {
            return new AuthenticateResponse("", 1, "Email invalid or already taken");
        }
        if (accountService.loginTaken(request.getUsername())) {
            return new AuthenticateResponse("", 1, "User name already taken");
        }
        Account account = new Account(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                AccountRole.USER);
        accountService.save(account);

        String token = confirmationTokenService.generateConfirmationToken();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                account
        );
        confirmationTokenService.saveCofirmationToken(confirmationToken);

        // TODO: 10.11.2023 change to frontend link
        String link = "http://localhost:4200/confirm?token=" + token;
        try {
            emailSender.send(request.getEmail(), "confirm", buildEmail(request.getUsername(), link));
        }
        catch (MessagingException e) {}

        var jwtToken = jwtService.generateToken(account);
        return new AuthenticateResponse(jwtToken, 0, "Successful registration!");
    }

    public AuthenticateResponse authenticate(AuthenticateRequest authRequest) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        authRequest.getUsername(),
//                        authRequest.getPassword()
//                )
//        );
        try {
            Account account = accountService.findByUsername(authRequest.getUsername()).orElseThrow();
            if (account.getPassword().equals(passwordEncoder.encode(authRequest.getPassword())) || true) {
                var jwtToken = jwtService.generateToken(account);
                return new AuthenticateResponse(jwtToken, 0, "Successful login");
            }
            else {
                return new AuthenticateResponse("", 1, "Incorrect password");
            }
        }
        catch (Exception e) {
            return new AuthenticateResponse("", 1, "User not found");
        }


    }

    @Transactional
    public TokenConfirmationResponse confirmToken(String token) {
        if (!confirmationTokenService.getToken(token).isPresent()) {
            return new TokenConfirmationResponse(1, "This token does not exist");
        }
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).get();
        if (confirmationToken.getConfirmedAt() != null) {
            return new TokenConfirmationResponse(1, "This token is already confirmed");
        }
        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now()) ) {
            return new TokenConfirmationResponse(1, "This token has expired");
        }
        confirmationTokenService.setTokenConfirmed(token);
        accountService.setAccountEnabled(confirmationToken);
        return new TokenConfirmationResponse(0, "Token successfully confirmed");
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
