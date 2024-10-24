package com.scaler.userservice.Services;

import com.scaler.userservice.Exceptions.EmailNotExistsException;
import com.scaler.userservice.Exceptions.TokenIsInvalidOrNotExistsException;
import com.scaler.userservice.Models.Token;
import com.scaler.userservice.Models.User;
import com.scaler.userservice.Repositories.TokenRepository;
import com.scaler.userservice.Repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private TokenRepository tokenRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private RandomStringUtils randomStringUtils;

    public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                           RandomStringUtils randomStringUtils) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.randomStringUtils = randomStringUtils;
    }

    @Override
    public User signUp(String email, String password, String name) {

        User newUser = new User();

        newUser.setEmail(email);
        newUser.setHashPassword(bCryptPasswordEncoder.encode(password));
        newUser.setName(name);

        return userRepository.save(newUser);
    }

    @Override
    public Token login(String email, String password) throws EmailNotExistsException {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()) {
            throw new EmailNotExistsException("Email not exists!");
        }

        User savedUser = userOptional.get();

        LocalDate today = LocalDate.now();
        LocalDate dateIn30Days = today.plusDays(30);
        Date expiryDate = Date.from(dateIn30Days.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Token newToken = new Token();

        newToken.setValue(randomStringUtils.nextAlphanumeric(15));
        newToken.setExpireAt(expiryDate);
        newToken.setUser(savedUser);

        Token savedToken = tokenRepository.save(newToken);

        return savedToken;
    }

    @Override
    public Token logOut(String token) throws TokenIsInvalidOrNotExistsException {

        Optional<Token> tokenOptional = tokenRepository.findByValueAndDeletedEquals(token, false);

        if(tokenOptional.isEmpty() || tokenOptional.get().isDeleted()) {
            throw new TokenIsInvalidOrNotExistsException("Token is invalid or doesn't exists in database");
        }

        Token savedToken = tokenOptional.get();

        savedToken.setDeleted(true);

        tokenRepository.save(savedToken);

        return savedToken;
    }
}
