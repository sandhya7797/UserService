package com.scaler.userservice.Services;

import com.scaler.userservice.Exceptions.TokenNotExistsException;
import com.scaler.userservice.Models.Token;
import com.scaler.userservice.Models.User;
import com.scaler.userservice.Repositories.TokenRepository;
import com.scaler.userservice.Repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private TokenRepository tokenRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
    public Token login(String email, String password) {

        Token newToken = new Token();

        newToken.setToken("abc");

        Token savedToken = tokenRepository.save(newToken);

        return savedToken;
    }

    @Override
    public Token logOut(Token token) throws TokenNotExistsException {
        Optional<Token> tokenOptional = tokenRepository.findById(token.getId());

        if(tokenOptional.isEmpty()) {
            throw new TokenNotExistsException("Token doesn't exists in database");
        }

        tokenRepository.delete(token);

        return token;
    }
}
