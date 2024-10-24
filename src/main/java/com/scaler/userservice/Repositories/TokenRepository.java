package com.scaler.userservice.Repositories;

import com.scaler.userservice.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Override
    void delete(Token entity);

    @Override
    Token save(Token token);
}
