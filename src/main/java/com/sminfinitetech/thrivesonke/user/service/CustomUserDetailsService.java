package com.sminfinitetech.thrivesonke.user.service;


import com.sminfinitetech.thrivesonke.config.CustomUserDetails;
import com.sminfinitetech.thrivesonke.user.model.User;
import com.sminfinitetech.thrivesonke.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (Objects.isNull(user)){
            System.out.println("No user found");
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(user);
    }
}
