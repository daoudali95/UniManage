package com.example.unimanagement.security;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor extends HandlerInterceptorAdapter {

    private final TokenWrapper tokenWrapper;

    public TokenInterceptor(TokenWrapper tokenWrapper) {
        this.tokenWrapper = tokenWrapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        final String authorizationHeaderValue = request.getHeader("Authorization");
        if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer")) {
            String token = authorizationHeaderValue.substring(7, authorizationHeaderValue.length());
            tokenWrapper.setToken(token);
        }
        return true;
    }
}
