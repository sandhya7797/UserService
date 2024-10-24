package com.scaler.userservice.Controllers;


import com.scaler.userservice.DTOs.LogOutRequestDTO;
import com.scaler.userservice.DTOs.LoginRequestDTO;
import com.scaler.userservice.DTOs.SignUpRequestDTO;
import com.scaler.userservice.Exceptions.TokenNotExistsException;
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

    @PostMapping
    public ResponseEntity<User> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        User user =  userService.signUp(signUpRequestDTO.getEmail(), signUpRequestDTO.getPassword(), signUpRequestDTO.getName());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    public ResponseEntity<Token> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Token token = userService.login(loginRequestDTO.getEmail(),loginRequestDTO.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    public ResponseEntity<Token> logOUt(LogOutRequestDTO logOutRequestDTO) throws TokenNotExistsException {
        Token token = userService.logOut(logOutRequestDTO.getToken());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
