package com.sminfinitetech.thrivesonke;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordEncoderTest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(14);

    @Test
    void testPasswordEncodingAndMatching() {
        // Raw password to be tested
        String rawPassword = "password123";

        // Encode the raw password
       // String encodedPassword = passwordEncoder.encode(rawPassword);
        // "$2a$14$d04xxvSckZ0bpyFCCGbfgeZihb5akMPn3HpDc9ZxV.ejNIjaAa5xi"
        // "$2a$14$d04xxvSckZ0bpyFCCGbfgeZihb5akMPn3HpDc9ZxV.ejNIjaAa5xi"
        // "$2a$14$d04xxvSckZ0bpyFCCGbfgeZihb5akMPn3HpDc9ZxV.ejNIjaAa5xi"

        // Khumalo
        // "$2a$14$2imBIGoxKEsPO8J1J7Q2gutzaNOxtTPi8QuSenuj8KjiOXWt9OfB."
        // "$2a$14$2imBIGoxKEsPO8J1J7Q2gutzaNOxtTPi8QuSenuj8KjiOXWt9OfB."
        String encodedPassword = "$2a$14$x2DaZWi5uWP9WPeN2EYobeU.wAmsoJwIQn4C26N66aCDUY583rAr2";
        System.out.println("Encoded Password: " + encodedPassword);

        // Check that the encoded password is not null or empty
        assertNotNull(encodedPassword, "Encoded password should not be null");
       // assertFalse(encodedPassword.isEmpty(), "Encoded password should not be empty");

        // Match the raw password with the encoded password
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("Password Match: " + matches);

        // Assert that the password matches
        assertTrue(matches, "Password should match the encoded version");
    }

    @Test
    void testPasswordMismatch() {
        // Raw password and an incorrect encoded password
        String rawPassword = "Test@123";
        String wrongPassword = "WrongPassword";

        // Encode the raw password
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Check that the wrong password does not match
        boolean matches = passwordEncoder.matches(wrongPassword, encodedPassword);
        System.out.println("Password Match (Wrong Password): " + matches);

        // Assert that the password does not match
        assertFalse(matches, "Wrong password should not match the encoded version");
    }
}

