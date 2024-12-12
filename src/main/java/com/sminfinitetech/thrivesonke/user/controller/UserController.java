package com.sminfinitetech.thrivesonke.user.controller;

import com.sminfinitetech.thrivesonke.helper.ApiResponse;
import com.sminfinitetech.thrivesonke.user.dto.LoginRequest;
import com.sminfinitetech.thrivesonke.user.model.User;
import com.sminfinitetech.thrivesonke.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User API", description = "APIs for managing users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<?>> registerUser(@Valid @RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            ApiResponse<User> response = new ApiResponse<>(true, HttpStatus.OK.value(), createdUser);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<String> responseError = new ApiResponse<>(false, HttpStatus.BAD_REQUEST.value(), "Failed to register user: " + e.getMessage());
            return ResponseEntity.badRequest().body(responseError);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>>  userLogin(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            String token = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
            ApiResponse<String> response = new ApiResponse<>(true, HttpStatus.OK.value(), token);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<String> responseError = new ApiResponse<>(false, HttpStatus.UNAUTHORIZED.value(), "Login failed: " + e.getMessage());
            return ResponseEntity.status(401).body(responseError);
        }
    }
}
