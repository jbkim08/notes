package com.secure.notes.security.request;

import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//가입할때 입력객체
@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username; //유저네임은 3~20자

    @NotBlank
    @Size(max = 50)
    @Email
    private String email; //이메일 최대 50자

    @Setter
    @Getter
    private Set<String> role; //권한 리스트

    @NotBlank
    @Size(min = 6, max = 40)
    private String password; //패스워드 6자 40자까지
}