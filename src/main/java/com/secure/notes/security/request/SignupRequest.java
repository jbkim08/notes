package com.secure.notes.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
public class SignupRequest {

    @NotBlank(message = "유저네임을 입력해 주세요")
    @Size(min = 2, max = 20, message = "유저네임은 2-20자 가능합니다")
    private String username; //유저네임

    @NotBlank(message = "이메일을 입력해 주세요")
    @Size(max = 50)
    @Email
    private String email;  //이메일

    @Setter
    @Getter
    private Set<String> role; //권한

    @NotBlank(message = "패스워드를 입력해 주세요")
    @Size(min = 4, max = 10, message = "패스워드는 4-10자 가능")
    private String password; //패스워드

}
