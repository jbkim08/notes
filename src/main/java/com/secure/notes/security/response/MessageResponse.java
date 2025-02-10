package com.secure.notes.security.response;

import lombok.Getter;
import lombok.Setter;

//문자열 메세지를 가진 객체
@Setter
@Getter
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

}