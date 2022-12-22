package by.ilyin.core.util.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailProcessManager {

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public void sendSimpleMail(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getText());
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            throw new RuntimeException("Messaging error", e); //todo another exception
        }
    }

}
