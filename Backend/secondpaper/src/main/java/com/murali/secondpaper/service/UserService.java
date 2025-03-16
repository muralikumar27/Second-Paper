package com.murali.secondpaper.service;

import com.murali.secondpaper.entity.User;
import com.murali.secondpaper.entity.VerificationToken;
import com.murali.secondpaper.entity.WorkVault;
import com.murali.secondpaper.enums.TokenType;
import com.murali.secondpaper.enums.VaultType;
import com.murali.secondpaper.event.UserRegistrationEvent;
import com.murali.secondpaper.event.UserVerificationEvent;
import com.murali.secondpaper.exception.AlreadyExistsException;
import com.murali.secondpaper.model.SignupDto;
import com.murali.secondpaper.repository.UserRepository;
import com.murali.secondpaper.repository.VerificationTokenRepository;
import com.murali.secondpaper.repository.WorkVaultRepository;
import com.murali.secondpaper.util.UrlCreator;
import com.murali.secondpaper.util.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final UrlCreator urlCreator;
    private final VerificationTokenRepository verificationTokenRepository;


    public UserService(PasswordEncoder passwordEncoder, UserRepository repository, UserMapper userMapper, ApplicationEventPublisher eventPublisher, UrlCreator urlCreator, VerificationTokenRepository verificationTokenRepository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
        this.userMapper = userMapper;
        this.eventPublisher = eventPublisher;
        this.urlCreator = urlCreator;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public String registerUser(SignupDto dto, HttpServletRequest request) {
        Optional<User> optionalUser = repository.findByEmail(dto.getEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!user.isEnabled()) {
                eventPublisher.publishEvent(new UserRegistrationEvent(user, urlCreator.createApplicationUrl(request)));
                return "This Email is already registered, we have sent a verification link to the mail Id";
            } else throw new AlreadyExistsException("Account with this email already exists");
        } else {
            User user = saveUser(userMapper.SignupDto2User(dto));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            eventPublisher.publishEvent(new UserRegistrationEvent(user, urlCreator.createApplicationUrl(request)));
            return "Please verify your account, verification link has been sent to email";
        }
    }

    private User saveUser(User user) {
        return repository.save(user);
    }

    public User getUserById(UUID userId) {
        return repository.findById(userId).get();
    }

    @Transactional
    public String VerifyUser(String token) {
        Optional<VerificationToken> optionalToken = verificationTokenRepository.findByToken(token);

        if (optionalToken.isEmpty() || optionalToken.get().getType() != TokenType.REGISTRATION)
            throw new IllegalArgumentException("Invalid Verification token, please hit re-send");

        if (Duration.between(optionalToken.get().getCreatedAt(), LocalDateTime.now())
                .compareTo(Duration.ofMinutes(5)) > 0)
            throw new IllegalArgumentException("Token Expired, please hit re-send");

        User user = optionalToken.get().getUser();

        if (user.isEnabled()) {
            throw new IllegalArgumentException("Account already verified, please login");
        }

        user.setEnabled(true);
        repository.save(user);

        eventPublisher.publishEvent(new UserVerificationEvent(user, user.getUsername(), VaultType.PRIVATE));

        return "Account verification successful";
    }
}
