package com.murali.secondpaper.event;

import com.murali.secondpaper.entity.User;
import com.murali.secondpaper.entity.VerificationToken;
import com.murali.secondpaper.enums.TokenType;
import com.murali.secondpaper.repository.VerificationTokenRepository;
import com.murali.secondpaper.util.EmailSender;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserRegistrationEventListener implements ApplicationListener<UserRegistrationEvent> {

    private final EmailSender emailSender;
    private final VerificationTokenRepository verificationTokenRepo;

    public UserRegistrationEventListener(EmailSender emailSender, VerificationTokenRepository verificationTokenRepo) {
        this.emailSender = emailSender;
        this.verificationTokenRepo = verificationTokenRepo;
    }

    @Override
    public void onApplicationEvent(UserRegistrationEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        String toEmail = user.getEmail();
        String subject = "Account verification";

        String mailBody = "Please verify your account by clicking on the link below\n" + event.getApplicationUrl() + "/verify-user?token=" + token;

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationToken.setCreatedAt(LocalDateTime.now());
        verificationToken.setType(TokenType.REGISTRATION);

        verificationTokenRepo.save(verificationToken);

        emailSender.sendEmail(toEmail, mailBody, subject);
    }
}
