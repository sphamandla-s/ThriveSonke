package com.sminfinitetech.thrivesonke.user.service;

import com.sminfinitetech.thrivesonke.user.model.Tenant;
import com.sminfinitetech.thrivesonke.user.model.User;
import com.sminfinitetech.thrivesonke.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void saveTenantAdminUser(Tenant tenant) {
        User adminUser = new User();
        adminUser.setActive(1);
        adminUser.setEmail(tenant.getEmail());
        adminUser.setRole("ADMIN");
        adminUser.setUsername(tenant.getAdminUsername());
        adminUser.setPassword(passwordEncoder.encode(tenant.getAdminPassword()));
        adminUser.setTenant(tenant);

        userRepository.save(adminUser);

        System.out.println("Admin user created for tenant: " + tenant.getId());
    }

    public String loginUser(String username, String password) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            if (authenticate.isAuthenticated()) {
                return jwtService.generateToken(username, password);
            } else {
                throw new BadCredentialsException("Invalid credentials");
            }
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Login failed: Invalid username or password");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred during login: " + e.getMessage());
        }
    }
}
