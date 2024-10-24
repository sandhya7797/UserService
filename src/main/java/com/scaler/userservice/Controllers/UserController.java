package com.scaler.userservice.Controllers;


import com.scaler.userservice.DTOs.LogOutRequestDTO;
import com.scaler.userservice.DTOs.LoginRequestDTO;
import com.scaler.userservice.DTOs.SignUpRequestDTO;
import com.scaler.userservice.Exceptions.EmailNotExistsException;
import com.scaler.userservice.Exceptions.TokenIsInvalidOrNotExistsException;
import com.scaler.userservice.Models.Token;
import com.scaler.userservice.Models.User;
import com.scaler.userservice.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        User user =  userService.signUp(signUpRequestDTO.getEmail(), signUpRequestDTO.getPassword(), signUpRequestDTO.getName());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody LoginRequestDTO loginRequestDTO) throws EmailNotExistsException {
        Token token = userService.login(loginRequestDTO.getEmail(),loginRequestDTO.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<Token> logOUt(@RequestBody LogOutRequestDTO logOutRequestDTO) throws TokenIsInvalidOrNotExistsException {
        Token token = userService.logOut(logOutRequestDTO.getToken());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
