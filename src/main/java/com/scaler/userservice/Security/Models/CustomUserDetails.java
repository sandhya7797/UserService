package com.scaler.userservice.Security.Models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scaler.userservice.Models.Role;
import com.scaler.userservice.Models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
public class CustomUserDetails implements UserDetails {

    private String password;

    private String username;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    private List<GrantedAuthority> authorities;

    public CustomUserDetails() {
    }

    public CustomUserDetails(User user) {
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.username = user.getEmail();
        this.password = user.getHashPassword();

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        for(Role role : user.getRoles()) {
            grantedAuthorityList.add(new CustomGrantedAuthority(role));
        }
        this.authorities = grantedAuthorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
