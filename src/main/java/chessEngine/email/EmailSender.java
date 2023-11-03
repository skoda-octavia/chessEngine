package chessEngine.email;

import jakarta.mail.MessagingException;

public interface EmailSender {
    void send(String recipient, String subject, String content) throws MessagingException;
}
