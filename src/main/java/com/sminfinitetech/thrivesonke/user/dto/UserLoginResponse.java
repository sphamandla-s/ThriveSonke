package com.sminfinitetech.thrivesonke.user.dto;

import com.sminfinitetech.thrivesonke.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserLoginResponse {
    private User user;
    private String token;



}

