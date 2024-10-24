package com.scaler.userservice.Repositories;

import com.scaler.userservice.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Override
    Token save(Token token);

    Optional<Token> findByValueAndDeletedEquals(String value, boolean isDeleted);
}
