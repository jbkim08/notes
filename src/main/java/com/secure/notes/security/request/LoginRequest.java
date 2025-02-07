package com.secure.notes.security.request;

import lombok.Getter;
import lombok.Setter;

//로그인 객체
@Setter
@Getter
public class LoginRequest {
    private String username;
    private String password;
}