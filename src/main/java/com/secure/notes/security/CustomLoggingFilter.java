package com.secure.notes.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//이 클래스를 바로 객체 등록 (@Service, @Controller, @Repository)
@Component
public class CustomLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("커스텀 로그인 필터 - 요청주소 : " + request.getRequestURI());
        filterChain.doFilter(request, response);// 위로는 요청(request) 아래는 응답(response)
        System.out.println("커스텀 로그인 필터 - 응답상태 : " + response.getStatus());
    }
}
