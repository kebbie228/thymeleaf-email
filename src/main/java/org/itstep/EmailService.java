package org.itstep;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender sender;

    public void sentTextEmail(Email email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.from);
        message.setTo(
                email.to
        );
        message.setSubject(email.subject);
        message.setText(email.text);

        sender.send(message);
    }
}