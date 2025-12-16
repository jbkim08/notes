package com.secure.notes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//controller + @responseboy => 자바객체를 바로 json변환해서 리턴
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/contact")
    public String sayContact(){
        return "Contact";
    }

    @GetMapping("/hi")
    public String sayHi(){
        return "HI";
    }


}
