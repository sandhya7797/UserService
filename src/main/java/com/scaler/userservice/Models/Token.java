package com.scaler.userservice.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "tokens")
public class Token extends BaseModel {

    private String token;

    private Date expireAt;

    @ManyToOne
    private User user;
}
