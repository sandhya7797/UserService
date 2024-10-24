package com.scaler.userservice.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "roles")
public class Role extends BaseModel {

    private String name;

    private User user;

}
