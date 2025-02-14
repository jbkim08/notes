package com.secure.notes.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender; //자바메일센더 객체

    public void sendPasswordResetEmail(String to, String resetUrl){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to); //유저 이메일 주소
        message.setSubject("패스워드 리셋 요청");
        message.setText("링크를 클릭해 패스워드 리셋 : " + resetUrl);
        mailSender.send(message);
    }
}
