package com.scaler.userservice.Repositories;

import com.scaler.userservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    User save(User user);
}
