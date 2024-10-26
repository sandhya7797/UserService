package com.scaler.userservice.Security.Services;

import com.scaler.userservice.Models.User;
import com.scaler.userservice.Repositories.UserRepository;
import com.scaler.userservice.Security.Models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //for us username is email so we will load user by email
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByEmail(username);

        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        CustomUserDetails userDetails = new CustomUserDetails(userOptional.get());

        return userDetails;
    }
}
