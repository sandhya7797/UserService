package com.scaler.userservice.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    private String email;

    private String password;

    private boolean isVerified;
}
