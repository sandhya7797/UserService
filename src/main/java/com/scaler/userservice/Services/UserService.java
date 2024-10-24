package com.scaler.userservice.Services;


import com.scaler.userservice.Exceptions.EmailNotExistsException;
import com.scaler.userservice.Exceptions.TokenIsInvalidOrNotExistsException;
import com.scaler.userservice.Models.Token;
import com.scaler.userservice.Models.User;
import lombok.NonNull;


public interface UserService {

    User signUp(String email, String password, String name);

    Token login(String email, String password) throws EmailNotExistsException;

    Token logOut(String token) throws TokenIsInvalidOrNotExistsException;

    User validateToken(@NonNull String token);
}
