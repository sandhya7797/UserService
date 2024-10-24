package com.scaler.userservice.DTOs;


import com.scaler.userservice.Models.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogOutRequestDTO {

    private Token token;
}
