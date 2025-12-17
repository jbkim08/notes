package com.secure.notes.security.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class LoginResponse {

    private String jwtToken; //인증후 발행된 토큰
    private String username; //유저네임
    private List<String> roles; //권한리스트

    public LoginResponse(String username, List<String> roles, String jwtToken) {
        this.username = username;
        this.roles = roles;
        this.jwtToken = jwtToken;
    }

}
