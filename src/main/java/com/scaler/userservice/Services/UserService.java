package com.scaler.userservice.Services;


import com.scaler.userservice.Exceptions.TokenNotExistsException;
import com.scaler.userservice.Models.Token;
import com.scaler.userservice.Models.User;


public interface UserService {

    User signUp(String email, String password, String name);

    Token login(String email, String password);

    Token logOut(Token token) throws TokenNotExistsException;

}
