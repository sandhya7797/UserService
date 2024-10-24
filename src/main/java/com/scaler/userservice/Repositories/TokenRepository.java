package com.scaler.userservice.Repositories;

import com.scaler.userservice.Models.Token;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Override
    Token save(Token token);

    Optional<Token> findByValueAndDeletedEquals(String value, boolean isDeleted);

    Optional<Token> findByValueAndDeletedEqualsAndExpireAtIsGreaterThanEqual(@NonNull String token, boolean b, Date date);
}
