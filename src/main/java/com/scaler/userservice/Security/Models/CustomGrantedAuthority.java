package com.scaler.userservice.Security.Models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scaler.userservice.Models.Role;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {

    private String name;

    public CustomGrantedAuthority() {

    }

    public CustomGrantedAuthority(Role role) {
        this.name = role.getName();
    }

    @Override
    public String getAuthority() {
        return name;
    }

}
